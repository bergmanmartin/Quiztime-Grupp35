package SecondFrame;

import Controller.Client;
import Controller.Server;
import Model.Users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * @Created 11/02/2020
 * @project P1
 * @Markus Gerdtsson
 */
public class MainSecondFrame {

    private User user;

    private JFrame frame;
    private JPanel topleftPanel;
    private JPanel toprightPanel;
    private JPanel bottomLeftPanel;
    private JPanel bottomRightPanel;

    private JButton startButton;
    private JButton quitButton;
    private JRadioButton playAloneButton;
    private JRadioButton playWithFriendsButton;


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
        bottomRight();
        bottomLeft();
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

        username.setText(user.getUsername());
        imageLBL.setIcon(user.getUserPicture());
        imagePanel.add(imageLBL);

        topleftPanel.add(imagePanel);
        topleftPanel.add(username);

        frame.add(topleftPanel);
    }

    public void topRight(){
        toprightPanel = new JPanel();

        frame.add(toprightPanel);
    }

    public void bottomLeft(){
        bottomLeftPanel = new JPanel();
        playAloneButton = new JRadioButton("Play Alone");
        playWithFriendsButton = new JRadioButton("Play With Friends");
        startButton = new JButton("Start Game");
        ButtonGroup btngroup = new ButtonGroup();
        btngroup.add(playAloneButton);
        btngroup.add(playWithFriendsButton);

        playAloneButton.setPreferredSize(new Dimension(200,60));
        playWithFriendsButton.setPreferredSize(new Dimension(200,60));
        startButton.setPreferredSize(new Dimension(200,60));

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Hämtar vilken knapp som är itryckt och sen utför.

                if(playAloneButton.isSelected()){
                    frame.setVisible(false);
                    try {
                        Server server = new Server(2343);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    Client Client = new Client("127.0.0.1", 2343);
                }
            }
        });

        frame.add(bottomLeftPanel);

    }

    public void bottomRight(){
        bottomRightPanel = new JPanel();

        frame.add(bottomRightPanel);
    }

}
