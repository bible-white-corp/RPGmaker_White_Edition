package editor.Object;

import editor.Editor;

import java.util.List;
import java.util.Vector;

public class ObjectIntel {
    private List<Integer> animations;///index of object's animation in objects list
    private String name;
    boolean is_static;

    public ObjectIntel(String objName, boolean is_static, List<Integer> animations) {
        this.is_static = is_static;
        name = objName;
        this.animations = animations;
    }

    public void setAnimation(int index, int animation){

        animations.add(index);
    }
    public void removeAnimation(int index){
        if (index < 0 || index >= animations.size())
            throw new IndexOutOfBoundsException();
        animations.remove(index);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
