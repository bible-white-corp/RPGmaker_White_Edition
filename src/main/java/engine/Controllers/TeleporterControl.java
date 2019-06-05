package engine.Controllers;

import editor.Object.ObjectInstantiation;

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

        boolean newInside = playerCollide();

        if (newInside && !isInside)
        {
            //FixMe Teleport
        }

        isInside = newInside;
    }
}
