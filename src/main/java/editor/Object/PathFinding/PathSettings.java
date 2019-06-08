package editor.Object.PathFinding;

import editor.Editor;
import editor.Maps.Level;
import engine.Controllers.PlayerControl;
import engine.Engine;


import java.awt.*;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class PathSettings {
    List<Point> paths;
    transient int curPath = 0;
    transient int curIt = 0;
    MoveType type;
    Random random;
    int levelIndex;
    int parentObj;

    public PathSettings(MoveType type, Point coord, int levelIndex, int objIndex){
        this.type = type;
        paths = new Vector<>();
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

    public int getLength(){
        return paths.size();
    }

    public Point getCur(){
        return paths.get(curPath);
    }

    public Point setNext(){
        if (type == MoveType.simple_trip){
           type = MoveType.idle;
        }
        else if (type == MoveType.two_way){
            if (++curPath != paths.size()){
                return getCur();
            }
            type = MoveType.idle;
        }
        else if (type == MoveType.infinite){
            if (curIt == 1){
                if (--curPath < 0) {
                    curIt = 0;
                    curPath += 2;
                }
                return getCur();
            }
            else {
                if (++curPath == paths.size()){
                    curIt = 1;
                    curPath -= 2;
                }
                return getCur();
            }
        }
        else if (type == MoveType.random){
            Level l = Editor.world.levelList.get(levelIndex);
            Point p;
            int x;
            int y;
            do{
                x = random.nextInt(l.getWidth());
                y = random.nextInt(l.getHeight());
                p = new Point(x * l.getTileWidth() - l.getTileWidth()/2,
                        y * l.getTileHeight() - l.getTileHeight() / 2);
            } while (!PlayerControl.canMove(p, levelIndex, Engine.world.worldObjects.getInWorldObj().get(parentObj)));
            paths.set(0, p);
            return paths.get(0);
        }
        return null;
    }

    public void addPoint(Point p){
        if (paths.size() < 2)
            paths.add(p);
    }
}
