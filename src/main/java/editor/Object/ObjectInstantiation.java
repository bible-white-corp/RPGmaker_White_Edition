package editor.Object;

import editor.Editor;
import editor.ProjectTree.objType;
import editor.Tiles.TilePair;

import java.awt.*;

public abstract class ObjectInstantiation {
    protected int objIntelIndex;
    protected int levelIndex = Editor.mainFrame.getLevelIndex();
    protected String name;
    protected int layer;
    protected Point position;

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


    public void finishInstantiation(int layer, Point position){
        this.layer = layer;
        this.position = position;
        Editor.world.objTree.addObj(this, Editor.world.worldObjects.getInWorldObj().size(),
                objType.getEquivalent(this));
        Editor.world.worldObjects.addObjInstance(this);
    }
}