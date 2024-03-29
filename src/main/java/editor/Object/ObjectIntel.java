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

    public boolean is_anim(int animNb) {
        for (int i = 0; i < animations.size(); i++) {
            if (animations.get(i) == animNb)
                return true;
        }
        return false;
    }

    public List<Integer> getAnimations() {
        return animations;
    }

    public Animation getAnimation(int index)
    {
        return Editor.world.worldObjects.animations.get(animations.get(index));
    }

    public Animation getIdle(){
        return getAnimation(0);
    }

    public String getName() {
        return name;
    }

    public List<String> getNames(){
        List<String> res = new Vector<>();
        List<Animation> list = Editor.world.worldObjects.getAnimations();
        for (int i : animations){
            res.add(list.get(i).toString());
        }
        return res;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
