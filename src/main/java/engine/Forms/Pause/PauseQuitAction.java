package engine.Forms.Pause;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static java.lang.System.exit;

public class PauseQuitAction extends AbstractAction {

    public PauseQuitAction() {
        super("Quit");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        exit(0);
    }
}
