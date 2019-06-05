package engine.Forms;

import editor.Object.ObjectInstantiation;
import engine.Controllers.PlayerControl;
import engine.Engine;

import javax.swing.*;

public class EngineFrame extends JFrame {

    private GameDisplay display = new GameDisplay();

    public GameDisplay getDisplay() {
        return display;
    }

    public EngineFrame()
    {
        add(display);
    }

    public void setLevel(int index)
    {
        display.setLevel(index);
    }

    public void start() {

        ObjectInstantiation player = Engine.world.worldObjects.getPlayer();

        if (player != null)
        {
            PlayerControl playerControl = new PlayerControl(player, display.getCamera());

            display.getKeyBoardInput().addKeyBoardListener(playerControl);
        }

        display.start();
    }
}
