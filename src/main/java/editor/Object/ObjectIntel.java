package editor.Object;

import editor.Tiles.Tile;

import java.util.List;
import java.util.TreeMap;
import java.util.Vector;

public class ObjectIntel {
    private List<Integer> animations;///index of object's animation in objects list

    public ObjectIntel() {
        animations = new Vector<>();
    }

    public void addAnimation(int index){

        animations.add(index);
    }
    public void removeAnimation(int index){
        if (index < 0 || index >= animations.size())
            throw new IndexOutOfBoundsException();
        animations.remove(index);
    }
}
