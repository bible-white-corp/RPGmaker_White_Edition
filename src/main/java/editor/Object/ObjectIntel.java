package editor.Object;

import editor.Tiles.Tile;

import java.util.List;
import java.util.TreeMap;
import java.util.Vector;

public class ObjectIntel {
    private List<Integer> animations;

    public ObjectIntel() {
        animations = new Vector<>();
    }

    public void addAnimation(int index){

        animations.add(index);
    }
}
