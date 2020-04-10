package VIew.GameFrame;

/**
 * @Created 11/02/2020
 * @project P1
 * @Markus Gerdtsson
 */
import javax.swing.*;
import java.awt.*;

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
    //JProgressbar
    private JProgressBar jProgressBar = new JProgressBar();

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
        FillThread fillThread = new FillThread();
        fillThread.start();
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
    private class FillThread extends Thread {


        public void run() {
            int i = 0;
            try {
                while (i <= 100) {
                    // fill the menu bar
                    jProgressBar.setValue(i + 10);

                    // delay the thread
                    Thread.sleep(1000);
                    i += 5;
                }
            } catch (Exception e) {
            }



        }
    }

    public void setQuestion(String question1, String alternative1, String alternative2, String alternative3, String alternative4){
        question.setText(question1);
        firstAlternative.setText(alternative1);
        secondAlternative.setText(alternative2);
        thirdAlternative.setText(alternative3);
        forthAlternative.setText(alternative4);
    }

}
