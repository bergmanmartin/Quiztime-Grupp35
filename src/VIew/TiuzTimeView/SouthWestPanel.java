package VIew.TiuzTimeView;

import javax.swing.*;
import java.awt.*;

public class SouthWestPanel extends JPanel {

//    private     Controller controller;

    private JRadioButton radioButtonUtmanaEnKompis = new JRadioButton("Challange a friend");
    private JRadioButton radioButtonUtmanaDigSjalv = new JRadioButton("Challande the computer/yourself");
    private ButtonGroup ButtonGroupRadio = new ButtonGroup();
    private JButton btnStartaSpelet;
    private JButton btnAvsluta;
    private JPanel pnlRadioButtons = new JPanel();
    private JPanel pnlButtons = new JPanel();

    public SouthWestPanel(/*Controller controller*/) {
        //this.controller = controller;
        //setLayout(new BorderLayout());
        //setPreferredSize(new Dimension(width, height));

        createComponentButtons();
    }

    public void createComponentButtons() {

        /**radio buttons*/
        ButtonGroupRadio.add(radioButtonUtmanaEnKompis);
        ButtonGroupRadio.add(radioButtonUtmanaDigSjalv);

        pnlRadioButtons.add(radioButtonUtmanaEnKompis);
        pnlRadioButtons.add(radioButtonUtmanaDigSjalv);

        /**radio buttons*/
        btnStartaSpelet = new JButton("Start");
        Dimension dim = new Dimension(50, 50);
        btnStartaSpelet.setSize(dim);

        btnAvsluta = new JButton("Quit");
        btnStartaSpelet.setSize(dim);

        pnlButtons.add(btnStartaSpelet);
        pnlButtons.add(btnAvsluta);

        add(pnlRadioButtons);
        add(pnlButtons);

        //addListeners();
    }

   /* public void addListeners() {
        ActionListener listener = new ButtonActionListeners();

        radioButtonUtmanaEnKompis.addActionListener(listener);
        radioButtonUtmanaDigSjalv.addActionListener(listener);

        btnStartaSpelet.addActionListener(listener);
        btnAvsluta.addActionListener(listener);*/
}

    /*public class ButtonActionListeners implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == btnAdd) {
                if (rbtnSwe.isSelected()) {
                    controller.contactTypeChanged(ButtonType.Svenska);
                } else if (rbtnEng.isSelected()) {
                    controller.contactTypeChanged(ButtonType.English);
                } else if (rbtnOther.isSelected()) {
                    controller.contactTypeChanged(ButtonType.Other);
                }
                controller.buttonPressed(ButtonType.Add);

            } else if (e.getSource() == btnChange) {

                if (rbtnSwe.isSelected()) {
                    controller.contactTypeChanged(ButtonType.Svenska);
                } else if (rbtnEng.isSelected()) {
                    controller.contactTypeChanged(ButtonType.English);
                } else if (rbtnOther.isSelected()) {
                    controller.contactTypeChanged(ButtonType.Other);
                }
                controller.buttonPressed(ButtonType.Change);
            } else if (e.getSource() == btnDelete) {
                controller.buttonPressed(ButtonType.Delete);
            }
        }
    }*/

