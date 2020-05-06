package Controller;

import Model.QuestionModel.Questions;
import VIew.GameFrame.Gameface;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Class that represents the client part of the server-client architecture. Considered the controller class for the game.
 * Has contact with the server and gathers information from the player and the server.
 * @author Markus Gerdtsson
 * @author Erik Nielse
 * @version 1.0
 */
public class Client {

    private String ip;
    private int port;
    private Socket socket;
    private Gameface gameface;
    private Questions[] questions = new Questions[10];
    private int numOfPoints = 0;

    /**
     * Initializes the gameface and sets up the client with the server connection.
     * @param ip
     * @param port
     */
    public Client(String ip, int port){

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

        //Tar nästa fråga
        private int counterOfQuestion = 0;

        public void run(){

            try {

                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());


                for (int i = 0; i < 10; i++) {

                    questions[i] = (Questions) inputStream.readObject();
                    gameface.resetButtons();
                }


                while (true) {
                    //Eventuellt en if-sats här?
                    newQuestions(counterOfQuestion);

                    sleep(10000);

                    getAlternative(counterOfQuestion);

                    //gameface.getSelectedKnapp().setSelected(false);

                    counterOfQuestion += 1;

                    System.out.println(numOfPoints);
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
        }

        /**
         * Checks the alternative and checks if the answer is correct and then adds a point if the condicition is fullfilled.
         * @param counter questions counter
         */
        public void getAlternative(int counter){

            System.out.println(gameface.getSelectedButton());
            System.out.println(questions[counter].getCorrectAlternative());

            if (gameface.getSelectedButton().equals(questions[counter].getCorrectAlternative())){

                numOfPoints += 1;
            }

        }

    }

}
