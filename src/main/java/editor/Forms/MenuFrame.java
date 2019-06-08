package editor.Forms;

import editor.Editor;
import editor.Maps.*;
import editor.Maps.actions.loadWorldAction;
import editor.Maps.actions.newMapAction;
import editor.Maps.actions.newWorldAction;
import editor.Maps.actions.saveWorldAction;
import editor.Object.actions.createAnimationAction;
import editor.Object.actions.createObjectAction;
import editor.Object.actions.createSpriteAction;
import editor.Object.actions.newSpritesSheet;
import editor.Tiles.actions.createTileSetAction;
import editor.Tiles.actions.loadTileSetAction;
import editor.Tiles.actions.saveTileSetAction;
import editor.Tools.Undo.action.redoAction;
import editor.Tools.Undo.action.undoAction;
import engine.Engine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import static engine.Engine.*;
import static java.lang.System.exit;

public class MenuFrame extends JMenuBar{

    World world;

    public MenuFrame()
    {
        JMenu menu = new JMenu("File");
        add(menu);

        Action quick_new = new newWorldAction();
        quick_new.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        JMenuItem menuItem = new JMenuItem(new newWorldAction());
        menuItem.setAction(quick_new);
        menu.add(menuItem);

        Action new_map = new newMapAction();
        menuItem = new JMenuItem("Add map");
        menuItem.setAction(new_map);
        menu.add(menuItem);

        menu.addSeparator();

        Action construct_ts = new createTileSetAction();
        menuItem = new JMenuItem();
        menuItem.setAction(construct_ts);
        menu.add(menuItem);

        Action quick_import_ts = new loadTileSetAction();
        menuItem = new JMenuItem();
        menuItem.setAction(quick_import_ts);
        menu.add(menuItem);

        Action quick_export_ts = new saveTileSetAction();
        menuItem = new JMenuItem("Export tiles");
        menuItem.setAction(quick_export_ts);
        menu.add(menuItem);

        menu.addSeparator();


        Action quick_load = new loadWorldAction();
        quick_load.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        menuItem = new JMenuItem(new loadWorldAction());
        menuItem.setAction(quick_load);
        menu.add(menuItem);

        Action quick_save = new saveWorldAction("Save project", true);
        quick_save.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        menuItem = new JMenuItem(quick_save);
        menuItem.setAction(quick_save);
        menu.add(menuItem);

        Action save_as = new saveWorldAction("Save project as...", false);
        menuItem = new JMenuItem(save_as);
        menu.add(menuItem);

        menu.addSeparator();

        menuItem = new JMenuItem("Settings");
        menu.add(menuItem);

        menu.addSeparator();

        Action quick_exit = new AbstractAction("Exit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit(0);
            }
        };

        quick_exit.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_DOWN_MASK));
        menuItem = new JMenuItem();
        menuItem.setAction(quick_exit);
        menu.add(menuItem);


        //Build Edit Menu
        menu = new JMenu("Edit");
        add(menu);

        Action quick_undo = new undoAction();
        quick_undo.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));
        menuItem = new JMenuItem();
        menuItem.setAction(quick_undo);
        menu.add(menuItem);

        Action quick_redo = new redoAction();
        quick_redo.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_DOWN_MASK));
        menuItem = new JMenuItem();
        menuItem.setAction(quick_redo);
        menu.add(menuItem);

        menu = new JMenu("Objects");
        add(menu);

        Action new_ss = new newSpritesSheet();
        menuItem = new JMenuItem("Add spritesSheet");
        menuItem.setAction(new_ss);
        menu.add(menuItem);

        menu.addSeparator();

        Action new_s = new createSpriteAction();
        menuItem = new JMenuItem();
        menuItem.setAction(new_s);
        menu.add(menuItem);

        menu.addSeparator();

        Action new_anim = new createAnimationAction();
        menuItem = new JMenuItem();
        menuItem.setAction(new_anim);
        menu.add(menuItem);

        menu.addSeparator();

        Action new_obj = new createObjectAction();
        menuItem = new JMenuItem();
        menuItem.setAction(new_obj);
        menu.add(menuItem);

        menu = new JMenu("Engine");
        add(menu);

        menuItem = new JMenuItem("Launch");

        Action quick_lauch = new AbstractAction("Launch") {
            @Override
            public void actionPerformed(ActionEvent e) {
                Engine.launchWorld((World) Editor.world.clone());
            }
        };

        quick_lauch.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_F10, KeyEvent.SHIFT_DOWN_MASK));
        menuItem.setAction(quick_lauch);
        menu.add(menuItem);

        menuItem = new JMenuItem("Debug");

        Action quick_debug = new AbstractAction("Debug") {
            @Override
            public void actionPerformed(ActionEvent e) {
                Engine.launchWorld(Editor.world);
            }
        };

        quick_debug.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_F9, KeyEvent.SHIFT_DOWN_MASK));
        menuItem.setAction(quick_debug);
        menu.add(menuItem);
    }
}
