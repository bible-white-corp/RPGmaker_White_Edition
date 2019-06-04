package engine.Controllers;

import editor.Object.ObjectInstantiation;
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

    private boolean canMove(Point p)
    {
        for (int i = 0; i < 10; ++i) {

            TilePair tilePair = Engine.world.levelList.get(0).getTilePixel(p.x, p.y, i);

            if (tilePair != null && tilePair.getTile().isWalkable() == false)
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

    @Override
    protected void computeKey() {



        if (isPlayerKeyPressed()) {

            Point coords = (Point) player.getPosition().clone();

            int player_speed = 2;

            if (input.IsPressed(GameKey.Up)) {

                coords.y -= player_speed;
                player.setAnimation(2);
            }
            else if (input.IsPressed(GameKey.Down))
            {
                coords.y += player_speed;
                player.setAnimation(1);
            }
            else if (input.IsPressed(GameKey.Left))
            {
                coords.x -= player_speed;
                player.setAnimation(3);
            }
            else if (input.IsPressed(GameKey.Right))
            {
                coords.x += player_speed;
                player.setAnimation(4);
            }

            player.getCurrentAnimation().getNext();

            if (canMove(coords)) {
                player.setPosition(coords);
                camera.setFocus(coords);
            }

            countdown = 5;
        }
        else
        {
            if (countdown == 0)
                player.getCurrentAnimation().getFirstSprite();
            else
                countdown --;
        }
    }
}
