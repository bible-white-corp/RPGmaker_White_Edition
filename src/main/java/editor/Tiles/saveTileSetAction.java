package editor.Tiles;

import editor.Editor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Vector;

public class saveTileSetAction extends AbstractAction {


    public saveTileSetAction() {
        super("Export TileSet");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Editor.world.tileSetList.size() == 0)
            return;

        boolean success = false;

        Vector<String> ts_list = new Vector<>();
        for (TileSet ts : Editor.world.tileSetList)
            ts_list.add(ts.getName());

        String res = (String) JOptionPane.showInputDialog(Editor.mainFrame,
                "Select a TileSet to export", "Export TileSet",
                JOptionPane.INFORMATION_MESSAGE, null, ts_list.toArray(),
                ts_list.get(0));

        if (res == null || res == "")
            return;

        JFileChooser fileChooser = new JFileChooser(".");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        //#TODO make user choose which tileset

        if (fileChooser.showSaveDialog(Editor.mainFrame) == JFileChooser.APPROVE_OPTION) {
            int index = Editor.world.tileSetList.indexOf(res);
            if (index == -1){
                success = false;
            }
            else {
                try {
                    Editor.world.tileSetList.get(index).exportSet(fileChooser.getSelectedFile().getPath());
                } catch (IOException ex) {
                    ex.printStackTrace();
                    success = false;
                }
            }
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
