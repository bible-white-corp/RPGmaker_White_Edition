package editor.Forms;

import editor.Editor;
import editor.Tiles.Tile;
import editor.Tools.Selection;

import javax.swing.*;
import java.awt.*;
import java.util.List;

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

            List<Tile> tiles = selection.getTiles();

            if (tiles != null)
                tiles.get(0).getParent().drawselection(selection,0,0, g);
        }
    }
}
