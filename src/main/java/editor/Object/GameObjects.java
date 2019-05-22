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
    List<ObjectIntel> objs;///mapping of objects_animations by their given name
    List<Animation> animations;///mapping of all user's animations
    List<ObjectInstantiation> instantiations;//list of all instantiated objects on the map

    public GameObjects() {
        objs = new Vector<>();
        animations = new Vector<>();
        instantiations = new Vector<>();
    }

    public List<ObjectIntel> getObjs() {
        return objs;
    }

    public List<Animation> getAnimations() {
        return animations;
    }

    public void addObject(String name){

        objs.add(new ObjectIntel(name));
    }

    public void addAnimation(String animationName, List<TilePair> sprites){
        animations.add(new Animation(animationName, sprites));
    }

    public void add_obj(TilePair position, int index, String instanceName){
        instantiations.add(new ObjectInstantiation(index, instanceName, position));
    }

    /*public void addAnimation(String objName, String animationName){
        objs.get(objName).addAnimation(animations.get(animationName));
    }*/



}
