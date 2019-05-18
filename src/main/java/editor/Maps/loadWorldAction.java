package editor.Maps;

import editor.Editor;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class loadWorldAction extends AbstractAction {

    public loadWorldAction(){
        super("Save all");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Editor.world.levelList.size() == 0)
            return;
        JFileChooser fileChooser = new JFileChooser(".");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (fileChooser.showSaveDialog(Editor.mainFrame) == JFileChooser.APPROVE_OPTION){
            try {
                Editor.world.exportMap(fileChooser.getSelectedFile().getPath());
            } catch (Exception excep) {
                JOptionPane.showMessageDialog(Editor.mainFrame, "IO error cancelled the saved",
                        "IO error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
