package Client.View.LoginGui;



import Client.Model.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.ImageFilter;
import java.io.File;
import java.util.ArrayList;

/**
 * Class that handles the functionnailty of the loginframe
 * @author martinbergman, Erik Nielsen, Marianne Mukanga, Markus Gerdtsson
 * @version 1.2
 *
 */

public class LoginController {
    private LoginFrame loginFrame;
    private User user;
    private ArrayList<User> userlist = new ArrayList<User>();
    private String file;
    private String filePath;

    /**
     * Constructor that initializes the loginFrame
     */
    public LoginController (){
        loginFrame = new LoginFrame(this);
    }

    /**
     * Checks the correct characters for the username
     * @param name
     * @return
     */
    public boolean checkUsername(String name){
        boolean match = name.matches("[a-zA-Z0-9]+");
        return match;
    }

    /**
     * Returns the path for the selected file from the filechooser
     * @return
     */
    public String selectedImage() {
        JFileChooser imageChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg");


        imageChooser.setFileFilter(filter);
        int chosenImage = imageChooser.showOpenDialog(null);
        if (chosenImage == JFileChooser.APPROVE_OPTION) {
            try {
                filePath = imageChooser.getSelectedFile().getAbsolutePath();

            } catch (Exception e) {}
        }
        return filePath;
    }


}
