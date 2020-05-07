package Model.Users;

import javax.swing.*;
import java.awt.*;

/**
 * Entity class that stores the User-object.
 * @author Martin Bergman
 * @author Markus Gerdtsson
 */

public class User {
    private String username;
    private ImageIcon userPicture;


    public User(String username, ImageIcon userPicture) {
        this.username = username;
        this.userPicture = userPicture;
    }

    /**
     * Returns the username
     * @return String
     */
    public  String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the image.
     * @return imageIcon
     */
    public ImageIcon getUserPicture() {
        return userPicture;
    }

    public void setUserPicture(ImageIcon userPicture) {
        this.userPicture = userPicture;
    }
}
