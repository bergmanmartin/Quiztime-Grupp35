import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Created 11/02/2020
 * @project P1
 * @Markus Gerdtsson
 */
public class clientGUI extends JFrame {

    private JFrame frame = new JFrame();
    private JPanel panel =  new JPanel();
    private JButton button = new JButton("nästa");
    private client1 client1;


    public clientGUI(client1 client){

        this.client1 = client;

        frame.setPreferredSize(new Dimension(400,400));
        frame.setLocation(800,400);
        panel.setPreferredSize(new Dimension(400,400));
        button.setPreferredSize(new Dimension(200,200));


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(button.isSelected()){
                    client.Ready();
                }
            }
        });
        panel.add(button);
        frame.add(panel);
        frame.setVisible(true);

        /*
        guit ska kalla metod i client som signalerar till servern att clienten är redo för nästa fråga.
         */


    }
}
