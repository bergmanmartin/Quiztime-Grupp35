package Server;



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

    private LinkedList<User> userList;

    private LinkedList<Socket> socketList;

    private LinkedList<ObjectOutputStream> objectStreamsList;


    public Server(int port) throws IOException {

        qr = new QuestionReader();

        gameQuestions = qr.getQuestions();

        userList = new LinkedList<>();

        socketList = new LinkedList<>();

        objectStreamsList = new LinkedList<>();



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

                while (true){
                    try{
                        socket = serverSocket.accept();

                        new clientList(socket).start();

                        //new clientHandler(socket);

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

    private class clientList extends Thread {

        private Socket socket;
        private ObjectInputStream ois;
        private ObjectOutputStream oos;

        public clientList(Socket socket){
            this.socket = socket;
            System.out.println("Startar en socket");
        }

        public void run(){

            try {

                ois = new ObjectInputStream(socket.getInputStream());
                //oos = new ObjectOutputStream(socket.getOutputStream());

                User user = (User) ois.readObject();

                System.out.println(object.);

                userList.add();

                for (Socket socket1 : socketList) {
                    for (User user1 : userList) {
                        oos.writeObject(user1.getUsername());
                    }
                }




            } catch (IOException | ClassNotFoundException e) {
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
