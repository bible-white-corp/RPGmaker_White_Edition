package engine.Forms.Pause;

import engine.Engine;

import javax.swing.*;

public class PauseMenu extends JPanel {

    public PauseMenu() {
        add(new JButton("Resume"));
        add(new JButton("Quit"));

        Engine.getEngineFrame().getDisplay().stop();
    }
}
