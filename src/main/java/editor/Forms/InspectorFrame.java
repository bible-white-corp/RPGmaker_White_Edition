package editor.Forms;

import editor.Editor;
import editor.Tiles.Tile;
import editor.Tiles.TilePair;
import editor.Tiles.TileSet;
import editor.Tools.Selection;

import javax.swing.*;
import java.awt.*;
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

        int state = -1;

        public TileLayer(Selection selection)//#TODO ouais pense a refactor ce truc de 200lignes
        {
            this.selection = selection;

            setLayout(new GridBagLayout());

            add(new JLabel("Name"), EditFrame.get_c(0,0,1,0));
            add(textField, EditFrame.get_c(1,0,1,0));
            add(enableText, EditFrame.get_c(2,0,1,0));

            add(new JLabel("Solid"),EditFrame.get_c(0,1,1,0));
            add(solid, EditFrame.get_c(1,1,1,0));
            add(enableSolid, EditFrame.get_c(2,1,1,0));

            add(new JLabel("Layer"), EditFrame.get_c(0,2,1,0));
            add(spinner, EditFrame.get_c(1,2,1,0));
            add(enableSpinner, EditFrame.get_c(2,2,1,0));

            resetButton();

            selection.addSelectionListener(() -> {

                resetButton();

                if (selection.getTiles().size() == 0)
                {
                    TileLayer.this.repaint();
                    return;
                }

                Tile t = selection.getTiles().get(0) != null ? selection.getTiles().get(0).getTile() : null;

                if (t == null)
                    return;

                textField.setText(t.getName());
                spinner.setValue(t.getLayer());
                solid.setSelected(!t.isWalkable());

                if (selection.getTiles().size() > 1)
                {
                    enableText.setEnabled(true);
                    enableSolid.setEnabled(true);
                    enableSpinner.setEnabled(true);

                    String name_v = selection.getTiles().get(0).getTile().getName();

                    selection.getTiles().forEach(tilePair -> {

                        if (tilePair != null) {
                            String other = tilePair.getTile().getName();

                            if (!Objects.equals(name_v, other)) {

                                enableText.setSelected(true);
                                return;
                            }
                        }
                    });

                    boolean solid_v = selection.getTiles().get(0).getTile().isWalkable();

                    selection.getTiles().forEach(tilePair -> {

                        if (tilePair != null) {

                            if (solid_v != tilePair.getTile().isWalkable()) {
                                enableSolid.setSelected(true);
                                return;
                            }
                        }
                    });

                    int layer_v = selection.getTiles().get(0).getTile().getLayer();

                    selection.getTiles().forEach(tilePair -> {

                        if (tilePair != null && layer_v != tilePair.getTile().getLayer()) {
                            enableSpinner.setSelected(true);
                            return;
                        }
                    });

                    if (enableText.isSelected())
                        textField.setEnabled(false);

                    if (enableSolid.isSelected())
                        solid.setEnabled(false);

                    if (enableSpinner.isSelected())
                        spinner.setEnabled(false);


                }

                state = 0;

                TileLayer.this.repaint();
            });

            enableText.addChangeListener(changeEvent ->  {

                if (state == -1)
                    return;

                if (enableText.isSelected())
                {
                    textField.setEnabled(false);
                }
                else
                {
                    textField.setEnabled(true);
                    String v = selection.getTiles().get(0).getTile().getName();

                    textField.setText(v);

                    for (int i = 0; i < selection.getTiles().size(); ++i)
                    {
                        Tile t = Editor.world.getTileFromPair(selection.getTiles().get(i));
                        t.setName(v);
                    }
                }
            });

            textField.addActionListener(changeEvent -> {

                if (state == -1)
                    return;

                for (int i = 0; i < selection.getTiles().size(); ++i)
                {
                    Tile t = Editor.world.getTileFromPair(selection.getTiles().get(i));
                    t.setName(textField.getText());
                }
            });

            enableSolid.addChangeListener(changeEvent ->  {

                if (state == -1)
                    return;

                if (enableSolid.isSelected())
                {
                    solid.setEnabled(false);
                }
                else
                {
                    solid.setEnabled(true);
                    boolean v = selection.getTiles().get(0).getTile().isWalkable();

                    solid.setSelected(!v);

                    for (int i = 0; i < selection.getTiles().size(); ++i)
                    {
                        Tile t = Editor.world.getTileFromPair(selection.getTiles().get(i));
                        t.setWalkable(v);
                    }
                }
            });

            solid.addChangeListener(changeEvent -> {

                if (state == -1)
                    return;

                for (int i = 0; i < selection.getTiles().size(); ++i)
                {
                    Tile t = Editor.world.getTileFromPair(selection.getTiles().get(i));
                    t.setWalkable(!solid.isSelected());
                }
            });

            enableSpinner.addChangeListener(changeEvent ->  {

                if (state == -1)
                    return;

                if (enableSpinner.isSelected())
                {
                    spinner.setEnabled(false);
                }
                else
                {
                    spinner.setEnabled(true);
                    int v = selection.getTiles().get(0).getTile().getLayer();

                    spinner.setValue(v);

                    for (int i = 0; i < selection.getTiles().size(); ++i)
                    {
                        Tile t = Editor.world.getTileFromPair(selection.getTiles().get(i));
                        t.setLayer(v);
                    }
                }
            });

            spinner.addChangeListener(changeEvent -> {

                if (state == -1)
                    return;

                for (int i = 0; i < selection.getTiles().size(); ++i)
                {
                    Tile t = Editor.world.getTileFromPair(selection.getTiles().get(i));
                    t.setLayer((Integer) spinner.getValue());
                }
            });
        }

        private void resetButton() {

            state = -1;

            textField.setEnabled(true);
            spinner.setEnabled(true);
            solid.setEnabled(true);

            enableText.setEnabled(false);
            enableSolid.setEnabled(false);
            enableSpinner.setEnabled(false);

            textField.setText("");
            spinner.setValue(0);
            solid.setSelected(false);

            enableText.setSelected(false);
            enableSolid.setSelected(false);
            enableSpinner.setSelected(false);

        }
    }

    private class objLayer extends JPanel{

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
                TileSet.drawselection(selection, 0, 0, 150,150, g);
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
