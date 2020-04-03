package Controll;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @Created 11/02/2020
 * @project P1
 * @Markus Gerdtsson
 */
public class Server {
    private String[] namn = new String[21];


    public Server(int port) throws IOException {

        readFromFile();

        new Connection(port).start();
    }

    public void readFromFile(){

        try {
            BufferedReader reader = new BufferedReader(new FileReader("files/Questions.txt"));

            for (int i = 0; i < 21; i++) {
                namn[i] = reader.readLine();
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

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


    private class clientHandler extends Thread {

        private Socket socket;
        private DataOutputStream dos;

        private int index = 0;


        public clientHandler(Socket socket) {


            this.socket = socket;

            start();

        }


        public void run() {


            while (!Thread.interrupted()) {

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


}


