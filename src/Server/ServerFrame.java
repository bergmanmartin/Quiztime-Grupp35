package Server;

import javax.swing.*;

/**
 * @Created 11/02/2020
 * @project P1
 * @Markus Gerdtsson
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
    public void addElement(String s){
        listModel.addElement(s);

    }


}
