package editor.Maps;

import editor.Editor;
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

        if (fileChooser.showOpenDialog(Editor.mainFrame) == JFileChooser.APPROVE_OPTION){
            try {
                World w = World.importWorld(fileChooser.getSelectedFile().getPath());
                Editor.world = w;
            } catch (Exception excep) {
                JOptionPane.showMessageDialog(Editor.mainFrame, "IO error cancelled the saved",
                        "IO error", JOptionPane.ERROR_MESSAGE);
            }
            JOptionPane.showMessageDialog(Editor.mainFrame, "IO error cancelled the saved",
                    "IO error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
