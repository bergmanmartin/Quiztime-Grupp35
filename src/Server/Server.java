package Server;



import SharedResources.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

/**
 * @Created 11/02/2020
 * @project P1
 * @Markus Gerdtsson
 */
public class Server {


    private QuestionReader qr;

    private Questions[] gameQuestions;

    private LinkedList<User> userLinkedList;

    private ServerFrame sf;

    private boolean gameStarted = false;



    public Server(int port) throws IOException {

        qr = new QuestionReader();

        gameQuestions = qr.getQuestions();

        userLinkedList = new LinkedList<>();

        sf = new ServerFrame(this);



        new Connection(port).start();
    }



    private class Connection extends Thread{
        private int port;

        public Connection(int port) throws IOException {
            this.port = port;
        }
        public void run(){
            Socket socket = null;
            System.out.println("servern staartad");

            try(ServerSocket serverSocket = new ServerSocket(port)){

                System.out.println("lyssnar på port nr" + serverSocket.getLocalPort());

                while (userLinkedList.size() <= 4){
                    try{
                        socket = serverSocket.accept();
                        new clientHandler(socket);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                while (allReady() == false){

                    sleep(500);

                }

                new QuestionSender(socket);

            }
            catch (IOException | InterruptedException e ){
                e.printStackTrace();
            }
        }
    }

    public boolean allReady(){
        for (User user : userLinkedList) {
            if (user.isReady() == false){
                return false;
            }
        }
        return true;
    }


    private class clientHandler extends Thread {

        private Socket socket;
        private DataOutputStream dos;

        private int index = 0;

        private DataOutputStream dataOutputStream;

        public clientHandler(Socket socket) {



            this.socket = socket;

            start();

        }


        public void run() {


            while (!Thread.interrupted()) {

                try {

                    //dos = new DataOutputStream(socket.getOutputStream());


                    ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

                    userLinkedList.add((User) inputStream.readObject());

                    dataOutputStream = new DataOutputStream(socket.getOutputStream());

                    sendUsers();


                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        public void sendUsers() throws IOException {

            dataOutputStream.writeUTF("ny");

            for (int i  = 0; i<userLinkedList.size(); i++) {
                dataOutputStream.writeUTF(userLinkedList.get(i).getUsername());
            }
        }
    }


    private class QuestionSender extends Thread{

        public QuestionSender(Socket socket) throws IOException {

            while (!Thread.interrupted()) {

                try {

                    //dos = new DataOutputStream(socket.getOutputStream());

                    ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

                    for(int i = 0; i<10; i++){

                        outputStream.writeObject(gameQuestions[i]);
                        outputStream.flush();

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


        /*public void sendQuestions(){
            try {

                dos = new DataOutputStream(socket.getOutputStream());

                for(int i = 0; i<21;i++){

                    dos.writeUTF(namn[i]);

                    Thread.sleep(2000);


                }



            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

         */
}
