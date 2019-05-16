package editor.Object;

import editor.Tiles.Tile;
import editor.Tiles.TileSet;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

public class Objects {
    TileSet ts;
    Map<String, ObjectIntel> objs;
    Map<String, Animation> animations;

    public Objects(String path, int x, int y) throws IOException {
        ts = TileSet.create(path, x, y);
        objs = new TreeMap<>();
    }

    public void addObject(String name){

        objs.put(name, new ObjectIntel());
    }

    /*public void addAnimation(String objName, String animationName){
        objs.get(objName).addAnimation(animations.get(animationName));
    }*/



}
