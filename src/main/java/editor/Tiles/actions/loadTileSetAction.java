package editor.Tiles.actions;

import editor.Editor;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class loadTileSetAction extends AbstractAction {

    public loadTileSetAction(){
        super("Import TileSet");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JFileChooser fileChooser = new JFileChooser(".");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (fileChooser.showOpenDialog(Editor.mainFrame) == JFileChooser.APPROVE_OPTION){

                if (!Editor.world.importTileSet(fileChooser.getSelectedFile().getPath())) {

                    JOptionPane.showMessageDialog(Editor.mainFrame, "IO error cancelled the import",
                            "IO error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

            JOptionPane.showMessageDialog(Editor.mainFrame, "Loading Complete!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
