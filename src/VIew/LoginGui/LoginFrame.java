package VIew.LoginGui;

import Controller.Client;
import Controller.Server;
import Model.Users.User;
import SecondFrame.MainSecondFrame;
import StartPageGUI.MainFrame;


import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class of the Loginframe with gui and functionality.
 * @version 1.0
 * @author martinbergman.
 */


public class LoginFrame extends JPanel {


    private ImageIcon userPicture;
    private JButton loginBtn = new JButton("Login");
    private JButton imageBtn = new JButton("Select image");

    private ImageIcon image;

    private JTextField loginTf = new JTextField();

    private JPanel pnlImage = new JPanel();
    private JPanel pnlImageBtn = new JPanel();
    private JPanel pnlLoginTf = new JPanel();
    private JPanel pnlLoginBtn = new JPanel();


    private JLabel imageLbl = new JLabel();

    private boolean correctImage = false;
    private boolean correctName = false;



    private JFrame loginFrame;


    private LoginController controller;


    public LoginFrame(LoginController controller) {

        this.controller = controller;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        settingImagePanel();
        settingSignInPanel();
        settingButtonPanel();

        setPreferredSize(new Dimension(600, 400));
        initWinow();


        buttonActions();
    }




    /**
     * Method for initatilizing the window frame for the loginscreen
     */

        public void initWinow() {
            loginFrame = new JFrame("Welcome to QuizTime!");
            loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            loginFrame.add(this);
            loginFrame.pack();
            loginFrame.setVisible(true);
            loginFrame.setLocationRelativeTo(null);
            loginFrame.setResizable(false);
        }

        private void settingImagePanel() {
            pnlImage.setPreferredSize(new Dimension(120, 120));
            pnlImage.setMaximumSize(new Dimension(120, 120));
            //pnlImage.setBorder(new LineBorder(Color.BLACK, 1));

            pnlImage.add(imageLbl);


            pnlImageBtn.setLayout(new BoxLayout(pnlImageBtn, BoxLayout.X_AXIS));
            pnlImageBtn.setPreferredSize(new Dimension(100, 40));
            pnlImageBtn.add(imageBtn);

            add(Box.createVerticalStrut(40));
            add(pnlImage);
            add(Box.createVerticalStrut(20));
            add(pnlImageBtn);
        }

        /**
         * Method for constructing the "bottom half" of the login frame.
         */
        private void settingSignInPanel() {
            pnlLoginTf.setLayout(new BoxLayout(pnlLoginTf, BoxLayout.X_AXIS));
            pnlLoginTf.setPreferredSize(new Dimension(300, 40));
            pnlLoginTf.setMaximumSize(new Dimension(300, 40));

            loginTf.setPreferredSize(new Dimension(190, 20));
            loginTf.setDocument(new  JTextFieldLimit(10));
            pnlLoginTf.add(loginTf);
            add(Box.createVerticalStrut(20));
            add(pnlLoginTf);
        }

        public void settingButtonPanel() {
            pnlLoginBtn.setLayout(new BoxLayout(pnlLoginBtn, BoxLayout.X_AXIS));
            pnlLoginBtn.setPreferredSize(new Dimension(80, 80));
            loginBtn.setPreferredSize(new Dimension(80, 40));
            pnlLoginBtn.add(loginBtn);
            add(Box.createVerticalStrut(20));
            add(pnlLoginBtn);
        }


        public void buttonActions() {

                imageBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setImageLbl();
                    }
                });


                loginBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setUserName();
                        try {
                            proceedLogin();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }

                });
        }


            public void setUserName() {
                if (controller.checkUsername(loginTf.getText()) == true) {
                    correctName = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Only numbers and letters are allowed in username.");
                }
            }


            public void setImageLbl() {

                String filepath = controller.selectedImage();

                if (filepath == null) {
                    return;
                } else if (filepath.endsWith(".png") || filepath.endsWith(".jpg")) {
                    userPicture = new ImageIcon(filepath);
                    Image image = userPicture.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
                    userPicture = new ImageIcon(image);
                    imageLbl.setIcon(userPicture);
                    correctImage = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Only jpg or png fileformat.");
                }
            }

    public String getUsernname() {
            return loginTf.getText();
    }


    private class JTextFieldLimit extends PlainDocument {
        private int limit;

        JTextFieldLimit(int limit) {
            super();
            this.limit = limit;
        }

        public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
            if (str == null) return;

            if ((getLength() + str.length()) <= limit) {
                super.insertString(offset, str, attr);
            }
        }
    }

    //TEST
    
            public void proceedLogin() throws IOException {

                if (correctName && correctImage == true) {
                    loginFrame.setVisible(false);

                    User user = new User(loginTf.getText(), userPicture);

                    MainSecondFrame mains = new MainSecondFrame(user);
                }
            }



    }





