package VIew.GameFrame;

/**
 * This class represents the game and its functions. Handles the selcted alternatives and other progression
 * throughout the game.
 * @author Markus Gerdtsson
 * @version 1.0
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gameface extends JFrame {
    //JPanels
    private JPanel northPanel = new JPanel();
    private JPanel southPanel = new JPanel();
    private JPanel centerPanel = new JPanel();
    //JLabels
    private JLabel question = new JLabel();
    //JButtons
    private ButtonGroup buttonGroup = new ButtonGroup();

    private JButton firstAlternative = new JButton();
    private JButton secondAlternative = new JButton();
    private JButton thirdAlternative = new JButton();
    private JButton forthAlternative = new JButton();

    private JButton selectedButton;

    //JProgressbar
    private JProgressBar jProgressBar = new JProgressBar();

    /**
     * Initializes the frame with the game.
     */
    public Gameface() {
        //Setting up the QuestionsGUI JFrame
        this.setTitle("Questions!");
        setSize(600, 400);
        setLocation(900, 450);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setNorthPanel();
        setCenterPanel();
        setSouthPanel();
        setActionListeners();

        firstAlternative.setFocusable(false);
        secondAlternative.setFocusable(false);
        thirdAlternative.setFocusable(false);
        forthAlternative.setFocusable(false);

        buttonGroup.add(firstAlternative);
        buttonGroup.add(secondAlternative);
        buttonGroup.add(thirdAlternative);
        buttonGroup.add(forthAlternative);

    }

    /**
     * Creates the panel where the question is shown.
     */
    public void setNorthPanel() {
        northPanel.setSize(600, 150);
        question.setFont(question.getFont().deriveFont(16.0f));
        northPanel.add(question);
        northPanel.setBackground(Color.orange);
        add(northPanel, BorderLayout.NORTH);
    }

    /***
     * Creates the part of the frame where the tim countdown is shown.
     */
    public void setCenterPanel() {
        centerPanel.setSize(new Dimension(400, 50));
        jProgressBar.setValue(0);
        jProgressBar.setStringPainted(true);
        jProgressBar.setPreferredSize(new Dimension(400, 50));
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.add(jProgressBar);
        centerPanel.setBackground(Color.orange);
        add(centerPanel, BorderLayout.CENTER);
    }

    /**
     * Creates the part where all the answer alternatives are shown.
     */
    public void setSouthPanel() {
        southPanel.setSize(new Dimension(400, 200));
        southPanel.setLayout(new GridLayout(2,2));
        southPanel.setBackground(Color.red);

        firstAlternative.setPreferredSize(new Dimension(150, 100));
        secondAlternative.setPreferredSize(new Dimension(150, 100));
        thirdAlternative.setPreferredSize(new Dimension(150, 100));
        forthAlternative.setPreferredSize(new Dimension(150, 100));

        southPanel.add(firstAlternative);
        southPanel.add(secondAlternative);
        southPanel.add(thirdAlternative);
        southPanel.add(forthAlternative);

        add(southPanel, BorderLayout.SOUTH);

    }

    /**
     * The thread that represents the countdown bar and finishes after 10 seconds.
     * @author Markus Gerdtsson
     * @version 1.0
     */
    private class FillThread extends Thread {

        /**
         * The threads run method that will fill the countdown bar every second.
         */
        public void run() {
                int i = 0;
                try {
                    while (i <= 100) {
                        // fill the menu bar
                        jProgressBar.setValue(i);

                        // delay the thread
                        Thread.sleep(1000);
                        i += 10;

                    }
                } catch (Exception e) {
            }

        }

    }

    /**
     * Sets the question with answer alternatives and functions for the buttons and starts a new Thread.
     * @param question1     Current question
     * @param alternative1
     * @param alternative2
     * @param alternative3
     * @param alternative4
     */
    public void setQuestion(String question1, String alternative1, String alternative2, String alternative3, String alternative4){

        if (selectedButton != null){
            removeOldSelectedButton();
            resetButtons();
        }
        selectedButton = new JButton();


        question.setText(question1);

        firstAlternative.setText(alternative1);
        secondAlternative.setText(alternative2);
        thirdAlternative.setText(alternative3);
        forthAlternative.setText(alternative4);



        FillThread fillThread = new FillThread();
        fillThread.start();
    }

    /**
     * Listeners for all the buttons and belonging functions.
     */
    public void setActionListeners(){

        firstAlternative.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedButton != null){
                    removeOldSelectedButton();
                }
                selectedButton = firstAlternative;
                setSelectedButton(selectedButton);
                firstAlternative.setSelected(true);
            }

        });

        secondAlternative.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedButton != null){
                    removeOldSelectedButton();
                }
                selectedButton = secondAlternative;
                setSelectedButton(selectedButton);
                secondAlternative.setSelected(true);
            }

        });

        thirdAlternative.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedButton != null){
                    removeOldSelectedButton();
                }
                selectedButton = thirdAlternative;
                setSelectedButton(selectedButton);
                thirdAlternative.setSelected(true);
            }

        });

        forthAlternative.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedButton != null){
                  removeOldSelectedButton();
                }
                selectedButton = forthAlternative;
                setSelectedButton(selectedButton);
                forthAlternative.setSelected(true);
            }

        });





    }

    /**
     * Deselects the button for the next question.
     */
    public void removeOldSelectedButton(){

        selectedButton.setBackground(null);
        selectedButton.setSelected(false);
    }

    /**
     * Sets the color of the selected button to green if it is the correct answer.
     * @param selectedButton
     */
    public void setSelectedButton(JButton selectedButton){

        selectedButton.setBackground(Color.yellow);
    }



    /**
     * Returnns the text in the selected button.
     * @return
     */
    public String getSelectedButton(){
        return selectedButton.getText();
    }

    /**
     * Resets all the Buttons so they are deselcted.
     */
    public void resetButtons() {
        firstAlternative.setSelected(false);
        secondAlternative.setSelected(false);
        thirdAlternative.setSelected(false);
        forthAlternative.setSelected(false);

    }

    public void setColorToGreenJButton(){
        selectedButton.setBackground(Color.green);
    }

    public void setColorToRedJButton(){
        selectedButton.setBackground(Color.RED);

    }
}

