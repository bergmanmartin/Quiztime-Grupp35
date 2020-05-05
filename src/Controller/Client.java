package Controller;

import Model.QuestionModel.Questions;
import VIew.GameFrame.Gameface;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @Created 11/02/2020
 * @project QuizTime
 * @Markus Gerdtsson
 */


public class Client {

    private String ip;
    private int port;
    private Socket socket;
    private Gameface gameface;
    private Questions[] questions = new Questions[10];
    private int numOfPoints = 0;

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
     * Inner class that run the thread
     */
    private class ClientGo extends Thread{

        //Keeping track of number of questions shown
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
         * Displaying the new question
         * @param counter
         */

        public void newQuestions(int counter){

            gameface.setQuestion(questions[counter].getQuestion(),questions[counter].getAlternative1(),questions[counter].getAlternative2(),questions[counter].getAlternative3(),questions[counter].getAlternative4());
        }

        /**
         * Collecting the alternative the player chooses
         * @param counter
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
