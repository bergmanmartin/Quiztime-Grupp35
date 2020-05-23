package Client.View;
import SharedResources.User;

import javax.sql.rowset.serial.SerialJavaObject;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


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
    private String[] correctAlternatives;
    private String[] questionList = new String[]{"Question 1", "Question 2","Question 3","Question 4","Question 5","Question 6","Question 7","Question 8","Question 9","Question 10"};
    private String[] answerList;
    private LinkedList<User> userScoreList;

    DefaultListModel<String> model = new DefaultListModel<>();


    /**
     * Initializes the frame.
     * @param user

     */

    public HighscoreFrame(User user, int numberOfPoints, String[] correctAlretnatives, String[] answerList, LinkedList userScore){

        this.numberOfPoints = numberOfPoints;
        this.user = user;
        this.correctAlternatives = correctAlretnatives;
        this.answerList = answerList;
        this.userScoreList = userScore;


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

        southPanel.setLayout(new BorderLayout());

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

        JTextField lblquestion = new JTextField("Questions:");
        JTextField lblcorrect = new JTextField("Correct:");
        JTextField lblyourAnswer = new JTextField("Answer:");

        JPanel southListPanel = new JPanel();
        JPanel southHeaderPanel = new JPanel();
        southHeaderPanel.setLayout(new GridLayout(1,3));
        southHeaderPanel.setPreferredSize(new Dimension(300,20));

        southListPanel.setLayout(new GridLayout(1,3));
        JList qlist = new JList(questionList);
        JList list = new JList(correctAlternatives);
        JList aList = new JList(answerList);

        lblquestion.setPreferredSize(new Dimension(100,40));
        lblcorrect.setPreferredSize(new Dimension(100,40));
        lblyourAnswer.setPreferredSize(new Dimension(100,40));

        Font font = new Font("Areal", Font.BOLD,16);

        list.setBackground(null);
        qlist.setBackground(null);
        aList.setBackground(null);
        list.setFont(font);
        qlist.setFont(font);
        aList.setFont(font);

        lblquestion.setBackground(null);
        lblcorrect.setBackground(null);
        lblyourAnswer.setBackground(null);
        lblcorrect.setFont(font);
        lblquestion.setFont(font);
        lblyourAnswer.setFont(font);

        lblcorrect.setEditable(false);
        lblquestion.setEditable(false);
        lblyourAnswer.setEditable(false);


        southHeaderPanel.add(lblquestion);
        southHeaderPanel.add(lblcorrect);
        southHeaderPanel.add(lblyourAnswer);
        southListPanel.add(qlist);
        southListPanel.add(list);
        southListPanel.add(aList);

        southPanel.add(southHeaderPanel, BorderLayout.NORTH);
        southPanel.add(southListPanel,BorderLayout.SOUTH);

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
        //northPanel.setLayout(new GridLayout(1,2));
        northPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        TitledBorder titledBorder = BorderFactory.createTitledBorder("High score");
        northPanel.setBorder(titledBorder);

        JPanel southPanel = new JPanel();
        southPanel.setPreferredSize(new Dimension(300,50));
        southPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        southPanel.setLayout(new GridLayout(1,2));
        JButton playButton = new JButton("Play again");
        playButton.setSelected(false);
        JButton quitButton = new JButton("Quit game");
        playButton.setSelected(false);



        southPanel.add(playButton);
        southPanel.add(quitButton);



        JList<String> list = new JList<>( model );

        //addElements();
        model.addElement(user.getUsername() + "Score: " + numberOfPoints);

        northPanel.add(list,BorderLayout.EAST);

        JLabel imageLabel = new JLabel();
        Image image = user.getUserPicture().getImage();
        Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        userPicture = new ImageIcon(newimg);
        northPanel.add(imageLabel,BorderLayout.WEST);

        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(northPanel, BorderLayout.NORTH);
        rightPanel.add(southPanel, BorderLayout.SOUTH);


        frame.add(rightPanel);
    }
    /*public void addElements(){
        for (User user1 : userScoreList) {
            model.addElement(user1.getUsername() + "Score: " + user1.getPoints());
        }
    }*/






}