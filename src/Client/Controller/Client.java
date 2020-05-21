package Client.Controller;



import Client.View.HighscoreFrame;
import Client.View.PlayWithFriendsFrame;
import SharedResources.Message;
import SharedResources.Questions;
import Client.View.Gameface;
import SharedResources.User;

import java.io.DataInputStream;
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
    private PlayWithFriendsFrame playWithFriendsFrame;
    private Questions[] questions = new Questions[10];
    private int numOfPoints = 0;
    private User user;
    //private LinkedList<> userlist = new LinkedList<>();
    private boolean ready;

    private String[] correctalteratives = new String[10];
    private String[] answerList = new String[10];

    public Client(String ip, int port, User user){

        this.gameface = new Gameface();
        this.ip = ip;
        this.port = port;
        this.user = user;
        ready = true;

        try {
            socket = new Socket(ip,port);

            new ClientGoSolo().start();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Client(String ip, int port, User user, boolean ready){

        this.playWithFriendsFrame = new PlayWithFriendsFrame(this, user);
        this.ready = ready;
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

    private class ClientGoSolo extends Thread{

        private volatile boolean running = true;
        //Tar nästa fråga
        private int counterOfQuestion = 0;

        public void run(){

            try {


                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

                outputStream.writeObject(user);
                outputStream.flush();

                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());


                for (int i = 0; i < 10; i++) {

                    questions[i] = (Questions) inputStream.readObject();
                    gameface.resetButtons();

                }


                while (running) {
                    //Eventuellt en if-sats här?


                    newQuestions(counterOfQuestion);

                    sleep(10000);

                    getAlternative(counterOfQuestion);
                    sleep(1500);


                    //gameface.getSelectedKnapp().setSelected(false);

                    counterOfQuestion += 1;

                    System.out.println("Points:" + numOfPoints);

                    if (counterOfQuestion == 10){
                        gameface.setVisible(false);
                        running = false;
                        System.out.println("Jag lever forfarande!!!");
                        new HighscoreFrame(user,numOfPoints,correctalteratives, answerList);

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
    }


    private class ClientGo extends Thread{

        private int counter = 0;

        public void run(){

            try {


                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

                outputStream.writeObject(user);
                outputStream.flush();

                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());




                while(true) {

                    String s = dataInputStream.readUTF();

                    if (s.equals("ny")){
                        playWithFriendsFrame.clearList();
                    }
                    else {
                        playWithFriendsFrame.updateList(s);
                    }



                    //playWithFriendsFrame.addElement();



                    /*
                    switch (message.getTyp()){

                        case "Questions":
                            //NY innre klass'
                            break;

                        case "User":
                            userlist = message;

                    }*/


                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        }
    public void newQuestions(int counter){

        gameface.setQuestion(questions[counter].getQuestion(),questions[counter].getAlternative1(),questions[counter].getAlternative2(),questions[counter].getAlternative3(),questions[counter].getAlternative4());
        correctalteratives[counter] = questions[counter].getCorrectAlternative();
    }

    public void getAlternative(int counter){

        System.out.println(gameface.getSelectedButton());
        System.out.println(questions[counter].getCorrectAlternative());

        answerList[counter] = gameface.getSelectedButton();

        if (gameface.getSelectedButton().equals(questions[counter].getCorrectAlternative())){

            numOfPoints += 1;
            gameface.setColorToGreenJButton();
        }

        else{
            gameface.setColorToRedJButton();
        }
    }
}

