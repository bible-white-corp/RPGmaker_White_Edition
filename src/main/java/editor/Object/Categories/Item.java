package editor.Object.Categories;

import editor.Object.ObjectInstantiation;
import editor.Tiles.TilePair;

public class Item extends ObjectInstantiation {

    Item (int index, String objName, TilePair position){
        this.objIntelIndex = index;
        this.name = objName;
        this.position = position;
    }
}
