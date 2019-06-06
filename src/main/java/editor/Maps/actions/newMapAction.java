package editor.Maps.actions;

import editor.Editor;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class newMapAction extends AbstractAction {

    public newMapAction(){
        super("Add map");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        levelPaneMultiInput pane = new levelPaneMultiInput();
        while (pane.getHeightField() < 4 || pane.getWidthField() < 4 ||
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

        Editor.world.addMap(pane.getHeightField(),
                pane.getWidthField(),
                pane.getTileHeightField(), pane.getTileWidthField(), pane.getLevelNameField());
    }
}
