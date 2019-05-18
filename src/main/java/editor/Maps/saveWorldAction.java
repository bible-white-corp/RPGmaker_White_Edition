package editor.Maps;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class saveWorldAction extends AbstractAction {

    World w;

    public saveWorldAction(World w){
        super("Save all");
        this.w = w;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (w.levelList.size() == 0)
            return;
        JFileChooser fileChooser = new JFileChooser(".");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (fileChooser.showSaveDialog(w.mainFrame) == JFileChooser.APPROVE_OPTION){
            try {
                w.exportMap(fileChooser.getSelectedFile().getPath());
            } catch (Exception excep) {
                JOptionPane.showMessageDialog(w.mainFrame, "IO error cancelled the saved",
                        "IO error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
