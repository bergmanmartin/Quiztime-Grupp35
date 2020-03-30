package LoginGui;

import javax.swing.*;
import java.awt.*;

/**
 * Class of the Loginframe with gui and functionality.
 * @version 1.0
 * @author martinbergman.
 */
public class LoginFrame extends JPanel {
    private JButton loginBtn;

    private JTextField loginTf;

    private JLabel welcomeLbl;
    private JLabel usernameLbl;

    private JPanel loginPnl;

    private JFrame loginFrame;



    public LoginFrame() {
        initWinow();
        createComponents();



    }

    /**
     * Method for initatilizing the window frame for the loginscreen
     */
    public void initWinow() {
        loginFrame = new JFrame("Quiztime");
        loginFrame.setVisible(true);
        loginFrame.setLayout(new GridLayout(1, 1));
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setPreferredSize(new Dimension(700, 500));
        loginFrame.pack();


    }

    /**
     * Method for creating the components in the login window.
     */
    public void createComponents() {
        loginBtn = new JButton("Login");
        welcomeLbl = new JLabel("Welcome to Quiztime!");
        usernameLbl = new JLabel("Enter Username");
        loginTf = new JTextField();
        loginPnl = new JPanel();


        loginPnl.setLayout(new GridLayout(4, 1));
        loginPnl.setSize(new Dimension(600, 400));

       loginPnl.add(welcomeLbl);
       loginPnl.add(usernameLbl);
       loginPnl.add(loginTf);
       loginPnl.add(loginBtn);
       loginBtn.addActionListener(e -> System.out.println("Hej"));

       loginFrame.add(loginPnl);



    }


    public static void main(String[] args) {
     LoginFrame frame = new LoginFrame();
    }


}
