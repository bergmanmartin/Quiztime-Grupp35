package StartPageGUI;

import VIew.LoginGui.LoginController;
import VIew.LoginGui.LoginFrame;

import javax.swing.*;

public class MainFrame extends JFrame {

    private int width = 600;
    private int height = 600;

    //        Controller controller;
    MainPanel panel;
    LoginFrame frame;
    LoginController controller;

    public MainFrame(/*Controller controller*/) {
//        this.controller = controller;
        setupFrame();
        frame = new LoginFrame(controller);


    }

    //standard settings
    public void setupFrame() {
        final int offsetX = width / 5;
        final int offsetY = height / 5;

        setSize(width, height);
        setTitle("QuizTime");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(offsetX, offsetY);

        panel = new MainPanel(/*controller*/);
        setContentPane(panel);
        pack();

        setVisible(true);


    }
    public static void main (String [] args){
        MainFrame mf = new MainFrame();
    }
}
