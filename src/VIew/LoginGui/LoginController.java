package VIew.LoginGui;

import javax.swing.*;
import java.util.ArrayList;

public class LoginController {
    private LoginFrame loginFrame;
    private User user;
    private ArrayList<String> userlist = new ArrayList<String>();

    public LoginController (){
        loginFrame = new LoginFrame(this);

    }


    public void invalidUsername() {
        if (loginFrame.getUsernname().equals("")) {
            JOptionPane.showMessageDialog(null, "Invalid username");
        }
    }

    public void addUserToList() {
        for (String user : userlist) {
            userlist.add(loginFrame.getUsernname());
        }
    }

    public void usernameAlreadyExists() {

    }



}
