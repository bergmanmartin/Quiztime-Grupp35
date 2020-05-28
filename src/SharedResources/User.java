package SharedResources;

import javax.swing.*;
import java.io.Serializable;

/**
 * @Author Markus Gerdtsson, Marianne Mukanga, Martin Bergman och Erik Nielsen.
 */

public class User implements Serializable {
    private String username;
    private ImageIcon userPicture;
    private int points;
    private boolean ready;
    private boolean playAlone;


    public User(String username, ImageIcon userPicture, boolean ready) {
        this.username = username;
        this.userPicture = userPicture;
        this.ready = ready;
    }

    public  String getUsername() {
        return username;
    }

    public ImageIcon getUserPicture() {
        return userPicture;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReadyTrue() {
        ready = true;
    }

    public void setPlayAlone(){
        playAlone = true;
    }

    public boolean getPlayAlone() {
        return playAlone;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
