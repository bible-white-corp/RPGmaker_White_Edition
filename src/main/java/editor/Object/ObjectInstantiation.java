package editor.Object;

import editor.Editor;
import editor.Tiles.TilePair;

public class ObjectInstantiation {
    int objIntelIndex;
    String name;
    TilePair position;

    ObjectInstantiation(int index, String objName, TilePair position){
        this.objIntelIndex = index;
        this.name = objName;
        this.position = position;
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

    public TilePair getPosition() {
        return position;
    }

    //#TODO @Clara ici que tu peux bosser
}
