package editor.Maps;

import javax.swing.*;
import javax.swing.text.StyledEditorKit;
import java.awt.event.ActionEvent;

import editor.Editor;

public class saveWorldAction extends AbstractAction {

    public saveWorldAction(){
        super("Save project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Editor.world.levelList.size() == 0)
            return;

        Boolean success = true;
        if (Editor.world.has_quickPath()){
            success = Editor.world.quick_export();
        }
        else {
            JFileChooser fileChooser = new JFileChooser(".");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            if (fileChooser.showSaveDialog(Editor.mainFrame) == JFileChooser.APPROVE_OPTION) {
                    success = Editor.world.exportMap(fileChooser.getSelectedFile().getPath());
            }
            else
                return;
        }
        if (success){
            JOptionPane.showMessageDialog(Editor.mainFrame, "Save complete",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(Editor.mainFrame, "IO error cancelled the export",
                    "IO error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
