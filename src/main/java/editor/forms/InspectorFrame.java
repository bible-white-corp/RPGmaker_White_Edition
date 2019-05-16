package editor.forms;

import editor.Editor;
import editor.Tiles.TilePair;

import javax.swing.*;
import java.awt.*;

public class InspectorFrame extends JPanel {

    TilePanel tilePanel;

    public InspectorFrame() {

        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(300,0));

        Editor.setInspector(this);

        initialize();
    }

    public void initialize()
    {
        tilePanel = new TilePanel(Editor.getSelected_tile());

        add(tilePanel, EditFrame.get_c(0,0,1,1));
        //add(new TextField(), EditFrame.get_c(0,1,0,0));
    }

    public void refresh()
    {
        tilePanel.select_tile = Editor.getSelected_tile();
        tilePanel.revalidate();
        tilePanel.repaint();
    }

    private class TilePanel extends JPanel{

        TilePair select_tile;

        public TilePanel(TilePair pair)
        {
            this.select_tile = pair;
        }

        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            if(select_tile != null)
            {
                select_tile.tileSet.drawtile(select_tile.tile,0,0, g);
            }
            g.drawRect(0,0,32,32);
        }
    }
}
