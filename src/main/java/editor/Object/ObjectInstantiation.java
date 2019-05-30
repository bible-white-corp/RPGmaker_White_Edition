package editor.Object;

import editor.Editor;
import editor.Tiles.TilePair;

public abstract class ObjectInstantiation {
    protected int objIntelIndex;
    protected String name;
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

    //#TODO @Clara ici que tu peux bosser
}
