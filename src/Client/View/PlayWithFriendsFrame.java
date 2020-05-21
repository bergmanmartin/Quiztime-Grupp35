package Client.View;



import Client.Controller.Client;
import SharedResources.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @Created 11/02/2020
 * @project P1
 * @Markus Gerdtsson
 */
public class PlayWithFriendsFrame {

    private Client client;

    private SharedResources.User user;

    private JFrame frame;

    private JPanel topleftPanel;
    private JPanel toprightPanel;
    private JPanel bottomLeftPanel;
    private JPanel bottomRightPanel;

    private JButton startButton;
    private JButton quitButton;

    private JRadioButton playAloneButton;
    private JRadioButton playWithFriendsButton;

    private JList list;

    private Thread thread;

    private ArrayList<User> users;

    DefaultListModel<String> listModel = new DefaultListModel<>();


    public PlayWithFriendsFrame(Client client, SharedResources.User user){

        this.user = user;
        this.client = client;

        frame = new JFrame();
        frame.setBounds(0, 0, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(2,2));
        frame.setTitle("QuizTime");
        //Fill JFrame
        topLeft();
        topRight();
        bottomLeft();
        bottomRight();

        frame.setVisible(true);
        frame.setResizable(false);            // Prevent user from change size
        frame.setLocationRelativeTo(null);    // Start middle screen



    }

    public void topLeft(){

        topleftPanel = new JPanel();

        JLabel username = new JLabel();
        JPanel imagePanel = new JPanel();
        JLabel imageLBL = new JLabel();

        topleftPanel.setLayout(new GridBagLayout());
        topleftPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        username.setText(user.getUsername());
        imageLBL.setIcon(user.getUserPicture());
        imagePanel.add(imageLBL);

        topleftPanel.add(imagePanel);
        topleftPanel.add(username);

        frame.add(topleftPanel);
    }

    public void topRight() {
        toprightPanel = new JPanel();


        toprightPanel.setBorder(BorderFactory.createTitledBorder(" Contact Register "));

        toprightPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

        list = new JList<>(listModel);


        Font font = new Font("Courier New", Font.PLAIN, 10);
        list.setFont(font);

        list.setPreferredSize(new Dimension(300, 200));

        toprightPanel.add(list);

        frame.add(toprightPanel);
    }

    public void bottomLeft() {

        bottomLeftPanel = new JPanel();
        bottomLeftPanel.setLayout(new GridLayout(2,2));
        bottomLeftPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        //RadioButtons + button group
        playAloneButton = new JRadioButton("Play Alone");
        playAloneButton.setPreferredSize(new Dimension(200, 60));
        bottomLeftPanel.add(playAloneButton);

        playWithFriendsButton = new JRadioButton("Play With Friends");
        playWithFriendsButton.setPreferredSize(new Dimension(200, 60));
        bottomLeftPanel.add(playWithFriendsButton);

        ButtonGroup btngroup = new ButtonGroup();

        //Start and quit buttons
        startButton = new JButton("Start Game");
        startButton.setPreferredSize(new Dimension(200, 60));
        startButton.setForeground(Color.GREEN);
        bottomLeftPanel.add(startButton);

        quitButton = new JButton("Quit");
        quitButton.setPreferredSize(new Dimension(200, 60));
        quitButton.setForeground(Color.RED);
        bottomLeftPanel.add(quitButton);

        btngroup.add(playAloneButton);
        btngroup.add(playWithFriendsButton);

        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                //Hämtar vilken knapp som är itryckt och sen utför.
                if(playAloneButton.isSelected()) {
                    frame.setVisible(false);
                    //try {
                      //  Server server = new Server(2343);
                   // } catch (IOException ex) {
                     //   ex.printStackTrace();
                   // }
                    
                }
            }
        });

        frame.add(bottomLeftPanel);
    }

    public void bottomRight() {

        bottomRightPanel = new JPanel();

        frame.add(bottomRightPanel);
    }


    public void updateList(String s){

        listModel.addElement(s);
    }

    public void clearList(){
        listModel.clear();
    }
/*
    public void addElement(LinkedList s){
        listModel.clear();
        for (Object o : s) {
            listModel.add(o)
        }
        listModel.add(s.ge)
        List<LinkedList> contacts = s;
        JList contactsView = new JList(contacts.toArray());
        }

    }*/

}