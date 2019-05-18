package editor.Forms;

import editor.Maps.World;
import editor.Maps.loadWorldAction;
import editor.Maps.saveWorldAction;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MenuFrame extends JMenuBar{

    World world;

    public MenuFrame()
    {
        JMenu menu = new JMenu("File");
        add(menu);

        JMenuItem menuItem = new JMenuItem("New");
        menu.add(menuItem);

        menuItem = new JMenuItem("Open");
        menu.add(menuItem);

        menu.addSeparator();

        menuItem = new JMenuItem("Settings");
        menu.add(menuItem);


        Action quick_load = new loadWorldAction();
        quick_load.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        menuItem = new JMenuItem(new loadWorldAction());
        menuItem.setAction(quick_load);
        menu.add(menuItem);

        Action quick_save = new saveWorldAction();
        quick_save.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        menuItem = new JMenuItem(quick_save);
        menuItem.setAction(quick_save);
        menu.add(menuItem);

        menu.addSeparator();

        menuItem = new JMenuItem("Exit");
        menu.add(menuItem);

        //Build Edit Menu
        menu = new JMenu("Edit");
        add(menu);

        menuItem = new JMenuItem("Undo");
        menu.add(menuItem);

        menuItem = new JMenuItem("Redo");
        menu.add(menuItem);

        menu.addSeparator();

        menuItem = new JMenuItem("Cut");
        menu.add(menuItem);

        menuItem = new JMenuItem("Copy");
        menu.add(menuItem);

        menuItem = new JMenuItem("Paste");
        menu.add(menuItem);
    }

}
