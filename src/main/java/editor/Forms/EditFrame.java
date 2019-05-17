package editor.Forms;

import editor.Maps.World;

import javax.swing.*;
import java.awt.*;

public class EditFrame extends JFrame {

    JPanel pan = new JPanel();
    World world;

    JMenuBar menuBar;
    JToolBar toolBar;
    JPanel footer;

    public EditFrame(World w) {

        this.world = w;
        menuBar = new MenuFrame(w);
        toolBar = new ToolsFrame();
        footer = new FooterFrame();
        createMainFrame();

        setTitle("RpgEditor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void createMainFrame() {

        setLayout(new GridBagLayout());
        pan.setLayout(new GridBagLayout());

        setJMenuBar(menuBar);
        add(toolBar,get_c(0,0,1,0));
        add(pan,get_c(0,1,1,1));
        add(footer,get_c(0,2,1,0));

        createMainWindow();
        createSideWindow();
    }

    private void createMainWindow() {

        JTabbedPane tabbedPane = new JTabbedPane();

        pan.add(tabbedPane, get_c(1,0,1,1));

        tabbedPane.add("Game", new GameFrame(world));
        tabbedPane.add("Story", new StoryFrame());

        JTabbedPane tabbedPane1 = new JTabbedPane();
        pan.add(tabbedPane1, get_c(2,0,0,1));

        tabbedPane1.add("Inspector", new InspectorFrame());
    }

    private void createSideWindow() {

        JPanel side_window = new JPanel();
        side_window.setLayout(new GridBagLayout());
        side_window.setPreferredSize(new Dimension(250,0));

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.add("TileSet", new TileSetFrame(world));
        tabbedPane.add("Objects", new ObjectFrame());

        side_window.add(tabbedPane, get_c(0,0,1,1));
        side_window.add(new HierachyFrame(),get_c(0,1,1,0.5));

        pan.add(side_window, get_c(0,0,0,1));
    }

    public static GridBagConstraints get_c(int gx, int gy, double wx, double wy) {

        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;

        c.gridx = gx;
        c.gridy = gy;

        c.weightx = wx;
        c.weighty = wy;

        return c;
    }
}
