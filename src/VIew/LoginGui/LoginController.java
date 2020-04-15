package VIew.LoginGui;

import Model.Users.User;
import StartPageGUI.LayoutExample;


import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

public class LoginController {
    private LoginFrame loginFrame;
    private User user;
    private ArrayList<User> userlist = new ArrayList<User>();
    private String file;
    private String filePath;
    private LayoutExample startPage;
    private LoginPanel loginPanel;



    public LoginController (){
        loginFrame = new LoginFrame(this);
        loginPanel = new LoginPanel(this);


    }

    public void setupStartPageView() {
        startPage = new LayoutExample(this);
        startPage.setVisible(true);
        loginFrame.dispose();
    }

    public void emptyUsername() {
        if (loginPanel.getUsernname().equals("")) {
            JOptionPane.showMessageDialog(null, "Invalid username");
        }
    }

    public void invalidCharacters() {
        String username = loginPanel.getUsernname();
        for (int i = 0; i < username.length(); i++) {
        }
    }


    public User createUser() {
        User user = new User(loginPanel.getUsernname(), loginPanel.getImage());

        userlist.add(user);
        return user;
    }



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



    public User getUser() {
        return user;
    }

}
