package Server;


import SharedResources.Questions;
import SharedResources.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * @project QuizTime
 * @author Markus Gerdtsson, Marianne Mukanga, Martin Bergman och Erik Nielsen.
 * @version 1.4
 *
 * This class is the server which controls the different questions and communicates with the client.
 */

public class Server {


    private QuestionReader qr;

    private Questions[] gameQuestions;

    private LinkedList<User> userLinkedList;

    private LinkedList<clientHandler> clientHandlers;


    private boolean allnotReady = true;

    private boolean gameStarted = false;

    private boolean checking = true;



    /**
     * Initializes the class QuestionReader.
     * Collects the questions from the array.
     * Initializes a new LinkedList and starts a new ServerFrame.
     * Starts class Connection.
     *
     * @param port port for the server
     * @throws IOException
     */
    public Server(int port) throws IOException {

        qr = new QuestionReader();

        gameQuestions = qr.getQuestions();

        userLinkedList = new LinkedList<>();

        clientHandlers = new LinkedList<>();

        new StatusChecker().start();

        new Connection(port).start();
    }


    /**
     * @project QuizTime
     * @author Markus Gerdtsson, Marianne Mukanga, Martin Bergman och Erik Nielsen.
     * @version 1.4
     *
     * This class is a thread which controls the different connections made to the server.
     */
    private class Connection extends Thread {
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
        public void run() {
            Socket socket = null;
            System.out.println("servern staartad");

            try (ServerSocket serverSocket = new ServerSocket(port)) {

                System.out.println("lyssnar på port nr" + serverSocket.getLocalPort());

                while (true) {
                    try {
                        socket = serverSocket.accept();

                        clientHandlers.add(new clientHandler(socket));

                    } catch (IOException e) {
                        e.printStackTrace();

                    }
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @project QuizTime
     * @author Markus Gerdtsson, Marianne Mukanga, Martin Bergman och Erik Nielsen.
     * @version 1.4
     *
     * This class is a thread which checks if all players are ready to play.
     */
    private class StatusChecker extends Thread {

        public void run() {

            while (checking) {


                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (allReady() == true) {
                    System.out.println(allReady());
                    new QuestionSender();
                    checking = false;

                }

            }
        }

        public boolean allReady() {

            if (userLinkedList.size() < 1) {
                return false;
            }

            for (User user : userLinkedList) {
                System.out.println(user.isReady());
                if (user.isReady() == false) {
                    return false;
                }
            }
            return true;
        }
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
        private ObjectOutputStream outputStream;
        private ObjectInputStream inputStream;

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


            while (!interrupted())
                try {


                    inputStream = new ObjectInputStream(socket.getInputStream());
                    outputStream = new ObjectOutputStream(socket.getOutputStream());

                    User connectedUser = (User) inputStream.readObject();

                    userLinkedList.add(connectedUser);

                    if (connectedUser.getPlayAlone() == true) {
                        new QuestionSender(socket);
                        break;
                    }


                    dataOutputStream = new DataOutputStream(socket.getOutputStream());

                    sendUsers();

                    while (allnotReady) {
                        User u = (User) inputStream.readObject();
                        for (User user : userLinkedList) {
                            if (user.getUsername().equals(u.getUsername())) {
                                user.setReadyTrue();
                            }
                        }
                    }


                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

        }

        /**
         * DataOutputStream writes the LinkedList different index for the username.
         *
         * @throws IOException
         */
        public void sendUsers() throws IOException {

            for (Server.clientHandler clientHandler1 : clientHandlers) {
                clientHandler1.dataOutputStream.writeUTF("ny");

                for (int i = 0; i < userLinkedList.size(); i++) {
                    clientHandler1.dataOutputStream.writeUTF(userLinkedList.get(i).getUsername());
                }
            }
        }
    }


    /**
     * @Author Markus Gerdtsson, Marianne Mukanga, Martin Bergman och Erik Nielsen.
     * This class writes the gameQuestions in an ObjectOutputStream.
     */
    private class QuestionSender extends Thread {

        Object object = new Object();

        /**
         * Initializes a new ObjectOutputStream and tells the socket to get the OutputStream.
         * Writes the gameQuestions class with it's different index 10 times in a for-loop.
         *
         * @param socket
         * @throws IOException
         */

        //For playAlone
        public QuestionSender(Socket socket) throws IOException, ClassNotFoundException {


            try {


                for (int i = 0; i < 10; i++) {

                    clientHandlers.getFirst().outputStream.writeObject((Questions) gameQuestions[i]);
                    clientHandlers.getFirst().outputStream.flush();

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public QuestionSender() {

            checking = false;


            System.out.println("quiestionsender skapas");

            try {

                for (Server.clientHandler clientHandler : clientHandlers) {

                    clientHandler.dataOutputStream.writeUTF("start");

                    clientHandler.dataOutputStream.writeUTF(String.valueOf(clientHandlers.size()));


                    for (int i = 0; i < 10; i++) {


                        System.out.println("skriver fråga " + i);
                        clientHandler.outputStream.writeObject((Questions) gameQuestions[i]);
                        clientHandler.outputStream.flush();
                    }

                }

                /*
                System.out.println("1");
                for (Server.clientHandler clientHandler : clientHandlers) {

                    User u = (User) clientHandler.inputStream.readObject();
                    System.out.println("2");
                    for (User user : userLinkedList) {
                        if (user.getUsername().equals(u.getUsername())) {
                            u = user;
                            System.out.println("3");
                        }
                    }

                }

                for (Server.clientHandler clientHandler : clientHandlers) {
                    System.out.println("4");
                    for (User user : userLinkedList) {
                        clientHandler.outputStream.writeObject(user);
                        clientHandler.outputStream.flush();
                        System.out.println("5");
                    }
                }


} catch (ClassNotFoundException e) {
                e.printStackTrace();
                 */
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }
}

