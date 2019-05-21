package editor.Object;

import editor.Editor;
import editor.Tiles.Tile;

import java.util.List;
import java.util.TreeMap;
import java.util.Vector;

public class ObjectIntel {
    private List<Integer> animations;///index of object's animation in objects list

    public ObjectIntel(String objName) {
        name = objName;
        animations = new Vector<>();
        Editor.world.projectTree.addNewObject(this);
    }

    public void addAnimation(int index){

        animations.add(index);
    }
    public void removeAnimation(int index){
        if (index < 0 || index >= animations.size())
            throw new IndexOutOfBoundsException();
        animations.remove(index);
    }

    @Override
    public String toString() {
        return name;
    }

    public String name;
}
