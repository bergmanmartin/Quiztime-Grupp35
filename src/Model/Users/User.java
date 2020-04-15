package Model.Users;

import javax.swing.*;
import java.awt.*;

/**
 * @Created 11/02/2020
 * @project
 * @Markus Gerdtsson
 */

public class User {
    private String username;
    private ImageIcon userPicture;


    public User(String username, ImageIcon userPicture) {
        this.username = username;
        this.userPicture = userPicture;
    }

    public  String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public ImageIcon getUserPicture() {
        return userPicture;
    }
    public void setUserPicture(ImageIcon userPicture) {
        this.userPicture = userPicture;
    }
}
