package engine.Controllers;

import editor.Editor;
import editor.Maps.Level;
import editor.Object.ObjectInstantiation;
import editor.Object.animationType;
import editor.Tiles.TilePair;
import engine.Controllers.KeyBoardInput.PlayerInputListener;
import engine.Engine;

import java.awt.*;

public class PlayerControl extends PlayerInputListener {

    ObjectInstantiation player;
    Camera camera;

    int countdown = 0;

    public PlayerControl(ObjectInstantiation player, Camera camera) {

        this.player = player;
        this.camera = camera;

        camera.setFocus(player.getPosition());
    }

    public static boolean canMove(Point p, int levelIndex, ObjectInstantiation self)
    {
        Level level = Editor.world.levelList.get(levelIndex);

        int width = self.getCurrentSprite().getDimension().width - 1;
        int height = self.getCurrentSprite().getDimension().height - 1;

        if (p.x < 0 || p.x + width >= level.getTileWidth() * level.getWidth()
         || p.y < 0 || p.y + height >= level.getTileHeight() * level.getHeight())
            return false;

        for (int i = 0; i < 10; ++i) {

            TilePair tilePair_1 = level.getTilePixel(p.x, p.y, i);
            TilePair tilePair_2 = level.getTilePixel(p.x + width, p.y, i);
            TilePair tilePair_3 = level.getTilePixel(p.x, p.y + height, i);
            TilePair tilePair_4 = level.getTilePixel(p.x + width, p.y + height, i);

            if (tilePair_1 != null && !tilePair_1.getTile().isWalkable())
                return false;
            if (tilePair_2 != null && !tilePair_2.getTile().isWalkable())
                return false;
            if (tilePair_3 != null && !tilePair_3.getTile().isWalkable())
                return false;
            if (tilePair_4 != null && !tilePair_4.getTile().isWalkable())
                return false;
        }

        return true;
    }

    private boolean isPlayerKeyPressed()
    {
        return  input.IsPressed(GameKey.Down) ||
                input.IsPressed(GameKey.Up) ||
                input.IsPressed(GameKey.Left) ||
                input.IsPressed(GameKey.Right);
    }

    public boolean move(Point coords, Point origin)
    {
        if (PlayerControl.canMove(coords, Engine.getEngineFrame().getDisplay().getLevel(), player)) {
            player.setPosition(coords);
            camera.setFocus(coords);

            return true;
        }
        else
        {
            Point direction = (Point) coords.clone();
            direction.translate(-origin.x, -origin.y);

            if (Math.abs(direction.x) <= 1 && direction.y == 0)
                return false;

            if (Math.abs(direction.y) <= 1 && direction.x == 0)
                return false;

            Point middle = new Point(origin.x + direction.x / 2, origin.y + direction.y / 2);

            if (move(middle, origin))
                return move(coords, middle);
        }

        return false;
    }

    @Override
    public void computeKey() {

        if (isPlayerKeyPressed()) {

            Point coords = (Point) player.getPosition().clone();

            int player_speed = input.IsPressed(GameKey.Shift) ? 8 : 4;

            if (input.IsPressed(GameKey.Ctrl))
                player_speed = 64;

            if (input.IsPressed(GameKey.Up)) {

                coords.y -= player_speed;
                player.setAnimation(animationType.BACKWARD);
            }
            else if (input.IsPressed(GameKey.Down))
            {
                coords.y += player_speed;
                player.setAnimation(animationType.FORWARD);
            }
            else if (input.IsPressed(GameKey.Left))
            {
                coords.x -= player_speed;
                player.setAnimation(animationType.LEFT);
            }
            else if (input.IsPressed(GameKey.Right))
            {
                coords.x += player_speed;
                player.setAnimation(animationType.RIGHT);
            }

            player.getCurrentAnimation().setNext();

            move(coords, player.getPosition());

            countdown = 5;
        }
    }
}
