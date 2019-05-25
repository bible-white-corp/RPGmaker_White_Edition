package editor.Tiles.actions;

import editor.Editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class createTileSetAction extends AbstractAction {

    private JPanel askPanel;
    private JSpinner tileHeightField;
    private JSpinner tileWidthField;
    private JTextField tsNameField;

    public createTileSetAction() {
        super("Construct TileSet");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JFileChooser fileChooser = new JFileChooser(".");

        if (fileChooser.showOpenDialog(Editor.mainFrame) == JFileChooser.APPROVE_OPTION) {

            if (ask_size() != JOptionPane.OK_OPTION)
                return;

            boolean creation = Editor.world.createTileSet(fileChooser.getSelectedFile().getPath(),
                    getTileWidthField(), getTileHeightField());

            if (!creation) {

                JOptionPane.showMessageDialog(Editor.mainFrame, "IO error cancelled the import",
                        "IO error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(Editor.mainFrame, "Loading Complete!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private int ask_size() {
        tileHeightField = new JSpinner();
        tileHeightField.setPreferredSize(new Dimension(70, 20));
        tileHeightField.setEditor(new JSpinner.NumberEditor(tileHeightField));

        tileWidthField = new JSpinner();
        tileWidthField.setPreferredSize(new Dimension(70, 20));
        tileWidthField.setEditor(new JSpinner.NumberEditor(tileWidthField));

        askPanel = new JPanel();

        askPanel.add(new JLabel("Tile Height:"));
        askPanel.add(tileHeightField);

        askPanel.add(new JLabel("Tile Width:"));
        askPanel.add(tileWidthField);


        int res = 0;
        do {
            res = JOptionPane.showConfirmDialog(Editor.mainFrame, askPanel,
                    "Enter values for the new map", JOptionPane.OK_CANCEL_OPTION);
        } while (getTileHeightField() < 1 && getTileWidthField() < 1);

        return res;
    }

    public int getTileHeightField() {
        return (int) tileHeightField.getValue();
    }

    public int getTileWidthField() {
        return (int) tileWidthField.getValue();
    }
}
