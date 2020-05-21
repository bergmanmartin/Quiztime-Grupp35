package Server;



import SharedResources.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

/**
 * @Author Markus Gerdtsson, Marianne Mukanga, Martin Bergman och Erik Nielsen.
 * This class is the server which controls the different questions and communicates with the client.
 */
public class Server{


    private QuestionReader qr;

    private Questions[] gameQuestions;

    private LinkedList<User> userLinkedList;

    private ServerFrame sf;

    private boolean allnotReady = true;

    private boolean gameStarted = false;


    /**
     * Initializes the class QuestionReader.
     * Collects the questions from the array.
     * Initializes a new LinkedList and starts a new ServerFrame.
     * Starts class Connection.
     * @param port port for the server
     * @throws IOException
     */
    public Server(int port) throws IOException {

        qr = new QuestionReader();

        gameQuestions = qr.getQuestions();

        userLinkedList = new LinkedList<>();

        sf = new ServerFrame(this);



        new Connection(port).start();
    }



    /**
     * @Author Markus Gerdtsson, Marianne Mukanga, Martin Bergman och Erik Nielsen.
     * This class is a thread which controls the different connections made to the server.
     */
    private class Connection extends Thread{
        private int port;

        /**
         * @param port the connection port
         * @throws IOException
         */
        public Connection(int port) throws IOException {
            this.port = port;
        }

        /**
         * Initializes a new serverSocket, accepts new connections and creates class clientHandler.
         */
        public void run(){
            Socket socket = null;
            System.out.println("servern staartad");

            try(ServerSocket serverSocket = new ServerSocket(port)){

                System.out.println("lyssnar på port nr" + serverSocket.getLocalPort());

                    try{
                        socket = serverSocket.accept();

                        new clientHandler(socket);

                    } catch (IOException e) {
                        e.printStackTrace();

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


    /**
     * @Author Markus Gerdtsson, Marianne Mukanga, Martin Bergman och Erik Nielsen.
     * This class handles the different clients and adds user data.
     */
    private class clientHandler extends Thread {

        private Socket socket;
        private DataOutputStream dos;

        private int index = 0;

        private DataOutputStream dataOutputStream;

        public clientHandler(Socket socket) {

            this.socket = socket;

            start();

        }


        /**
         * Initializes a new ObjectInputStream and socket gets InputStream.
         * Adds user data to the LinkedList and reads the object.
         * Initializes a new DataOutputStream and socket gets OutputStream.
         * Then calls the method sendUsers.
         */
        public void run() {


            while (!Thread.interrupted()) {

                try {

                    //dos = new DataOutputStream(socket.getOutputStream());


                    ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

                    userLinkedList.add((User) inputStream.readObject());

                    if (userLinkedList.getFirst().isReady()){
                        new QuestionSender(socket);
                        break;
                    }

                    dataOutputStream = new DataOutputStream(socket.getOutputStream());

                    sendUsers();


                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * DataOutputStream writes the LinkedList different index for the username.
         * @throws IOException
         */
        public void sendUsers() throws IOException {

            dataOutputStream.writeUTF("ny");

            for (int i  = 0; i<userLinkedList.size(); i++) {
                dataOutputStream.writeUTF(userLinkedList.get(i).getUsername());
            }
        }
    }


    /**
     *@Author Markus Gerdtsson, Marianne Mukanga, Martin Bergman och Erik Nielsen.
     * This class writes the gameQuestions in an ObjectOutputStream.
     */
    private class QuestionSender extends Thread{


        /**
         * Initializes a new ObjectOutputStream and tells the socket to get the OutputStream.
         * Writes the gameQuestions class with it's different index 10 times in a for-loop.
         * @param socket
         * @throws IOException
         */
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
