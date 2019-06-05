package engine.Forms.Pause;

import engine.Engine;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PauseResumeAction extends AbstractAction {

    public PauseResumeAction() {
        super("Resume");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Engine.getEngineFrame().getDisplay().exitMenu();
    }
}
