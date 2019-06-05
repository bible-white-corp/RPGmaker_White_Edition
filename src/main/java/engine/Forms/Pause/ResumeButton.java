package engine.Forms.Pause;

import javax.swing.*;
import java.awt.*;

public class ResumeButton extends JButton {
    public ResumeButton() {
        super(new PauseResumeAction());
        setPreferredSize(new Dimension(100,50));
    }
}
