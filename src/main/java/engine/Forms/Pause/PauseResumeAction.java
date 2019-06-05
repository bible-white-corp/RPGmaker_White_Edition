package engine.Forms.Pause;

import engine.Engine;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PauseResumeAction extends AbstractAction {

    private PauseMenu pauseMenu;

    public PauseResumeAction(PauseMenu pauseMenu) {
        super("Resume");
        this.pauseMenu = pauseMenu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Engine.getEngineFrame().start();
        Engine.getEngineFrame().remove(pauseMenu);
    }
}
