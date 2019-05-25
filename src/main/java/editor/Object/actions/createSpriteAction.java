package editor.Object.actions;

import editor.Editor;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class createSpriteAction extends AbstractAction {

    public createSpriteAction() {
        super("New sprite");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Editor.editFrame.tabbedPane.setSelectedIndex(2);
        if (Editor.editFrame.editionFrame.is_set()) {
            Editor.editFrame.editionFrame.active();
        }
        else {
            JOptionPane.showMessageDialog(Editor.mainFrame,
                    "Please, select a SpritesSheet before trying to create sprites",
                    "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
}