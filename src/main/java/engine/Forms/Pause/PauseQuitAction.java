package engine.Forms.Pause;

import editor.Editor;
import engine.Engine;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static java.lang.System.exit;

public class PauseQuitAction extends AbstractAction {

    private PauseMenu pauseMenu;

    public PauseQuitAction(PauseMenu pauseMenu) {
        super("Quit");
        this.pauseMenu = pauseMenu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(Engine.getEngineFrame(),
                "Are you sur you want to quit the game?",
                "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)) {
            Engine.getEngineFrame().dispose();
        }
    }
}