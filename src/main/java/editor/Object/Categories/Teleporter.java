package editor.Object.Categories;

import editor.Editor;
import editor.Maps.Level;
import editor.Object.ObjectInstantiation;

public class Teleporter extends ObjectInstantiation {
    int sibling_index;
    int sibling_map_index;

    public Teleporter(int index, String objName){
        this.objIntelIndex = index;
        this.name = objName;
    }

    public void setSibling(int sibling_index, int sibling_map_index) {
        this.sibling_index = sibling_index;
        this.sibling_map_index = sibling_map_index;
    }

    public Level getSiblingLevel(){
        return Editor.world.levelList.get(sibling_map_index);
    }

    public Teleporter getSibling(){
        return (Teleporter) Editor.world.worldObjects.getInWorldObj().get(sibling_index);
    }
}
