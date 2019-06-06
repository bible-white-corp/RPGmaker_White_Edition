package editor.Maps.actions;

import editor.Editor;
import editor.Maps.World;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class loadWorldAction extends AbstractAction {

    public loadWorldAction(){
        super("Import project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JFileChooser fileChooser = new JFileChooser(".");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (fileChooser.showOpenDialog(Editor.editFrame) == JFileChooser.APPROVE_OPTION){
            try {
                World w = World.importWorld(fileChooser.getSelectedFile().getPath());
                Editor.world = w;
                Editor.editFrame.tileSetFrame.display.changeTileSet(0);
                Editor.mainFrame.setLevel(0);
                Editor.mainFrame.repaint();
                Editor.editFrame.editionFrame.setSheet(0);
                Editor.world.projectTree.reload();
                Editor.world.objTree.reload();

            } catch (Exception excep) {

                excep.printStackTrace();

                JOptionPane.showMessageDialog(Editor.editFrame, "IO error cancelled the import",
                        "IO error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(Editor.editFrame, "Loading Complete!",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
