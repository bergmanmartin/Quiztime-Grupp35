package StartPageGUI;

import VIew.LoginGui.LoginController;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class LayoutExample extends JFrame {

    //private JTextField tField;
    private JTextArea taUserPicture;
    private LoginController controller;

    private JTextArea tArea1;
    private JTextArea tArea2;
    private JTextArea tArea3;
    private JTextArea tArea4;

    private JRadioButton leftRButton;
    private JRadioButton rightRButton;

    private GridBagConstraints gbc;

    private Random random;
    private JFrame frame;

    public LayoutExample(LoginController controller) {
        gbc = new GridBagConstraints();
        random = new Random();
        this.controller = controller;
    }

    private void displayGUI() {
        frame = new JFrame("QuiZtime Start");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel contentPane = getPanel();
        contentPane.setLayout(new BorderLayout(5, 5));
        //JLabel topLabel = new JLabel("PAGE_START Label", JLabel.CENTER);
        //contentPane.add(topLabel, BorderLayout.PAGE_START);

        /**Den här delen ska till MainPanel. Här delas hela sidan upp i två delar.*/
        JPanel basePanel = getPanel();
        basePanel.setLayout(new GridLayout(1, 2));

        JPanel leftPanel = getPanel();
        leftPanel.setLayout(new GridBagLayout());
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel fieldLabel = new JLabel("User Name");
        taUserPicture = new JTextArea();

        /** Här ska bild och användarnamnet vara */
        JPanel topPanel = getPanel();
        topPanel.setLayout(new GridBagLayout());
        topPanel.add(fieldLabel, getConstraints(1, 0, 2, 1, GridBagConstraints.HORIZONTAL, 0.3f, 0.1f));
        topPanel.add(taUserPicture, getConstraints(0, 0, 1, 1, GridBagConstraints.HORIZONTAL, 0.7f, 1.0f));
        leftPanel.add(topPanel, getConstraints(0, 0, 1, 1, GridBagConstraints.BOTH, 1.0f, 0.40f));

        JPanel middlePanel = getPanel();
        middlePanel.setLayout(new BorderLayout(5, 5));
        tArea1 = new JTextArea(10, 10); /** Här ska JRadioKnapparna vara! */
        middlePanel.add(tArea1, BorderLayout.CENTER);
        JPanel radioPanel = getPanel();
        radioPanel.setLayout(new GridLayout(1, 2, 5, 5));
        leftRButton = new JRadioButton("Utmana dig själv!", false);
        rightRButton = new JRadioButton("Utmana en kompis!", false);
        radioPanel.add(leftRButton);
        radioPanel.add(rightRButton);
        middlePanel.add(radioPanel, BorderLayout.PAGE_END);
        leftPanel.add(middlePanel, getConstraints(0, 1, 1, 2, GridBagConstraints.BOTH, 1.0f, 0.60f));
        basePanel.add(leftPanel);

        JPanel rightPanel = getPanel();
        rightPanel.setLayout(new GridLayout(2, 1, 10, 10));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tArea2 = new JTextArea(10, 10);
        tArea3 = new JTextArea(10, 10);
        //tArea4 = new JTextArea(10, 10);
        rightPanel.add(tArea2);
        rightPanel.add(tArea3);
        //rightPanel.add(tArea4);
        basePanel.add(rightPanel);
        contentPane.add(basePanel, BorderLayout.CENTER);

        JLabel bottomLabel = new JLabel("PAGE_END Label", JLabel.CENTER);
        contentPane.add(bottomLabel, BorderLayout.PAGE_END);

        frame.setContentPane(contentPane);
        frame.pack();
        frame.setLocationByPlatform(true);
        //frame.setVisible(false);
    }

    private JPanel getPanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(true);
        panel.setBackground(new Color(random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat()));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        return panel;
    }

    private GridBagConstraints getConstraints(
            int x, int y, int w, int h, int fillValue
            , float weightx, float weighty) {
        //GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.fill = fillValue;
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        gbc.weightx = weightx;
        gbc.weighty = weighty;

        return gbc;
    }

    public JFrame getStartPage() {
        return frame;
    }

    public static void main(String... args) {
        LoginController controller = new LoginController();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                new LayoutExample(controller).displayGUI();
            }
        };
        EventQueue.invokeLater(runnable);
    }
}

