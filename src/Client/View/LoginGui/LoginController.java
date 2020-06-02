package Client.View.LoginGui;


import SharedResources.User;

import javax.swing.*;
import java.util.ArrayList;

/**
 * @author Markus Gerdtsson, Marianne Mukanga, Martin Bergman och Erik Nielsen.
 * This class controlls the login tasks in the application.
 * @version 1.4
 */
public class LoginController {
    private LoginFrame loginFrame;
    private SharedResources.User user;
    private ArrayList<User> userlist = new ArrayList<>();
    private String file;
    private String filePath;


    /**
     * Initializes the loginFrame and assigns the controller.
     */
    public LoginController() {
        loginFrame = new LoginFrame(this);
    }


    /**
     * Checks if the user input contains valid characters and returns a boolean.
     * @param name Users input name
     * @return true or false
     */
    public boolean checkUsername(String name) {
        boolean match = name.matches("[a-zA-Z0-9]+");
        return match;
    }


    /**
     * Checks if the filePath selected is valid and returns the filePath
     * @return
     */
    public String selectedImage() {
        JFileChooser imageChooser = new JFileChooser();
        int chosenImage = imageChooser.showOpenDialog(null);
        if (chosenImage == JFileChooser.APPROVE_OPTION) {
            try {
                filePath = imageChooser.getSelectedFile().getAbsolutePath();

            } catch (Exception e) {
            }
        }
        return filePath;
    }

}
