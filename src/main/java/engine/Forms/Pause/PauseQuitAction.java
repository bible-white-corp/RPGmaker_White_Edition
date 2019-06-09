package engine.Forms.Pause;

import engine.Engine;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static java.lang.System.exit;

public class PauseQuitAction extends AbstractAction{

    public static void perform() {
        if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(Engine.getEngineFrame(),
                "Are you sur you want to quit the game?",
                "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)) {
            Engine.getEngineFrame().dispose();
            if (Engine.gamemode){
                exit(0);
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        perform();
    }
}