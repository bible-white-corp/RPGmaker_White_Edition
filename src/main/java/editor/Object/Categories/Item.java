package editor.Object.Categories;

import editor.Object.ObjectInstantiation;
import editor.Tiles.TilePair;

public class Item extends ObjectInstantiation {

    public Item(int index, String objName){
        this.objIntelIndex = index;
        this.name = objName;
    }
}