package editor.Forms;

import editor.Editor;
import editor.Tiles.Tile;
import editor.Tiles.TilePair;
import editor.Tools.Selection;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Objects;

public class InspectorFrame extends JPanel {

    public InspectorFrame() {

        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(300,0));

        initialize();
    }

    public void initialize()
    {
        add(new TilePanel(Editor.getSelection()), new GridBagConstraints(0,0,1,1,0,0,10,0,new Insets(20,20,20,20), 0,0));
        add(new JSeparator(), EditFrame.get_c(0,1,1,0));
        add(new TileLayer(Editor.getSelection()), new GridBagConstraints(0,2,1,1,1,0,11,2,new Insets(20,20,20,20), 0,0));
        add(new JSeparator(), EditFrame.get_c(0,3,1,0));
        add(new TileMatter(), EditFrame.get_c(0,4,1,1));
    }

    private class TileLayer extends JPanel
    {
        Selection selection;

        JTextField textField = new JTextField();
        JCheckBox solid = new JCheckBox();
        JSpinner spinner = new JSpinner(new SpinnerNumberModel(0,0,10,1));

        JCheckBox enableText = new JCheckBox();
        JCheckBox enableSolid = new JCheckBox();
        JCheckBox enableSpinner = new JCheckBox();

        public TileLayer(Selection selection) {

            this.selection = selection;

            setLayout(new GridBagLayout());

            add(new JLabel("Name"), EditFrame.get_c(0, 0, 1, 0));
            add(textField, EditFrame.get_c(1, 0, 1, 0));
            add(enableText, EditFrame.get_c(2, 0, 1, 0));

            add(new JLabel("Solid"), EditFrame.get_c(0, 1, 1, 0));
            add(solid, EditFrame.get_c(1, 1, 1, 0));
            add(enableSolid, EditFrame.get_c(2, 1, 1, 0));

            add(new JLabel("Layer"), EditFrame.get_c(0, 2, 1, 0));
            add(spinner, EditFrame.get_c(1, 2, 1, 0));
            add(enableSpinner, EditFrame.get_c(2, 2, 1, 0));

            selection.addSelectionListener(selectionListener);

            initialize();
        }

        Selection.SelectionListener selectionListener = () -> {

            resetButton();

            if (selection.getTiles().size() == 0)
            {
                TileLayer.this.repaint();
                return;
            }

            Tile t = selection.getTiles().get(0).getTile();

            textField.setText(t.getName());
            setSliderValue(t.getLayer());
            solid.setSelected(!t.isWalkable());

            if (selection.getTiles().size() > 1)
            {
                enableText.setEnabled(true);
                enableSolid.setEnabled(true);
                enableSpinner.setEnabled(true);

                enableText.setSelected(selection.getTiles().stream().map(tilePair -> tilePair.getTile().getName()).distinct().limit(2).count() > 1);
                enableSolid.setSelected(selection.getTiles().stream().map(tilePair -> tilePair.getTile().isWalkable()).distinct().limit(2).count() > 1);
                enableSpinner.setSelected(selection.getTiles().stream().map(tilePair -> tilePair.getTile().getLayer()).distinct().limit(2).count() > 1);

                textField.setEnabled(!enableText.isSelected());
                solid.setEnabled(!enableSolid.isSelected());
                spinner.setEnabled(!enableSpinner.isSelected());
            }

            TileLayer.this.repaint();
        };

        public void initialize()
        {
            resetButton();

            enableText.addActionListener(changeEvent ->  {

                textField.setEnabled(enableText.isSelected());

                if (!enableText.isSelected())
                {
                    String v = selection.getTiles().get(0).getTile().getName();
                    selection.getTiles().stream().forEach(tilePair -> tilePair.getTile().setName(v));
                }
            });

            textField.addActionListener(changeEvent -> selection.getTiles().forEach(tilePair -> tilePair.getTile().setName(textField.getName())));

            enableSolid.addActionListener(changeEvent ->  {

                solid.setEnabled(!enableSolid.isSelected());

                if (!enableSolid.isSelected())
                {
                    boolean v = selection.getTiles().get(0).getTile().isWalkable();
                    selection.getTiles().forEach(tilePair -> tilePair.getTile().setWalkable(v));
                }
            });

            solid.addActionListener(changeEvent -> selection.getTiles().forEach(tilePair -> tilePair.getTile().setWalkable(!solid.isSelected())));

            enableSpinner.addActionListener(changeEvent ->  {

                spinner.setEnabled(!enableSpinner.isSelected());

                if (!enableSpinner.isSelected())
                {
                    int v = selection.getTiles().get(0).getTile().getLayer();
                    selection.getTiles().forEach(tilePair -> tilePair.getTile().setLayer(v));
                }
            });

            spinner.addChangeListener(changeEvent -> selection.getTiles().forEach(tilePair -> tilePair.getTile().setLayer((Integer) spinner.getValue())));
        }

        private void setSliderValue(int v) {

            ChangeListener[] listeners = spinner.getChangeListeners();

            spinner.removeChangeListener(listeners[0]);
            spinner.setValue(v);
            spinner.addChangeListener(listeners[0]);
        }

        private void resetButton() {

            textField.setEnabled(true);
            spinner.setEnabled(true);
            solid.setEnabled(true);

            enableText.setEnabled(false);
            enableSolid.setEnabled(false);
            enableSpinner.setEnabled(false);

            textField.setText("");
            setSliderValue(0);
            solid.setSelected(false);

            enableText.setSelected(false);
            enableSolid.setSelected(false);
            enableSpinner.setSelected(false);
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

            g.drawRect(0,0,149,149);

            List<TilePair> tiles = selection.getTiles();

            if (tiles != null)
                tiles.get(0).getTileSet().drawselection(selection, 0, 0, 150,150, g);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(150,150);
        }
    }

    private class TileMatter extends JPanel {

        public TileMatter()
        {
        }

    }
}
