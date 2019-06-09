package editor.Maps.actions;

import editor.Editor;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class saveWorldAction extends AbstractAction {

    private boolean quick;

    public saveWorldAction(String buttonName, boolean is_quick){
        super(buttonName);
        this.quick = is_quick;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Editor.world.levelList.size() == 0)
            return;

        Boolean success = true;
        if (Editor.world.has_quickPath() && quick){
            success = Editor.world.quick_export();
        }
        else {
            JFileChooser fileChooser = new JFileChooser(".");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            if (fileChooser.showSaveDialog(Editor.editFrame) == JFileChooser.APPROVE_OPTION) {
                    success = Editor.world.exportMap(fileChooser.getSelectedFile().getPath());
            }
            else
                return;
        }
        if (success){
            JOptionPane.showMessageDialog(Editor.editFrame, "Save complete",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(Editor.editFrame, "IO error cancelled the export",
                    "IO error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
