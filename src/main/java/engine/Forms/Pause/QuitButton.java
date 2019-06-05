package engine.Forms.Pause;

import javax.swing.*;
import java.awt.*;

public class QuitButton extends JButton {
    public QuitButton() {
        super(new PauseQuitAction());
        setPreferredSize(new Dimension(100,50));
    }
}
