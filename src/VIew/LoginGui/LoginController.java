package VIew.LoginGui;

import Model.Users.User;

import javax.swing.*;
import java.util.ArrayList;

public class LoginController {
    private LoginFrame loginFrame;
    private User user;

    public LoginController (){
        loginFrame = new LoginFrame(this);

    }


        if (loginFrame.getUsernname().equals("")) {
            JOptionPane.showMessageDialog(null, "Invalid username");
        }
    }

    public void invalidCharacters() {
        String username = loginFrame.getUsernname();

        for (int i = 0; i < username.length(); i++) {

        }
    }

    public void selectImageMessage() {
       // if ()
    }



    public void usernameAlreadyExists() {

    }

    public User createUser() {
        User user = new User(loginFrame.getUsernname(), loginFrame.getImage());

        userlist.add(user);
        return user;
    }

}
