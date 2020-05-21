package Server;

import javax.swing.*;

/**
 * @Author Markus Gerdtsson, Marianne Mukanga, Martin Bergman och Erik Nielsen.
 * This class contains the JFrame for the server.
 */
public class ServerFrame extends JFrame {
    private JList<String> countryList;
    DefaultListModel<String> listModel = new DefaultListModel<>();
    public ServerFrame(Server s) {
        //create the model and add elements


        //create the list
        countryList = new JList<>(listModel);
        add(countryList);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("JList Example");
        this.setSize(200,200);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    /**
     * Adds and element to the ListModel.
     * @param s
     */
    public void addElement(String s){
        listModel.addElement(s);

    }


}
