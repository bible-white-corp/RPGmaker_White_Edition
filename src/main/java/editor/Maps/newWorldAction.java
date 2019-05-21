package editor.Maps;

import editor.Editor;
import editor.Forms.EditFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class newWorldAction extends AbstractAction {

    public newWorldAction(){
        super("New project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String projectName = JOptionPane.showInputDialog(Editor.mainFrame,
                "Name the new project:", "New project", JOptionPane.INFORMATION_MESSAGE);
        if (projectName == null)
            return;

        levelPaneMultiInput pane = new levelPaneMultiInput();
        while (pane.getHeightField() <= 0 || pane.getWidthField() <= 0 ||
                pane.getTileHeightField() <= 0 || pane.getTileWidthField() <= 0)
        {
            if (pane.getResult() != JOptionPane.OK_OPTION)
                return;
            JOptionPane.showMessageDialog(Editor.mainFrame, "Min value is 1",
                    "Prompt error", JOptionPane.ERROR_MESSAGE);
            pane = new levelPaneMultiInput();
        }

        if (pane.getResult() != JOptionPane.OK_OPTION)
            return;

        World w = new World(projectName);
        Editor.world = w;
        Editor.world.projectTree.reload();
        Editor.editFrame.tileSetFrame.display.changeTileSet(0);

        w.addMap(pane.getHeightField(), pane.getWidthField(),
                pane.getTileHeightField(), pane.getTileWidthField(), pane.getLevelNameField());

        Editor.mainFrame.setLevel(0);
        Editor.mainFrame.repaint();
    }
}
