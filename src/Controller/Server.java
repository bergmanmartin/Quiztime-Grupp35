package Controller;

import Model.QuestionModel.Questions;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Sets up the server connection and reads the question from the reader.
 * @author Markus Gerdtsson
 * @author Erik Nielsen
 * @version 1.0
 */

public class Server {

    private Questionreader qr;

    private Questions[] gameQuestions;

    /**
     * Initializes the reader and the questions calsses and sets up the Connection
     * @param port
     * @throws IOException
     */
    public Server(int port) throws IOException {

        qr = new Questionreader();

        gameQuestions = qr.getQuestions();

        new Connection(port).start();
    }

<<<<<<< HEAD
    /**
     * Inner class that connect the client to the server
     */

=======

    /**
     * Sets up the server connection
     * @author Martin Bergman
     * @author Erik Nielsen
     */
>>>>>>> Comment
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

                while (true){
                    try{
                        socket = serverSocket.accept();
                        new clientHandler(socket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
            catch (IOException e ){
                e.printStackTrace();
            }
        }
    }

    /**
<<<<<<< HEAD
     * Inner class that run the thread during the game
     */

=======
     * Sets up the clienthandler that reads  from the outputStream.
     */
>>>>>>> Comment
    private class clientHandler extends Thread {

        private Socket socket;
        private DataOutputStream dos;


        public clientHandler(Socket socket) {


            this.socket = socket;

            start();

        }


        public void run() {


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

    /**
     * Sends the questions on demand
     */

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



