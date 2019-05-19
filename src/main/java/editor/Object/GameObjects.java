package editor.Object;

import editor.Tiles.Tile;
import editor.Tiles.TileSet;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

public class GameObjects {
    Map<String, ObjectIntel> objs;
    Map<String, Animation> animations;

    public GameObjects() {
        objs = new TreeMap<>();
        animations = new TreeMap<>();
    }

    public void addObject(String name){

        objs.put(name, new ObjectIntel());
    }

    public void addAnimation(String animationName, List<Tile> sprites){
        animations.put(animationName, new Animation(animationName, sprites));
    }

    /*public void addAnimation(String objName, String animationName){
        objs.get(objName).addAnimation(animations.get(animationName));
    }*/



}
