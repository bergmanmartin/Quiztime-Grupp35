package StartPageGUI;

import javax.swing.*;
import java.awt.*;

public class NorthWestPanel extends JPanel {

    private     int     width = 200;

    private     int     height = 200;

    private     JTextArea       textAreaPicture     = new JTextArea("bild");

    private     JLabel          textFieldUserName = new JLabel("     User name");

    private     JPanel          pnlNWLeft;

    private     JPanel          pnlNWRight;

    //private Controller controller;

    public NorthWestPanel(/*Controller controller*/) {
        //this.controller = controller;
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(width, height));

        createComponentsForPictureNUsername();
    }

    public void createComponentsForPictureNUsername() {

        GridLayout layout = new GridLayout();

        pnlNWLeft = new JPanel(layout);
        pnlNWRight = new JPanel(layout);

        pnlNWLeft.add(textAreaPicture);
        pnlNWRight.add(textFieldUserName);

        add(pnlNWLeft, BorderLayout.WEST);
        add(pnlNWRight, BorderLayout.CENTER);
    }
}
