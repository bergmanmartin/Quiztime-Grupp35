package Client.Controller;



import Client.Model.Questions;
import Client.Model.User;
import Client.View.Gameface;
import Client.View.PlayWithFriendsFrame;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;

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

    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public Client(String ip, int port, User user){

        //this.gameface = new Gameface();
        new PlayWithFriendsFrame(user, this);
        this.ip = ip;
        this.port = port;
        this.user = user;

        try {
            socket = new Socket(ip,port);

            new ClientConnecter().start();

            //new ClientGo().start();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private class ClientConnecter extends Thread{

        private LinkedList<User> userList;

        public ClientConnecter() throws IOException {

            oos = new ObjectOutputStream(socket.getOutputStream());
            //ois = new ObjectInputStream(socket.getInputStream());


        }
        public void run(){
            try {
                oos.writeObject(user);
                oos.flush();

                ois = new ObjectInputStream(socket.getInputStream());

                while(true){


                }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {

                    User a = (User) ois.readObject();




                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }



    private class ClientGo extends Thread{

        private int counter = 0;


        public void run(){

            try {

                oos = new ObjectOutputStream(socket.getOutputStream());



                } catch (IOException ex) {
                ex.printStackTrace();
            }


            while(true) {

                    newQuestions(counter);

                try {
                    sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                getAlternative(counter);

                    counter += 1;

                    System.out.println(numOfPoints);

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
