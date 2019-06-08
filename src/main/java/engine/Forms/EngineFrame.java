package engine.Forms;

import editor.Object.ObjectInstantiation;
import editor.ProjectTree.objType;
import engine.Controllers.PlayerControl;
import engine.Controllers.TeleporterControl;
import engine.Engine;

import javax.swing.*;
import java.awt.event.WindowEvent;

public class EngineFrame extends JFrame {

    private GameDisplay display = new GameDisplay();
    private NpcCalculus npcCalculus;

    public GameDisplay getDisplay() {
        return display;
    }

    public NpcCalculus getNpcCalculus() {
        return npcCalculus;
    }

    public EngineFrame(long elasped_time, int npcSpeed)
    {
        add(display);
        npcCalculus = new NpcCalculus(elasped_time, npcSpeed);
    }

    public void setLevel(int index)
    {
        display.setLevel(index);
    }

    public void start() {

        ObjectInstantiation player = Engine.world.worldObjects.getPlayer();

        for (ObjectInstantiation obj : Engine.world.worldObjects.getInWorldObj())
        {
            if (obj != null && obj.getType() == objType.TELEPORTER)
                display.getKeyBoardInput().addKeyBoardListener(new TeleporterControl(player, obj, display.getCamera()));
        }

        if (player != null)
        {
            PlayerControl playerControl = new PlayerControl(player, display.getCamera());

            display.getKeyBoardInput().addKeyBoardListener(playerControl);

            setLevel(player.getLevelIndex());
        }

        display.start();
        npcCalculus.start();

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(WindowEvent winEvt) {

                display.stop();
                npcCalculus.stop();

                System.exit(0);
            }
        });
    }


}
