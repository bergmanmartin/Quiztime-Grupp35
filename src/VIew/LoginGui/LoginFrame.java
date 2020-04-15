package VIew.LoginGui;

import Model.Users.User;


import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class of the Loginframe with gui and functionality.
 * @version 1.0
 * @author martinbergman.
 */
public class LoginFrame extends JFrame {

    private LoginPanel loginPanel;
    private LoginController controller;



    /**
     * Method for initatilizing the window frame for the loginscreen
     */

   public LoginFrame(LoginController controller) {
        this.controller = controller;
        loginPanel = new LoginPanel(controller);


        setTitle("Welcome to QuizTime!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(loginPanel);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);


    }

}





