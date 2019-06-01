package editor.Tools.Undo.action;

import editor.Editor;
import editor.Tools.Undo.DoneAction;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class redoAction extends AbstractAction {
    public redoAction() {
        super("Redo");
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        DoneAction cur;
        do {
            cur = Editor.world.redo.pop();
        }
        while (cur != null && cur.size() == 0);
        if (cur == null)
            return;
        cur.cancelAction();
        Editor.mainFrame.repaint();;
    }
}
