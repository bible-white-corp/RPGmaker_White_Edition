package engine.Forms.Pause;

import engine.Engine;

import javax.swing.*;
import java.awt.*;

public class PauseMenu extends JPanel {

    public PauseMenu() {
        setPreferredSize(new Dimension(400,400));
        add(new JButton(new PauseResumeAction(this)));
        add(new JButton(new PauseQuitAction()));

        setVisible(true);
        Engine.getEngineFrame().getDisplay().stop();
    }
}