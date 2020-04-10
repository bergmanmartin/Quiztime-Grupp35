package Controller;

import Model.QuestionModel.Questions;
import VIew.GameFrame.Gameface;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @Created 11/02/2020
 * @project P1
 * @Markus Gerdtsson
 */
public class Client {
    // Martin
    private String ip;
    private int port;
    private Socket socket;
    private Gameface gameface;
    private Questions[] questions = new Questions[10];

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

    private class ClientGo extends Thread{

        private int counter = 0;

        public void run(){

            try {

                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

                for (int i = 0; i < 10; i++) {

                    questions[i] = (Questions) inputStream.readObject();
                }


                while(true) {

                    newQuestions(counter);
                    counter += 1;

                    sleep(10000);

                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        public void newQuestions(int counter){

            gameface.setQuestion(questions[counter].getQuestion(),questions[counter].getAlternative1(),questions[counter].getAlternative2(),questions[counter].getAlternative3(),questions[counter].getAlternative4());


        }


    }





}
