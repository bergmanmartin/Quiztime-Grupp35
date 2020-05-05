package Controller;

import Model.QuestionModel.Questions;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Created 11/02/2020
 * @project P1
 * @Markus Gerdtsson
 */

public class Server {

    private Questionreader qr;

    private Questions[] gameQuestions;


    public Server(int port) throws IOException {

        qr = new Questionreader();

        gameQuestions = qr.getQuestions();

        new Connection(port).start();
    }

    /**
     * Inner class that connect the client to the server
     */

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
     * Inner class that run the thread during the game
     */

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



