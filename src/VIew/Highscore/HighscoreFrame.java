package VIew.Highscore;

import Model.Users.User;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;


/**
 * Class that is the main page of the program with options to start the game
 * and shows highscore list and connected users.
 * @author Markus Gerdtsson
 * @author Marianne Mukanga
 * @version 1.0
 */
public class HighscoreFrame {
    private User user;

    private JFrame frame;
    private JPanel leftPanel;
    private JPanel rightPanel;

    private int numberOfPoints;
    private ImageIcon userPicture;




    /**
     * Initializes the frame.
     * @param user
     */
    public HighscoreFrame(User user,int numberOfPoints){

        this.numberOfPoints = numberOfPoints;
        this.user = user;

        frame = new JFrame();
        frame.setBounds(0, 0, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(1,2));
        frame.setTitle("QuizTime - Highscore");
        //Fill JFrame
        Left();
        Right();

        frame.setVisible(true);
        frame.setResizable(false);            // Prevent user from change size
        frame.setLocationRelativeTo(null);    // Start middle screen

    }



    /**
     * Creates the topleft part of the panel with belonging components.
     */
    public void Left(){
        leftPanel = new JPanel();
        leftPanel.setBackground(Color.ORANGE);

        JPanel southPanel = new JPanel();
        southPanel.setPreferredSize(new Dimension(300,301));
        southPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        southPanel.setBackground(Color.ORANGE);

        JPanel northPanel = new JPanel();
        northPanel.setPreferredSize(new Dimension(300,60));
        northPanel.setLayout(new GridLayout(1,2));

        //User
        JPanel northLeftPanel = new JPanel();
        northLeftPanel.setPreferredSize(new Dimension(150,60));
        //northLeftPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        TitledBorder titledBorder1 = BorderFactory.createTitledBorder("Player");
        titledBorder1.setTitleColor(Color.red);
        northLeftPanel.setBorder(titledBorder1);
        northLeftPanel.setLayout(new GridLayout(1,2));
        northLeftPanel.setBackground(Color.ORANGE);

        JLabel imageLabel = new JLabel();
        Image image = user.getUserPicture().getImage();
        Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        userPicture = new ImageIcon(newimg);




        JLabel userNameLabel = new JLabel();
        userNameLabel.setPreferredSize(new Dimension(60,40));
        userNameLabel.setText(user.getUsername());

        northLeftPanel.add(imageLabel, BorderLayout.WEST);
        imageLabel.setIcon(userPicture);
        northLeftPanel.add(userNameLabel, BorderLayout.EAST);

        //Score
        JPanel northRightPanel = new JPanel();
        northRightPanel.setPreferredSize(new Dimension(150,60));
        northRightPanel.setBorder(BorderFactory.createLineBorder(Color.red));
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Points");
        titledBorder.setTitleColor(Color.red);
        northRightPanel.setBorder(titledBorder);
        northRightPanel.setBackground(Color.ORANGE);

        JLabel numberOfPointsLabel = new JLabel();
        numberOfPointsLabel.setText(String.valueOf(numberOfPoints));
        northRightPanel.add(numberOfPointsLabel);
        numberOfPointsLabel.setBackground(Color.ORANGE);

        northPanel.add(northLeftPanel);
        northPanel.add(northRightPanel);



        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(southPanel, BorderLayout.SOUTH);
        leftPanel.add(northPanel,BorderLayout.NORTH);

        frame.add(leftPanel);
    }


    /**
     * Creates the topright oart with belonging components.
     */
    public void Right() {
        rightPanel = new JPanel();

        JPanel northPanel = new JPanel();
        northPanel.setPreferredSize(new Dimension(300, 311));
        northPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        JPanel southPanel = new JPanel();
        southPanel.setPreferredSize(new Dimension(300,50));
        southPanel.setBorder(BorderFactory.createLineBorder(Color.black));


        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(northPanel, BorderLayout.NORTH);
        rightPanel.add(southPanel, BorderLayout.SOUTH);


        JList list = new JList();

        Font font = new Font("Courier New", Font.PLAIN, 10);
        list.setFont(font);

        list.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        list.setPreferredSize(new Dimension(300, 200));

        //addListner();
        rightPanel.add(list);

        frame.add(rightPanel);
    }



}