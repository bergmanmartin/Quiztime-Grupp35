package Client.View;

/**
 * Class that represents the game frame with all the questions.
 * @version 1.0
 * @author Martin Bergman, Markus Gerdtsson, Erik Nielsen, Marianne Mukanga
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
    private JButton firstAlternative = new JButton();
    private JButton secondAlternative = new JButton();
    private JButton thirdAlternative = new JButton();
    private JButton forthAlternative = new JButton();

    private JButton selectedButton;

    //JProgressbar
    private JProgressBar jProgressBar = new JProgressBar();

    /**
     * Constructs the game frame
     */
    public Gameface() {
        //Setting up the QuestionsGUI JFrame
        this.setTitle("Questions!");
        setSize(600, 400);
        setLocation(900, 450);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setNorthPanel();
        setCenterPanel();
        setSouthPanel();
        setActionListeners();

    }

    public void setNorthPanel() {
        northPanel.setSize(600, 150);
        question.setFont(question.getFont().deriveFont(16.0f));
        northPanel.add(question);
        add(northPanel, BorderLayout.NORTH);
    }

    public void setCenterPanel() {
        centerPanel.setSize(new Dimension(400, 50));
        jProgressBar.setValue(0);
        jProgressBar.setStringPainted(true);
        jProgressBar.setPreferredSize(new Dimension(400, 50));
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.add(jProgressBar);
        add(centerPanel, BorderLayout.CENTER);
    }

    public void setSouthPanel() {
        southPanel.setSize(new Dimension(400, 200));
        southPanel.setLayout(new GridLayout(2,2));
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
     * Inner class that limits the time to 10 seconds on the progressbar
     */
    private class FillThread extends Thread {


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
     * Sets the question and the answers on the buttons.
     * @param question1
     * @param alternative1
     * @param alternative2
     * @param alternative3
     * @param alternative4
     */
    public void setQuestion(String question1, String alternative1, String alternative2, String alternative3, String alternative4){
        question.setText(question1);
        firstAlternative.setText(alternative1);
        secondAlternative.setText(alternative2);
        thirdAlternative.setText(alternative3);
        forthAlternative.setText(alternative4);

        if (selectedButton != null){
            removeOldSelectedButton();
        }

        FillThread fillThread = new FillThread();
        fillThread.start();
    }

    /**
     * Sets listeners for the buttons
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
            }

        });



    }

    /**
     * Resets the button visibility
     */
    public void removeOldSelectedButton(){

        selectedButton.setBackground(null);

    }

    /**
     * Sets the right answer to the green background
     * @param selectedButton
     */
    public void setSelectedButton(JButton selectedButton){

        selectedButton.setBackground(Color.GREEN);

    }

    /**
     * Returns the answer of the button
     * @return
     */
    public String getSelectedButton(){
        return selectedButton.getText();
    }

}

