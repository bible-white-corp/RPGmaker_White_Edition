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
                Editor.mainFrame.setLevel(w.levelList.get(0));
                Editor.mainFrame.repaint();

            } catch (Exception excep) {

                excep.printStackTrace();

                JOptionPane.showMessageDialog(Editor.mainFrame, "IO error cancelled the import",
                        "IO error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(Editor.mainFrame, "Loading Complete!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
