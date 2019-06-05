package engine.Controllers;

import editor.Editor;
import editor.Object.ObjectInstantiation;
import engine.Engine;

import java.awt.*;

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
        Point center = (Point) player.getPosition().clone();
        center.move(player.getCurrentSprite().getX(), player.getCurrentSprite().getY());

        int width = self.getCurrentSprite().getX();
        int height = self.getCurrentSprite().getY();

        Point upperleft = self.getPosition();

        int deltax = center.x - upperleft.x;
        int deltay = center.y - upperleft.y;

        return deltax >= 0 && deltax < width && deltay > 0 && deltay < height;
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
