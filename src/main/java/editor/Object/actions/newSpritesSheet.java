package editor.Object.actions;

import editor.Editor;
import editor.Maps.World;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class newSpritesSheet extends AbstractAction {
    public newSpritesSheet(){
        super("Import project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser(".");

        if (fileChooser.showOpenDialog(Editor.mainFrame) == JFileChooser.APPROVE_OPTION){
            try {
                Editor.world.worldObjects.addSpriteSheet(fileChooser.getSelectedFile().getPath());

            } catch (Exception excep) {

                excep.printStackTrace();

                JOptionPane.showMessageDialog(Editor.mainFrame, "IO error cancelled the import",
                        "IO error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(Editor.mainFrame, "Loading Complete!",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
