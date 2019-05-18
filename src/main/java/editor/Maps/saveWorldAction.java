package editor.Maps;

import javax.swing.*;
import java.awt.event.ActionEvent;
import editor.Editor;

public class saveWorldAction extends AbstractAction {

    public saveWorldAction(){
        super("Import project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Editor.world.levelList.size() == 0)
            return;
        JFileChooser fileChooser = new JFileChooser(".");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (fileChooser.showOpenDialog(Editor.mainFrame) == JFileChooser.APPROVE_OPTION){
            try {
                World w = World.importWorld(fileChooser.getSelectedFile().getPath());
                Editor.world = w;
            } catch (Exception excep) {
                JOptionPane.showMessageDialog(Editor.mainFrame, "IO error cancelled the import",
                        "IO error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
