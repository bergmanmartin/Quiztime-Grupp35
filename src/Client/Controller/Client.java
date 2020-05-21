package Client.Controller;



import Client.Model.Questions;
import Client.View.Gameface;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 *  This class is the controller-class for client and handles a client for user. The class keep track of the client's socket,.
 * @author martinbergman. Marianne Mukanga, Erik Nielsen, Markus Gerdtsson
 * @version 1.2
 */
public class Client {
    // Martin
    private String ip;
    private int port;
    private Socket socket;
    private Gameface gameface;
    private Questions[] questions = new Questions[10];
    private int numOfPoints = 0;

    /**
     * Constructor of user, GUI, ip-adress and port to the server. Constructs information about the server that's to be called.
     * This constructor is called when the user chooses to play alone and opens the Gameface.
     */
    public Client(String ip, int port){

        this.gameface = new Gameface();
        this.ip = ip;
        this.port = port;

        try {
            socket = new Socket(ip,port);

            new ClientGo().start();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * This is an inner-class extending a thread that handles the questions.
     * This class is used when the user chooses to play alone.
     */

    private class ClientGo extends Thread{

        private int counter = 0;

        /**
         * The server fetches the question from the file. The question are transformed to objectsand loops through 10 times
         * for 10 questions.
         */
        public void run(){

            try {

                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

                for (int i = 0; i < 10; i++) {

                    questions[i] = (Questions) inputStream.readObject();
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

        /**
         * Method that makes a new question apeear from the file
         * @param counter number of questions
         */
        public void newQuestions(int counter){

            gameface.setQuestion(questions[counter].getQuestion(),questions[counter].getAlternative1(),questions[counter].getAlternative2(),questions[counter].getAlternative3(),questions[counter].getAlternative4());
        }

        /**
         * Returns the selected alternative and then adds to the points if answer is correct.
         * @param counter
         */
        public void getAlternative(int counter){

            System.out.println(gameface.getSelectedButton());
            System.out.println(questions[counter].getCorrectAlternative());

            if (gameface.getSelectedButton().equals(questions[counter].getCorrectAlternative())){

                numOfPoints += 1;
            }

        }

    }

}
