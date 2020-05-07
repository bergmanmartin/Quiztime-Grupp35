package VIew.LoginGui;

import Model.Users.User;
import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

/**
 * Class that handles login functionality.
 * @author martinbergman.
 * @version 1.0
 */
public class LoginController {
    private LoginFrame loginFrame;
    private User user;
    private ArrayList<User> userlist = new ArrayList<User>();
    private String file;
    private String filePath;

<<<<<<< HEAD
    public LoginController (){
        loginFrame = new LoginFrame(this);

    }

=======
    /**
     * Initializes loginFrame
     */
    public LoginController (){
        loginFrame = new LoginFrame(this);
    }

    /**
     * Checks the username for valid character
     * @param name the typed in username
     * @return
     */
>>>>>>> Comment
    public boolean checkUsername(String name){
        boolean match = name.matches("[a-zA-Z0-9]+");
        return match;
    }

<<<<<<< HEAD
/*
    public User createUser() {
        User user = new User(loginPanel.getUsernname(), loginPanel.getImage());

        userlist.add(user);
        return user;
    }
*/

=======
    /**
     * Method for image selection.
     * @return
     */
>>>>>>> Comment
    public String selectedImage() {
        JFileChooser imageChooser = new JFileChooser();
        int chosenImage = imageChooser.showOpenDialog(null);
        if (chosenImage == JFileChooser.APPROVE_OPTION) {
            try {
                filePath = imageChooser.getSelectedFile().getAbsolutePath();

            } catch (Exception e) {}
        }
        return filePath;
    }

}
