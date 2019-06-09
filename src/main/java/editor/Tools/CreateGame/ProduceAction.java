package editor.Tools.CreateGame;

import editor.Editor;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ProduceAction extends AbstractAction {
    public ProduceAction() {
        super("Export my game");
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JFileChooser fileChooser = new JFileChooser(".");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (fileChooser.showSaveDialog(Editor.editFrame) == JFileChooser.APPROVE_OPTION) {
            ProduceJar produceJar = new ProduceJar();
            try {
                produceJar.produce(fileChooser.getSelectedFile().getPath());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(Editor.editFrame, "IO Error",
                        "Could not export your game :(", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(Editor.editFrame, "Game exported at "
                    + fileChooser.getSelectedFile().getPath(), "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
