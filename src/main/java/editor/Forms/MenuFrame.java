package editor.Forms;

import editor.Maps.World;
import editor.Maps.saveWorldAction;

import javax.swing.*;

public class MenuFrame extends JMenuBar{

    World world;

    public MenuFrame(World w)
    {
        world = w;
        JMenu menu = new JMenu("File");
        add(menu);

        JMenuItem menuItem = new JMenuItem("New");
        menu.add(menuItem);

        menuItem = new JMenuItem("Open");
        menu.add(menuItem);

        menu.addSeparator();

        menuItem = new JMenuItem("Settings");
        menu.add(menuItem);

        menuItem = new JMenuItem(new saveWorldAction(w));
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
