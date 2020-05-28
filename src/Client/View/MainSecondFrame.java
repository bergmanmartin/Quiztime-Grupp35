package Client.View;



import Client.Controller.Client;
import SharedResources.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Markus Gerdtsson, Marianne Mukanga, Martin Bergman och Erik Nielsen.
 * This class contains the gui for the LoginFrame.
 * @version 1.4
 */

public class MainSecondFrame {

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


    /**
     * Sets up and initializes the JFrame.
     * @param user The User object.
     */
    public MainSecondFrame(User user){

        this.user = user;

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
        frame.setBackground(Color.ORANGE);

    }

    /**
     * Sets up and initializes the topLeftPanel and adds gui components.
     */
    public void topLeft(){

        topleftPanel = new JPanel();

        JLabel username = new JLabel();
        JPanel imagePanel = new JPanel();
        JLabel imageLBL = new JLabel();

        topleftPanel.setLayout(new GridBagLayout());
        //topleftPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        topleftPanel.setBackground(Color.ORANGE);

        //username.setSize(100,75);
        username.setText(user.getUsername());
        imageLBL.setIcon(user.getUserPicture());
        imagePanel.add(imageLBL);
        imagePanel.setBackground(Color.ORANGE);

        topleftPanel.add(imagePanel);
        topleftPanel.add(username);

        frame.add(topleftPanel);
    }

    /**
     * Sets up and initializes the topRightPanel and adds gui components.
     */
    public void topRight() {
        toprightPanel = new JPanel();


        toprightPanel.setBorder(BorderFactory.createTitledBorder(" Contact Register "));

        toprightPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

        toprightPanel.setBackground(Color.ORANGE);

        JList list = new JList();

        Font font = new Font("Courier New", Font.PLAIN, 10);
        list.setFont(font);

        list.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        list.setPreferredSize(new Dimension(300, 200));

        //addListner();
        toprightPanel.add(list);

        frame.add(toprightPanel);
    }

    /**
     * Sets up and initializes the bottomLeftPanel and adds gui components.
     * Starts and initializes Client connection to server.
     */
    public void bottomLeft() {

        bottomLeftPanel = new JPanel();
        bottomLeftPanel.setLayout(new GridLayout(2,2));
        //bottomLeftPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        bottomLeftPanel.setBackground(Color.ORANGE);

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
                    Client Client = new Client("127.0.0.1", 2343, user);

                } else if (playWithFriendsButton.isSelected()) {
                    frame.setVisible(false);
                    Client Client = new Client("127.0.0.1", 2343, user, false);
                }
            }
        });

        frame.add(bottomLeftPanel);
    }

    /**
     * Sets up and initializes the bottomRightPanel and adds gui components.
     */
    public void bottomRight() {

        bottomRightPanel = new JPanel();
        bottomRightPanel.setBackground(Color.orange);
        frame.add(bottomRightPanel);
    }

}
