package Client.View;



import Client.Controller.Client;
import SharedResources.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


/**
 * @project Quiztime
 * @author Martin Bergman, Marianne Mukagana, Markus Gerdtsson, Erik Nielsen.
 * @version 1.4
 */
public class PlayWithFriendsFrame {

    private Client client;

    private User user;

    private JFrame frame;

    private JPanel topleftPanel;
    private JPanel toprightPanel;
    private JPanel bottomLeftPanel;
    private JPanel bottomRightPanel;

    private JButton readyButton;
    private JButton quitButton;

    private JList list;

    private DefaultListModel<String> listModel = new DefaultListModel<>();

    public PlayWithFriendsFrame(Client client, SharedResources.User user){

        this.user = user;
        this.client = client;

        frame = new JFrame();
        frame.setBounds(0, 0, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(2,2));
        frame.setTitle("QuizTime");

        topLeft();
        topRight();
        bottomLeft();
        bottomRight();

        frame.setVisible(true);
        frame.setResizable(false);            // Prevent user from change size
        frame.setLocationRelativeTo(null);    // Start middle screen
    }

    /**
     * Constructs the top left part of the frame.
     */
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

    /**
     * Constructs the top right part of the frame.
     */
    public void topRight() {
        toprightPanel = new JPanel();

        toprightPanel.setBorder(BorderFactory.createTitledBorder(" Contact Register "));

        toprightPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

        list = new JList<>(listModel);

        Font font = new Font("SansSerif Bold", Font.BOLD, 20);
        list.setFont(font);

        list.setPreferredSize(new Dimension(280, 200));

        toprightPanel.add(list);

        frame.add(toprightPanel);
    }

    /**
     * Construcs the components of the bottom left part of the frame.
     * Method where the players can select if they are ready.
     */
    public void bottomLeft() {

        bottomLeftPanel = new JPanel();
        bottomLeftPanel.setLayout(new GridLayout(2,2));
        bottomLeftPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        //Start and quit buttons
        readyButton = new JButton("Ready");
        readyButton.setPreferredSize(new Dimension(200, 60));
        readyButton.setForeground(Color.GREEN);
        bottomLeftPanel.add(readyButton);

        quitButton = new JButton("Quit");
        quitButton.setPreferredSize(new Dimension(200, 60));
        quitButton.setForeground(Color.RED);
        bottomLeftPanel.add(quitButton);

        readyButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                user.setReadyTrue();
                try {
                    client.setReady();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });
        frame.add(bottomLeftPanel);
    }

    /**
     * Constructs the filler panel for the bottom right part of the frame
     */
    public void bottomRight() {
        bottomRightPanel = new JPanel();
        frame.add(bottomRightPanel);
    }

    /**
     * Updates the userlist.
     * @param s
     */
    public void updateList(String s){
        listModel.addElement(s);
    }

    /**
     * Clears the userlist.
     */
    public void clearList() {
        listModel.clear();
    }

    /**
     * Closes the frame
     */
    public void close(){
        frame.setVisible(false);
    }
}