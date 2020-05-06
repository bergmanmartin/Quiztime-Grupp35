package Client.Controller;



import Client.Model.Questions;
import Client.Model.User;
import Client.View.Gameface;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    private int numOfPoints = 0;
    private User user;

    public Client(String ip, int port, User user){

        this.gameface = new Gameface();
        this.ip = ip;
        this.port = port;
        this.user = user;

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

        private ObjectOutputStream oos;

        public void run(){

            try {

                oos = new ObjectOutputStream(socket.getOutputStream());

                oos.writeObject(user);

                } catch (IOException ex) {
                ex.printStackTrace();
            }


            while(true) {

                    newQuestions(counter);

                    sleep(10000);

                    getAlternative(counter);

                    counter += 1;

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

        public void newQuestions(int counter){

            gameface.setQuestion(questions[counter].getQuestion(),questions[counter].getAlternative1(),questions[counter].getAlternative2(),questions[counter].getAlternative3(),questions[counter].getAlternative4());
        }

        public void getAlternative(int counter){

            System.out.println(gameface.getSelectedButton());
            System.out.println(questions[counter].getCorrectAlternative());

            if (gameface.getSelectedButton().equals(questions[counter].getCorrectAlternative())){

                numOfPoints += 1;
            }

        }

    }

}
