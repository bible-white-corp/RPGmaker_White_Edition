package engine.Controllers;

import editor.Object.ObjectInstantiation;
import engine.Engine;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class TeleporterControl extends KeyBoardInput.TeleporterInputListener {

    Camera camera;
    ObjectInstantiation player;
    ObjectInstantiation self;

    boolean isInside = true;

    static List<TeleporterControl> others = new LinkedList<TeleporterControl>();

    public TeleporterControl(ObjectInstantiation player, ObjectInstantiation obj, Camera camera) {

        this.self = obj;
        this.player = player;
        this.camera = camera;

        others.add(this);
    }

    public boolean playerCollide()
    {
        Point center = (Point) player.getPosition().clone();
        center.translate(player.getCurrentSprite().getDimension().width / 2, player.getCurrentSprite().getDimension().height / 2);

        int width = self.getCurrentSprite().getDimension().width;
        int height = self.getCurrentSprite().getDimension().height;

        Point upperleft = self.getPosition();

        int deltax = center.x - upperleft.x;
        int deltay = center.y - upperleft.y;

        return deltax >= 0 && deltax < width && deltay >= 0 && deltay < height;
    }

    @Override
    public void computeKey() {

        if (Engine.getEngineFrame().getDisplay().getLevel() != self.getLevelIndex())
            return;

        boolean newInside = playerCollide();

        if (newInside && !isInside)
        {
            if (self.getSibling() == null)
                return;

            others.forEach(other -> other.isInside = true);

            Engine.getEngineFrame().getDisplay().setLevel(self.getSibling().getLevelIndex());
            player.setPosition(self.getSibling().getPosition());
            player.setLevelIndex(self.getSibling().getLevelIndex());
            camera.forceFocus(self.getSibling().getPosition());
        }

        isInside = newInside;
    }
}
