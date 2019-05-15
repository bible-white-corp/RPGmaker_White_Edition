package editor.forms;

import javax.swing.*;
import java.awt.*;

public class EditFrame extends JFrame {

    JPanel pan = new JPanel();

    JMenuBar menuBar = new MenuFrame();
    JToolBar toolBar = new ToolsFrame();
    JPanel footer = new FooterFrame();

    public EditFrame() {

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

        tabbedPane.add(new GameFrame());
        tabbedPane.add(new InspectorFrame());
    }

    private void createSideWindow() {

        JPanel side_window = new JPanel();
        side_window.setLayout(new GridBagLayout());
        side_window.setPreferredSize(new Dimension(250,0));

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.add(new TileSetFrame());
        tabbedPane.add(new ObjectFrame());

        side_window.add(tabbedPane, get_c(0,0,1,1));
        side_window.add(new HierachyFrame(),get_c(0,1,1,0));

        pan.add(side_window, get_c(0,0,0,1));
    }

    public static GridBagConstraints get_c(int gx, int gy, int wx, int wy) {

        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;

        c.gridx = gx;
        c.gridy = gy;

        c.weightx = wx;
        c.weighty = wy;

        return c;
    }
}
