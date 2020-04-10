package LoginGui;


import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
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


    private JFrame loginFrame;

    private int score;

    private User user;

    private String username;

    private JFileChooser imageChooser;

    private HashMap<User, Integer> userList = new HashMap<User, Integer>();

    private LoginController controller;

    public LoginFrame(LoginController controller) {

        user = new User(username, userPicture);
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
    private void initWinow() {
        loginFrame = new JFrame("Welcome to Quiztime!");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.add(this);
        loginFrame.pack();
        loginFrame.setVisible(true);
        loginFrame.setLocationRelativeTo(null);

    }

    /**
     * Method for constructing the panel which contains the image chooser
     *
     */
    private void settingImagePanel() {
        pnlImage.setPreferredSize(new Dimension(120, 120));
        pnlImage.setMaximumSize(new Dimension(120, 120));
        pnlImage.setBorder(new LineBorder(Color.BLACK, 1));

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
        loginTf.setDocument(new JTextFieldLimit(10));
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

    public String getUsernname() {
        return loginTf.getText();
    }


    public void buttonActions () {
        loginBtn.addActionListener(e -> {
            controller.emptyUsername();

            });
        imageBtn.addActionListener(e -> {
            controller.selectedImage();
            setImageLbl();

        });

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

    public void setImageLbl() {
        ImageIcon userPicture = new ImageIcon(controller.selectedImage());
        Image image = userPicture.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        userPicture = new ImageIcon(image);
        imageLbl.setIcon(userPicture);

    }
    public ImageIcon getImage() {
        return userPicture;
    }

}


