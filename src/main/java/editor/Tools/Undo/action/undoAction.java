package editor.Tools.Undo.action;

import editor.Editor;
import editor.Tools.Undo.DoneAction;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class undoAction extends AbstractAction {
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        DoneAction cur = Editor.world.undo.pop();
        if (cur == null)
            return;
        Editor.world.redo.add(cur.cancelAction());
        Editor.mainFrame.repaint();;
    }
}
