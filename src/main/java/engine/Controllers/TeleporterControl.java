package engine.Controllers;

import editor.Editor;
import editor.Object.ObjectInstantiation;
import engine.Engine;

public class TeleporterControl extends KeyBoardInput.TeleporterInputListener {

    ObjectInstantiation player;
    ObjectInstantiation self;

    boolean isInside = true;

    public TeleporterControl(ObjectInstantiation player, ObjectInstantiation obj) {

        this.self = obj;
        this.player = player;
    }

    public boolean playerCollide()
    {
        return false;
    }

    @Override
    public void computeKey() {

        if (Engine.getEngineFrame().getDisplay().getLevel() != self.getLevelIndex())
            return;

        boolean newInside = playerCollide();

        if (newInside && !isInside)
        {
            Engine.getEngineFrame().getDisplay().setLevel(self.getSibling().getLevelIndex());
            player.setPosition(self.getSibling().getPosition());
        }

        isInside = newInside;
    }
}
