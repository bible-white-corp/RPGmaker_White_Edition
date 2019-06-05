package engine.Forms.Pause;

import engine.Engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PauseMenu {

    static Object[] options = {"Exit game", "Resume"};


    public static void perform() {
        int res = JOptionPane.showOptionDialog(Engine.getEngineFrame(), "",
                "Game Paused", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, null);

        if (res == JOptionPane.YES_OPTION)
            PauseQuitAction.perform();
        else
            PauseResumeAction.perform();
    }
}