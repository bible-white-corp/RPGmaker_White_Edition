package engine.Forms.Pause;

import engine.Engine;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PauseAction extends AbstractAction {
    @Override
    public void actionPerformed(ActionEvent e) {
        Engine.getEngineFrame().getDisplay().escPressed();
    }
}
