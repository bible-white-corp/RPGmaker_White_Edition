package editor.Tools.Undo.action;

import editor.Editor;
import editor.Tools.Undo.DoneAction;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class undoAction extends AbstractAction {
    public undoAction() {
        super("Undo");
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        DoneAction cur;
        do {
            cur = Editor.world.undo.pop();
        }
        while (cur != null && cur.size() == 0);
        if (cur == null)
            return;
        Editor.world.redo.add(cur.cancelAction());
        Editor.mainFrame.repaint();
    }
}