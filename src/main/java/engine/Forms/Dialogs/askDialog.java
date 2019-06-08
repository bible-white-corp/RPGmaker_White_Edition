package engine.Forms.Dialogs;

import editor.Editor;
import editor.Maps.Level;
import editor.Object.ObjectInstantiation;
import editor.ProjectTree.objType;
import engine.Engine;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class askDialog extends AbstractAction {

    public static Object[] answers = {"Bye!"};

    public askDialog() {
        super("askDialog");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ObjectInstantiation player = Engine.world.worldObjects.getPlayer();
        if (player == null)
            return;

        ObjectInstantiation closest = null;
        Level curLevel = Engine.getEngineFrame().getDisplay().getCurLevel();

        Object[] npcs = Engine.world.worldObjects.getInWorldObj().stream()
                .filter(w -> w != null)
                .filter(w -> w.getType() == objType.NPC)
                .filter(w -> (Math.abs(player.getPosition().x - w.getPosition().x)) <=  curLevel.getTileWidth() &&
                        Math.abs((player.getPosition().y - w.getPosition().y)) <= curLevel.getTileHeight())
                .toArray();

        if (npcs.length == 0)
            return;

        ObjectInstantiation cur = (ObjectInstantiation) npcs[0];

        Engine.getEngineFrame().getDisplay().stop();
        JOptionPane.showOptionDialog(Engine.getEngineFrame(), cur.getDialog(), cur.getName(), JOptionPane.YES_OPTION,
                JOptionPane.PLAIN_MESSAGE, new ImageIcon(cur.getCurrentSprite().getImage()), answers, null);
        Engine.getEngineFrame().getDisplay().start();
        Engine.getEngineFrame().getNpcCalculus().start();
    }
}