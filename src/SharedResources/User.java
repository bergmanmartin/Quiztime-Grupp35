package SharedResources;

import javax.swing.*;
import java.io.Serializable;

/**
 * @Created 11/02/2020
 * @project
 * @Markus Gerdtsson
 */

public class User implements Serializable {
    private String username;
    private ImageIcon userPicture;
    private boolean ready;


    public User(String username, ImageIcon userPicture, boolean ready) {
        this.username = username;
        this.userPicture = userPicture;
        this.ready = ready;
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

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }
}
