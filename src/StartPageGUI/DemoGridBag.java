package StartPageGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DemoGridBag extends JFrame {

    //================================================================ constants
    private static final int BORDER = 12;  // Window border in pixels.
    private static final int GAP = 5;   // Default gap btwn components.

    //=================================================================== fields
    //... GUI components
    JTextArea jTUserPicture = new JTextArea();
    JLabel jLUserName = new JLabel("UserName");
    JLabel jLHighScore = new JLabel("Highscore!");

    JButton btnStart = new JButton("Starta spelet");
    JButton btnAvsluta = new JButton("Avsluta");
    JButton btnSport = new JButton("Sport");
    JButton btnHistoria = new JButton("Historia");
    JButton btnGeografi = new JButton("Geografi");

    JRadioButton rbUtmanaDigSjalv = new JRadioButton();
    JRadioButton rbUtmanaEnKompis = new JRadioButton();

    //JTextField findTF     = new JTextField(20);
    //JTextField replaceTF  = new JTextField(20);
    //JCheckBox matchCaseCB = new JCheckBox("Match Case");
    //JCheckBox wholeWrdsCB = new JCheckBox("Whole Words");
    //JCheckBox regexCB     = new JCheckBox("Regular Expressions");
    //JCheckBox highlightCB = new JCheckBox("Highlight Results", true);
    //JCheckBox wrapCB      = new JCheckBox("Wrap Around", true);
    //JCheckBox selectionCB = new JCheckBox("Search Selection");
    //JCheckBox backwardsCB = new JCheckBox("Search Backwards");
    //JCheckBox incrementCB = new JCheckBox("Incremental Search", true);

    //JDialog   replaceDialog = new JDialog();

    //============================================================== constructor
    public DemoGridBag() {

        //... Create a dialog box with GridBag content pane.
        setContentPane(createContentPane());
        setTitle("Find Replace");
        pack();
        setLocationRelativeTo(this);

        //... Create a button for the window to display this dialog.
        JButton showDialogBtn = new JButton("Show Find/Replace Dialog");
        showDialogBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(true);
            }
        });

        //... Create content pane with one button and set window attributes.
        JPanel windowContent = new JPanel();
        windowContent.setLayout(new BorderLayout());
        windowContent.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        windowContent.add(showDialogBtn, BorderLayout.CENTER);

        //... Set the window characteristics.
        super.setContentPane(windowContent);
        super.pack();                               // Layout components.
        super.setTitle("DemoGridBag");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null);          // Center window.
    }

    //======================================================== createContentPane
    private JPanel createContentPane() {

        //... Create an independent GridLayout panel of buttons.
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1, GAP, GAP));
        buttonPanel.add(btnStart);
        buttonPanel.add(btnAvsluta);
        buttonPanel.add(btnSport);
        buttonPanel.add(btnHistoria);
        buttonPanel.add(btnGeografi);

        //... Create an independent GridLayout panel of check boxes.
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new GridLayout(4, 2));
        /*checkBoxPanel.add(matchCaseCB);
        checkBoxPanel.add(wrapCB);
        checkBoxPanel.add(wholeWrdsCB);
        checkBoxPanel.add(selectionCB);
        checkBoxPanel.add(regexCB);
        checkBoxPanel.add(backwardsCB);
        checkBoxPanel.add(highlightCB);
        checkBoxPanel.add(incrementCB);*/

        //... Create GridBagLayout content pane; set border.
        JPanel content = new JPanel(new GridBagLayout());
        content.setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER, BORDER, BORDER));

        //\\//\\//\\//\\//\\ GridBagLayout code begins here
        GBHelper pos = new GBHelper();  // Create GridBag helper object.

        //... First row
        content.add(jTUserPicture, pos);
        content.add(new Gap(GAP), pos.nextCol());
        content.add(jLUserName, pos.nextCol().expandW());
        content.add(new Gap(GAP), pos.nextCol());
        content.add(buttonPanel, pos.nextCol().height(5).align(GridBagConstraints.NORTH));

        content.add(new Gap(GAP), pos.nextRow());  // Add a gap below

        //... Next row.
        //content.add(replaceLbl, pos.nextRow());
        //content.add(new Gap(GAP), pos.nextCol());
        //content.add(replaceTF, pos.nextCol().expandW());

        content.add(new Gap(2 * GAP), pos.nextRow());  // Add a big gap below

        //... Last content row.
        content.add(checkBoxPanel, pos.nextRow().nextCol().nextCol());

        //... Add an area that can expand at the bottom.
        content.add(new Gap(), pos.nextRow().width().expandH());
        //\\//\\//\\//\\//\\ GridBagLayout code ends here
        return content;
    }

    //===================================================================== main

    public static void main(String[] args) {
        //... Set Look and Feel.
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception unused) {
            // Nothing can be done, so just ignore it.
        }

        //... Start up GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                DemoGridBag window = new DemoGridBag();
                window.setVisible(true);
            }
        });
    }
}
