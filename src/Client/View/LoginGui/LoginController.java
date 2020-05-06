package Client.View.LoginGui;



import Client.Model.User;

import javax.swing.*;
import java.util.ArrayList;

//import StartPageGUI.LayoutExample;

public class LoginController {
    private LoginFrame loginFrame;
    private User user;
    private ArrayList<User> userlist = new ArrayList<User>();
    private String file;
    private String filePath;
  //  private LayoutExample startPage;




    public LoginController (){
        loginFrame = new LoginFrame(this);



    }


    public boolean checkUsername(String name){
        boolean match = name.matches("[a-zA-Z0-9]+");
        return match;
    }



    public void emptyUsername() {
        if (loginFrame.getUsernname().equals("")) {
            JOptionPane.showMessageDialog(null, "Invalid username");
        }
    }

    public void invalidCharacters() {
        String username = loginFrame.getUsernname();
        for (int i = 0; i < username.length(); i++) {
        }
    }

/*
    public User createUser() {
        User user = new User(loginPanel.getUsernname(), loginPanel.getImage());

        userlist.add(user);
        return user;
    }
*/


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
