package editor.Object;

import editor.Editor;
import editor.Maps.Level;
import editor.ProjectTree.objType;
import java.awt.*;

public class ObjectInstantiation {
    protected int objIntelIndex;
    protected int levelIndex = Editor.mainFrame.getLevelIndex();
    protected String name;
    protected int layer;
    protected Point position;
    int index;
    int sibling_index = -1;
    int sibling_map_index;
    objType type;
    protected boolean randomMove = false;

    public objType getType() {
        return type;
    }

    public ObjectInstantiation(int objIntelIndex, String name, objType type) {
        this.objIntelIndex = objIntelIndex;
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return name;
    }

    public ObjectIntel getIntel(){
        return Editor.world.worldObjects.objs.get(objIntelIndex);
    }

    public String getName() {
        return name;
    }

    public Point getPosition() {
        return position;
    }

    public int getLevelIndex() {
        return levelIndex;
    }

    public void setRandomMove(boolean randomMove) {
        this.randomMove = randomMove;
    }

    public boolean hasRandomMove() {
        return randomMove;
    }

    public int getIndex() {
        return index;
    }

    public void finishInstantiation(int layer, Point position){
        this.layer = layer;
        this.index = Editor.world.worldObjects.getInWorldObj().size();
        this.position = position;
        Editor.world.objTree.addObj(this, Editor.world.worldObjects.getInWorldObj().size(), type);
        Editor.world.worldObjects.addObjInstance(this);
    }

    public void setSibling(int sibling_index, int sibling_map_index) {
        this.sibling_index = sibling_index;
        this.sibling_map_index = sibling_map_index;
    }

    public Level getSiblingLevel(){
        return Editor.world.levelList.get(sibling_map_index);
    }

    public ObjectInstantiation getSibling(){
        if (sibling_index == -1)
            return null;
        return Editor.world.worldObjects.getInWorldObj().get(sibling_index);
    }
}