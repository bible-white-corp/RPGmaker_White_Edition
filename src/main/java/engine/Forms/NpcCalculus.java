package engine.Forms;

import editor.Editor;
import editor.Object.ObjectInstantiation;
import editor.Object.PathFinding.MoveType;
import editor.Object.animationType;
import editor.ProjectTree.objType;
import engine.Controllers.PlayerControl;
import engine.Engine;

import java.awt.*;

public class NpcCalculus implements Runnable {

    private long elapse_time = 50;
    private int speed;
    private Thread thread;

    public NpcCalculus(long elapse_time, int speed) {
        this.elapse_time = elapse_time;
        this.speed = speed;
    }

    private void changeAnimation(ObjectInstantiation instantiation, Point newPosition) {
        int orientation = -1;//0: north, 1: east, 2: south, 3: west, -1: no change
        Point prevPos = instantiation.getPosition();

        if (newPosition.x > prevPos.x)
            orientation = 0;//north
        else if (newPosition.x < prevPos.x)
            orientation = 2;//south
        else if (newPosition.y > prevPos.y)
            orientation = 1;//east
        else if (newPosition.y < prevPos.y)
            orientation = 3;//west
        switch (orientation) {
            case 0:
                if (instantiation.getCurAnimType() == animationType.BACKWARD)
                    instantiation.getCurrentAnimation().setNext();
                else
                    instantiation.setAnimation(animationType.BACKWARD);
                break;
            case 1:
                if (instantiation.getCurAnimType() == animationType.LEFT)
                    instantiation.getCurrentAnimation().setNext();
                else
                    instantiation.setAnimation(animationType.LEFT);
                break;
            case 2:
                if (instantiation.getCurAnimType() == animationType.FORWARD)
                    instantiation.getCurrentAnimation().setNext();
                else
                    instantiation.setAnimation(animationType.FORWARD);
                break;
            case 3:
                if (instantiation.getCurAnimType() == animationType.RIGHT)
                    instantiation.getCurrentAnimation().setNext();
                else
                    instantiation.setAnimation(animationType.RIGHT);
                break;
            default:
                instantiation.getCurrentAnimation().setNext();
        }
    }

    private Point calculMov(ObjectInstantiation instantiation) {
        Point dest = instantiation.getPath().getCur();
        Point curPos = instantiation.getPosition();
        if (instantiation.getPath().getCur() == curPos) {
            if (instantiation.getPath().setNext() == null) {
                instantiation.getPath().setType(MoveType.idle);
            }
            return curPos;
        }
        int x_mov = dest.x - curPos.x;
        int y_mov = dest.y - curPos.y;

        Point newPosition;
        int sign;
        if (Math.abs(x_mov) > Math.abs(y_mov)) {
            sign = x_mov >= 0 ? 1 : -1;
            x_mov = x_mov / speed == 0 ? x_mov : speed * sign;
            newPosition = new Point(x_mov, curPos.y);
        } else {
            sign = y_mov >= 0 ? 1 : -1;
            y_mov = y_mov / speed == 0 ? y_mov : speed * sign;
            newPosition = new Point(curPos.x, y_mov);
        }
        return newPosition;
    }

    private void tick() {
        int level = Engine.getEngineFrame().getDisplay().getLevel();
        Object[] npcs = Engine.world.worldObjects.getInWorldObj().stream()
                .filter(w -> w != null)
                .filter(w -> w.getType() == objType.NPC)
                .filter(w -> w.getLevelIndex() == level)
                .toArray();

        ObjectInstantiation cur;
        for (Object obj : npcs)
        {
            cur = (ObjectInstantiation) obj;

            if (cur.getPath().getType() == MoveType.idle)
            {
                cur.getCurrentAnimation().setNext();
            }
            else
            {
                Point dest = calculMov(cur);
                if (PlayerControl.canMove(dest, level))
                    cur.setPosition(dest);
                changeAnimation(cur, dest);
            }
        }
    }

    @Override
    public void run() {
        while (thread != null) {
            try {
                Thread.sleep(elapse_time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tick();
        }
    }

    public void stop() {
        thread = null;
    }

    public void start() {
        thread = new Thread(this);
        thread.start();
    }
}
