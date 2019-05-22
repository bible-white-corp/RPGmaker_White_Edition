package editor.Forms;

import editor.Editor;
import editor.Tiles.Tile;
import editor.Tiles.TilePair;
import editor.Tools.Selection;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.SpinnerUI;
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
        add(new TileLayer(Editor.getSelection()), EditFrame.get_c(0,1,1,1));
    }

    private class TileLayer extends JPanel
    {
        Selection selection;
        SpinnerNumberModel numberModel = new SpinnerNumberModel(0,0,10,1);
        JSpinner spinner = new JSpinner(numberModel);

        public TileLayer(Selection selection)
        {
            this.selection = selection;

            selection.addSelectionListener(() -> {

                if (selection.getTiles().size() < 1)
                {
                    TileLayer.this.remove(spinner);
                }
                else {

                    Tile t = Editor.world.getTileFromPair(selection.getTiles().get(0));

                    spinner.setValue(t.getLayer());

                    TileLayer.this.add(spinner);
                }

                TileLayer.this.repaint();
            });

            spinner.addChangeListener(changeEvent -> {

                for (int i = 0; i < selection.getTiles().size(); ++i)
                {
                    Tile t = Editor.world.getTileFromPair(selection.getTiles().get(i));
                    t.setLayer((Integer) spinner.getValue());
                }
            });
        }
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

            List<TilePair> tiles = selection.getTiles();

            if (tiles != null)
                tiles.get(0).getTileSet().drawselection(selection, 0, 0, g);
        }
    }
}
