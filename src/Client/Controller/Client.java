package Client.Controller;


import Client.View.HighscoreFrame;
import Client.View.PlayWithFriendsFrame;
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
 * @project Quiztime
 * @author Markus Gerdtsson, Erik Nielsen, Marianne Mukanga, Martin Bergman
 * @version 1.4
 *
 * This class handles the client's side of the program. Handles the connection with the server.
 * The class also handles the input- and outputstreams. Basically plays the game.
 */
public class Client {

    private String ip;
    private int port;

    private Socket socket;
    private Gameface gameface;
    private PlayWithFriendsFrame playWithFriendsFrame;
    private Questions[] questions = new Questions[10];

    private int numOfPoints = 0;

    private User user;

    private boolean ready;
    private boolean waitingForPlayers = true;
    private int numOfPlayers;

    private DataInputStream dataInputStream;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    private String[] correctalteratives = new String[10];
    private String[] answerList = new String[10];

    private LinkedList<User> userScore;


    /**
     * Constructs and initializes the game and starts the play alone thread.
     * @param ip the ip adress as a String
     * @param port the port for the connection
     * @param user the User-object
     */
    public Client(String ip, int port, User user) {

        this.gameface = new Gameface();
        this.ip = ip;
        this.port = port;
        this.user = user;
        user.setPlayAlone();

        try {
            socket = new Socket(ip, port);

            new ClientGoSolo().start();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Constructs, initializes and starts the game if you've chosen play with friends.
     * @param ip Ip-adress
     * @param port int port
     * @param user User-object
     * @param ready ready check
     */
    public Client(String ip, int port, User user, boolean ready) {

        this.playWithFriendsFrame = new PlayWithFriendsFrame(this, user);
        this.ready = ready;
        this.ip = ip;
        this.port = port;
        this.user = user;

        try {
            socket = new Socket(ip, port);

            new ClientGo().start();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Inner class that handles the actual game thread and belonging functionality. Retrieves the questions through
     * streams. When the game is finished the highscore frame will show with the player's result.
     */
    private class ClientGoSolo extends Thread {

        private volatile boolean running = true;

        private int counterOfQuestion = 0;

        public void run() {

            try {

                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

                objectOutputStream.writeObject(user);
                objectOutputStream.flush();

                objectInputStream = new ObjectInputStream(socket.getInputStream());

                for (int i = 0; i < 10; i++) {

                    questions[i] = (Questions) objectInputStream.readObject();
                    gameface.resetButtons();

                }


                while (running) {

                    newQuestions(counterOfQuestion);

                    sleep(10000);

                    getAlternative(counterOfQuestion);
                    sleep(1500);

                    counterOfQuestion += 1;

                    System.out.println("Points:" + numOfPoints);

                    if (counterOfQuestion == 10) {
                        gameface.setVisible(false);
                        running = false;
                        System.out.println("Jag lever forfarande!!!");
                        userScore.add(user);
                        new HighscoreFrame(user, numOfPoints, correctalteratives, answerList, userScore);

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

    /**
     * Inner class that runs the client with belonging functionality for the play with friends function.
     * Ifa player has chosen the play with friends alternative the server will be notified and update the
     * list of players that have chosen to play with friends. When everyone has pushed the ready button
     * the game will start.
     */
    private class ClientGo extends Thread {



        private volatile boolean running = true;
        private boolean readingPoints = true;

        private int counterOfQuestion = 0;


        public void run() {

            while (waitingForPlayers) {
                try {

                    objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    objectInputStream = new ObjectInputStream(socket.getInputStream());

                    objectOutputStream.writeObject(user);
                    objectOutputStream.flush();

                    dataInputStream = new DataInputStream(socket.getInputStream());

                    while (true) {

                        String s = dataInputStream.readUTF();

                        if (s.equals("ny")) {
                            playWithFriendsFrame.clearList();

                        } else if (s.equals("start")) {
                            playWithFriendsFrame.close();
                            waitingForPlayers = false;
                            gameface = new Gameface();
                            break;
                            //new ClientPlayWithFriends().start();


                        } else {
                            playWithFriendsFrame.updateList(s);
                        }

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {

                numOfPlayers = Integer.parseInt(dataInputStream.readUTF());

                System.out.println(numOfPlayers);

                for (int i = 0; i < 10; i++) {

                    questions[i] = (Questions) objectInputStream.readObject();
                    gameface.resetButtons();

                }

                while (running) {

                    newQuestions(counterOfQuestion);
                    sleep(10000);

                    getAlternative(counterOfQuestion);
                    sleep(1500);

                    counterOfQuestion += 1;

                    System.out.println("Points:" + numOfPoints);

                    if (counterOfQuestion == 10) {
                        gameface.setVisible(false);
                        running = false;
                        user.setPoints(numOfPoints);
                        /*
                        objectOutputStream.writeObject(user);
                        objectOutputStream.flush();
                        System.out.println("1");

                        for(int i = 0; i < numOfPlayers; i++){

                            User u = (User)objectInputStream.readObject();
                            userScore.add(u);
                            System.out.println("2");
                        }
                        */
                        new HighscoreFrame(user, numOfPoints, correctalteratives, answerList, userScore);
                        break;

                    }
                }

                while (readingPoints){
                    User u = (User)objectInputStream.readObject();
                    userScore.add(u);
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

    /**
     * Checks for the players who are ready and sets bollean to true.
     * @throws IOException
     */
    public void setReady() throws IOException {
        this.ready = true;
        objectOutputStream.writeObject(user);
    }

    /**
     * Shows a new question.
     * @param counter
     */
    public void newQuestions(int counter) {

        gameface.setQuestion(questions[counter].getQuestion(), questions[counter].getAlternative1(), questions[counter].getAlternative2(), questions[counter].getAlternative3(), questions[counter].getAlternative4());
        correctalteratives[counter] = questions[counter].getCorrectAlternative();
    }

    /**
     * Gets the alternative the player has chosen. Adds points if the answer is right. Method also handles
     * the color changes depending on if its correct or incorrect answers.
     * @param counter
     */
    public void getAlternative(int counter) {

        System.out.println(gameface.getSelectedButton());
        System.out.println(questions[counter].getCorrectAlternative());

        answerList[counter] = gameface.getSelectedButton();

        if (gameface.getSelectedButton().equals(questions[counter].getCorrectAlternative())) {

            numOfPoints += 1;
            gameface.setColorToGreenJButton();
        } else {
            gameface.setColorToRedJButton();
        }
    }
}

