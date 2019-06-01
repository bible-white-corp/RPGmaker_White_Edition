package editor.Tools.Undo.action;

import editor.Editor;
import editor.Tools.Undo.DoneAction;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class redoAction extends AbstractAction {
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        DoneAction cur = Editor.world.redo.pop();
        if (cur == null)
            return;
        cur.cancelAction();
        Editor.mainFrame.repaint();;
    }
}
