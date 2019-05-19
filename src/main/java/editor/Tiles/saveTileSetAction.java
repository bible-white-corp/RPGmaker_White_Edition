package editor.Tiles;

import editor.Editor;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class saveTileSetAction extends AbstractAction {


    public saveTileSetAction() {
        super("Export TileSet");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Editor.world.tileSetList.size() == 0)
            return;

        boolean success = false;

        JFileChooser fileChooser = new JFileChooser(".");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        //#TODO make user choose which tileset

        if (fileChooser.showSaveDialog(Editor.mainFrame) == JFileChooser.APPROVE_OPTION) {
        } else
            return;
        if (success) {
            JOptionPane.showMessageDialog(Editor.mainFrame, "Save complete",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(Editor.mainFrame, "IO error cancelled the export",
                    "IO error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
