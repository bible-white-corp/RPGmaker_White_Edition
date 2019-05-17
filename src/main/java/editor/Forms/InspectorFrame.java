package editor.Forms;

import editor.Editor;
import editor.Tiles.Tile;
import editor.Tools.Selection;

import javax.swing.*;
import java.awt.*;

public class InspectorFrame extends JPanel {

    public InspectorFrame() {

        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(300,0));

        initialize();
    }

    public void initialize()
    {
        add(new TilePanel(Editor.getSelection()), EditFrame.get_c(0,0,1,1));
    }

    private class TilePanel extends JPanel{

        Selection selection;

        public TilePanel(Selection selection)
        {
            this.selection = selection;

            selection.addSelectionListener(() -> TilePanel.this.repaint());
        }

        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            Tile select_tile = selection.getTile();

            if (select_tile != null)
                select_tile.getParent().drawtile(select_tile,0,0, g);
        }
    }
}
