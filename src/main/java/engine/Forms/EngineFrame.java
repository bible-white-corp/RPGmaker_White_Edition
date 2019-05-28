package engine.Forms;

import engine.Engine;

import javax.swing.*;

public class EngineFrame extends JFrame {

    private GameDisplay display = new GameDisplay();

    public EngineFrame()
    {
        add(display);
    }

    public void setLevel(int index)
    {
        display.setLevel(0);
    }
}
