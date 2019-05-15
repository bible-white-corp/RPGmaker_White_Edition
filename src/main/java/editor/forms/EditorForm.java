package editor.forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class EditorForm {
    private JPanel EditorWindow;
    private JPanel ToolsView;
    private JScrollPane GameView;
    private JScrollPane ProjectView;
    private JScrollPane TileSetView;
    private JPanel LeftPanel;


    public static JMenuBar createMenuBar() {

        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;
        JRadioButtonMenuItem rdmi;
        JCheckBoxMenuItem cbmi;

        //Create the menu bar.
        menuBar = new JMenuBar();

        //Build the File Menu.
        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);
        menu.getAccessibleContext().setAccessibleDescription("Dealing with Files");
        menuBar.add(menu);

        //a group of JMenuItems
        menuItem = new JMenuItem("New Project...",
                new ImageIcon("images/newproject.png"));
        menuItem.setMnemonic(KeyEvent.VK_P);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "New Project");
        menu.add(menuItem);

        menuItem = new JMenuItem("New File...",
                new ImageIcon("images/newfile.png"));
        menuItem.setMnemonic(KeyEvent.VK_F);
        menu.add(menuItem);

        //a group of check box menu items
        menu.addSeparator();
        cbmi = new JCheckBoxMenuItem("A check box menu item");
        cbmi.setMnemonic(KeyEvent.VK_C);
        menu.add(cbmi);

        cbmi = new JCheckBoxMenuItem("Another one");
        cbmi.setMnemonic(KeyEvent.VK_H);
        menu.add(cbmi);

        //a group of radio button menu items
        menu.addSeparator();
        ButtonGroup group = new ButtonGroup();
        rdmi = new JRadioButtonMenuItem("Radio button menu item");
        rdmi.setSelected(true);
        rdmi.setMnemonic(KeyEvent.VK_R);
        group.add(rdmi);
        menu.add(rdmi);

        rdmi = new JRadioButtonMenuItem("Another radio button");
        rdmi.setMnemonic(KeyEvent.VK_O);
        group.add(rdmi);
        menu.add(rdmi);

        //a submenu
        menu.addSeparator();
        submenu = new JMenu("A submenu");
        submenu.setMnemonic(KeyEvent.VK_S);

        menuItem = new JMenuItem("Menu item in the submenu");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_2, ActionEvent.ALT_MASK));
        submenu.add(menuItem);

        menuItem = new JMenuItem("Another menu item");
        submenu.add(menuItem);
        menu.add(submenu);

        //Build Edit menu in the menu bar.
        menu = new JMenu("Edit");
        menu.setMnemonic(KeyEvent.VK_E);
        menu.getAccessibleContext().setAccessibleDescription(
                "Edit Menu");
        menuBar.add(menu);
        return menuBar;

    }

    private static JToolBar createToolBar() {

        JToolBar toolbar = new JToolBar();
        ImageIcon icon = new ImageIcon("src/main/resources/images/icon_save.jpg");

        JButton exitButton = new JButton(icon);
        toolbar.add(exitButton);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        return toolbar;
    }

    public static JFrame createFrame(){
        JFrame frame = new JFrame("");
        frame.setJMenuBar(createMenuBar());
        frame.add(createToolBar(), BorderLayout.NORTH);

        frame.setContentPane(new EditorForm().EditorWindow);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        return frame;
    }
}
