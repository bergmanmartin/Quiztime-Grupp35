package Controller;

import Model.QuestionModel.Questions;
import Model.Users.User;
import VIew.GameFrame.Gameface;
import VIew.Highscore.HighscoreFrame;

import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Class that represents the client part of the server-client architecture. Considered the controller class for the game.
 * Has contact with the server and gathers information from the player and the server.
 * @author Markus Gerdtsson
 * @author Erik Nielsen
 * @version 1.0
 */
public class Client {

    private String ip;
    private int port;
    private Socket socket;
    private Gameface gameface;
    private Questions[] questions = new Questions[10];
    private int numOfPoints = 0;
    private User user;

    private String[] correctalteratives = new String[10];
    private String[] answeList = new String[10];

    /**
     * Initializes the gameface and sets up the client with the server connection.
     * @param ip
     * @param port
     */
    public Client(String ip, int port, User user){
        this.user = user;
        this.gameface = new Gameface();
        this.ip = ip;
        this.port = port;

        try {
            socket = new Socket(ip,port);

            new ClientGo().start();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Runs the client and retrieves information form the server. Reads the question objects. After 10 seconds a new
     * question is read.
     */
    private class ClientGo extends Thread{
        private volatile boolean running = true;
        //Tar nästa fråga
        private int counterOfQuestion = 0;

        public void run(){

            try {

                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());


                for (int i = 0; i < 10; i++) {

                    questions[i] = (Questions) inputStream.readObject();
                    gameface.resetButtons();
                }


                while (running) {
                    //Eventuellt en if-sats här?

                    newQuestions(counterOfQuestion);

                    sleep(100);

                    getAlternative(counterOfQuestion);
                    sleep(200);


                    //gameface.getSelectedKnapp().setSelected(false);

                    counterOfQuestion += 1;

                    System.out.println("Points:" + numOfPoints);

                    if (counterOfQuestion == 10){
                        gameface.setVisible(false);
                        running = false;
                        System.out.println("Jag lever forfarande!!!");
                        new HighscoreFrame(user,numOfPoints,correctalteratives, answeList);

                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        /**
         * Shwos a new question.
         * @param counter
         */
        public void newQuestions(int counter){

            gameface.setQuestion(questions[counter].getQuestion(),questions[counter].getAlternative1(),questions[counter].getAlternative2(),questions[counter].getAlternative3(),questions[counter].getAlternative4());
            correctalteratives[counter] = questions[counter].getCorrectAlternative();
        }

        /**
         * Checks the alternative and checks if the answer is correct and then adds a point if the condicition is fullfilled.
         * @param counter questions counter
         */
        public void getAlternative(int counter){

            System.out.println("Chosen alternative: " + gameface.getSelectedButton());
            System.out.println("Correct alternative: " + questions[counter].getCorrectAlternative());

            answeList[counter] = gameface.getSelectedButton();

            if (gameface.getSelectedButton().equals(questions[counter].getCorrectAlternative())){

                numOfPoints += 1;
                gameface.setColorToGreenJButton();
            }
            else {
                gameface.setColorToRedJButton();
            }

        }

    }

}
