package editor.Maps.actions;

import editor.Editor;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class newWorldAction extends AbstractAction {

    public newWorldAction(){
        super("New project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String projectName = JOptionPane.showInputDialog(Editor.editFrame,
                "Name the new project:", "New project", JOptionPane.INFORMATION_MESSAGE);
        if (projectName == null)
            return;

        levelPaneMultiInput pane = new levelPaneMultiInput();
        while (pane.getHeightField() <= 3 || pane.getWidthField() <= 3 ||
                pane.getTileHeightField() <= 15 || pane.getTileWidthField() <= 15)
        {
            if (pane.getResult() != JOptionPane.OK_OPTION)
                return;
            JOptionPane.showMessageDialog(Editor.editFrame, "Min values are 4 x 4 and 16 x 16",
                    "Prompt error", JOptionPane.ERROR_MESSAGE);
            pane = new levelPaneMultiInput();
        }

        if (pane.getResult() != JOptionPane.OK_OPTION)
            return;

        Editor.world.reset(projectName);

        Editor.world.addMap(pane.getHeightField(), pane.getWidthField(),
                pane.getTileHeightField(), pane.getTileWidthField(), pane.getLevelNameField());

        Editor.mainFrame.setLevel(0);
        Editor.editFrame.tileSetFrame.display.changeTileSet(0);
        Editor.mainFrame.repaint();
    }
}
