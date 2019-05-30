package editor.Object;

import editor.Editor;
import editor.Tiles.TilePair;

public abstract class ObjectInstantiation {
    protected int objIntelIndex;
    protected int levelIndex = Editor.mainFrame.getLevelIndex();
    protected String name;
    protected int layer;
    protected TilePair position;

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

    public TilePair getPosition() {
        return position;
    }

    public int getLevelIndex() {
        return levelIndex;
    }

    public void setPosition(TilePair position) {
        this.position = position;
    }

    public void finishInstantiation(int layer, TilePair position){
        this.layer = layer;
        this.position = position;
        Editor.world.worldObjects.addObjInstance(this);
    }
}
