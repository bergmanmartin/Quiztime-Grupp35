package VIew.Highscore;

import Model.Users.User;

import javax.sql.rowset.serial.SerialJavaObject;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.HashMap;
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



    private DefaultListModel listModel;






    /**
     * Initializes the frame.
     * @param user

     */

    public HighscoreFrame(User user,int numberOfPoints, String[] correctAlretnatives, String[] answerList){

        this.numberOfPoints = numberOfPoints;
        this.user = user;
        this.correctAlternatives = correctAlretnatives;
        this.answerList = answerList;


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

        JPanel southPanel = new JPanel();
        southPanel.setPreferredSize(new Dimension(300,301));
        southPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        southPanel.setLayout(new GridLayout(1,3));

        JPanel northPanel = new JPanel();
        northPanel.setPreferredSize(new Dimension(300,60));
        northPanel.setLayout(new GridLayout(1,2));

        JPanel northLeftPanel = new JPanel();
        northLeftPanel.setPreferredSize(new Dimension(150,60));
        northLeftPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        northLeftPanel.setLayout(new GridLayout(1,2));

        JLabel imageLabel = new JLabel();
        Image image = user.getUserPicture().getImage();
        Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        userPicture = new ImageIcon(newimg);

        JList qlist = new JList(questionList);
        JList list = new JList(correctAlternatives);
        JList aList = new JList(answerList);

        Font font = new Font("Areal", Font.BOLD,16);

        list.setBackground(null);
        qlist.setBackground(null);
        aList.setBackground(null);
        list.setFont(font);
        qlist.setFont(font);
        aList.setFont(font);

        southPanel.add(qlist);
        southPanel.add(list);
        southPanel.add(aList);

        JLabel userNameLabel = new JLabel();
        userNameLabel.setPreferredSize(new Dimension(60,40));
        userNameLabel.setText(user.getUsername());

        northLeftPanel.add(imageLabel, BorderLayout.WEST);
        imageLabel.setIcon(userPicture);
        northLeftPanel.add(userNameLabel, BorderLayout.EAST);


        JPanel northRightPanel = new JPanel();
        northRightPanel.setPreferredSize(new Dimension(150,60));
        northRightPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Points");
        northRightPanel.setBorder(titledBorder);

        JLabel numberOfPointsLabel = new JLabel();
        numberOfPointsLabel.setText(String.valueOf(numberOfPoints));
        northRightPanel.add(numberOfPointsLabel);

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


        DefaultListModel<String> model = new DefaultListModel<>();
        JList<String> list = new JList<>( model );
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






}