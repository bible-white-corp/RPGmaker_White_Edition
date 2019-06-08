package editor.Object.PathFinding;

import editor.Editor;
import editor.Maps.Level;
import editor.Object.ObjectInstantiation;
import engine.Controllers.PlayerControl;
import engine.Engine;


import java.awt.*;
import java.util.Random;
import java.util.Vector;

public class PathSettings {
    Vector<Point> paths;
    transient int curPath = 1;
    transient int curIt = 0;
    MoveType type;
    Random random;
    int levelIndex;
    int parentObj;

    public PathSettings(MoveType type, Point coord, int levelIndex, int objIndex) {
        this.type = type;
        paths = new Vector<>();
        paths.add(coord);
        paths.add(coord);
        this.levelIndex = levelIndex;
        random = new Random();
        parentObj = objIndex;
    }

    public MoveType getType() {
        return type;
    }

    public void setType(MoveType type) {
        this.type = type;
    }

    public Point getCur() {
        if (type == MoveType.idle)
            return paths.get(0);
        return paths.get(curPath);
    }

    public Point setNext() {
        if (type == MoveType.simple_trip) {
            type = MoveType.idle;
        } else if (type == MoveType.two_way) {
            if (++curPath != paths.size()) {
                return getCur();
            }
            type = MoveType.idle;
        } else if (type == MoveType.infinite) {
            if (curIt >= 1) {
                if (--curPath < 0) {
                    curIt = 0;
                    curPath += 2;
                }
                return getCur();
            } else {
                if (++curPath >= paths.size()) {
                    curIt = 1;
                    curPath -= 2;
                }
                return getCur();
            }
        } else if (type == MoveType.random) {
            Level l = Editor.world.levelList.get(levelIndex);
            Point p;
            ObjectInstantiation parent = Engine.world.worldObjects.getInWorldObj().get(parentObj);
            Point curPos = parent.getPosition();
            Level level = Engine.world.levelList.get(Engine.getEngineFrame().getDisplay().getLevel());
            int x, y, r;
            do {
                r = random.nextInt(4);
                switch (r) {
                    case 0:
                        x = 1;
                        y = 0;
                        break;
                    case 1:
                        x = 0;
                        y = 1;
                        break;
                    case 2:
                        x = -1;
                        y = 0;
                        break;
                    default:
                        x = 0;
                        y = -1;
                        break;
                }
                p = new Point(curPos.x + x * level.getTileWidth(), curPos.y + y * level.getTileHeight());
            } while (!PlayerControl.canMove(p, levelIndex, parent));
            paths.set(0, p);
            return paths.get(0);
        }
        return null;
    }

    public void addPoint(Point p) {
        paths.set(0, p);
    }

    public void setOrigin(Point p) {
        paths.set(1, p);
    }
}
