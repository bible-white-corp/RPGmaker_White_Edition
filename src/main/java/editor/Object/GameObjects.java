package editor.Object;

import editor.Tiles.Tile;
import editor.Tiles.TilePair;
import editor.Tiles.TileSet;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

/**
 * This class handles world's objects
 */
public class GameObjects {
    Map<String, ObjectIntel> objs;///mapping of objects_animations by their given name
    Map<String, Animation> animations;///mapping of all user's animations

    public GameObjects() {
        objs = new TreeMap<>();
        animations = new TreeMap<>();
    }

    public Map<String, ObjectIntel> getObjs() {
        return objs;
    }

    public Map<String, Animation> getAnimations() {
        return animations;
    }

    public void addObject(String name){

        objs.put(name, new ObjectIntel(name));
    }

    public void addAnimation(String animationName, List<TilePair> sprites){
        animations.put(animationName, new Animation(animationName, sprites));
    }

    /*public void addAnimation(String objName, String animationName){
        objs.get(objName).addAnimation(animations.get(animationName));
    }*/



}
