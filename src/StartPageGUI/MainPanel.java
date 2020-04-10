package StartPageGUI;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    //    Controller controller;
    NorthWestPanel pnlNorthWest;
    SouthWestPanel pnlSouthWest;
    BorderLayout layout;
    TablePanel pnlTable;

    public MainPanel(/*Controller controller*/) {
//        this.controller = controller;
        setupPanels();
    }

    /**Set up GUI*/
    private void setupPanels() {

        layout = new BorderLayout();
        setLayout(layout);

        //Border border = this.getBorder();
        //Border margin = BorderFactory.createEmptyBorder(1, 12, 12, 12);
        //setBorder(new CompoundBorder(border, margin));

        pnlNorthWest = new NorthWestPanel(/*controller*/);
        pnlSouthWest = new SouthWestPanel(/*controller*/);
        pnlTable = new TablePanel(/*controller*/);

        add(pnlNorthWest, layout.WEST);
        add(pnlSouthWest, layout.SOUTH);
        // add(pnlTable, layout.EAST);
    }
}
