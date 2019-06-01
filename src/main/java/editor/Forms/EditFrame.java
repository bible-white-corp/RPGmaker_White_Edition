package editor.Forms;

import editor.Editor;

import javax.swing.*;
import java.awt.*;

public class EditFrame extends JFrame {

    JPanel pan = new JPanel();
    JMenuBar menuBar;
    JToolBar toolBar;
    JPanel footer;
    public TileSetFrame tileSetFrame;
    public ObjectFrame objectFrame;
    public ObjectEditionFrame editionFrame;
    public JTabbedPane tabbedPane;

    public EditFrame() {

        menuBar = new MenuFrame();
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

        tabbedPane = new JTabbedPane();

        pan.add(tabbedPane, get_c(1,0,1,1));

        JScrollPane scrollPane = new JScrollPane(new GameFrame());

        scrollPane.setPreferredSize(new Dimension(0,0));

        tabbedPane.add("Game", scrollPane);

        editionFrame = new ObjectEditionFrame();
        tabbedPane.add("Object Edition", editionFrame);

        JTabbedPane tabbedPane1 = new JTabbedPane();
        pan.add(tabbedPane1, get_c(2,0,0,1));

        tabbedPane1.add("Inspector", new InspectorFrame());
    }

    private void createSideWindow() {

        JPanel side_window = new JPanel();
        side_window.setLayout(new GridBagLayout());
        side_window.setPreferredSize(new Dimension(250,0));

        JTabbedPane tabbedPane = new JTabbedPane();

        tileSetFrame = new TileSetFrame();
        objectFrame = new ObjectFrame();

        tabbedPane.add("TileSet", tileSetFrame);
        tabbedPane.add("Objects", objectFrame);

        side_window.add(tabbedPane, get_c(0,0,1,1));
        side_window.add(new JScrollPane(Editor.world.projectTree.myTree),get_c(0,1,1,0.5));

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
