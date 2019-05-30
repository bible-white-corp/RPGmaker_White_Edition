package editor.Object.Categories;

import editor.Object.ObjectInstantiation;
import editor.Tiles.TilePair;

public class Player extends ObjectInstantiation {
    public Player(int index, String objName){
        this.objIntelIndex = index;
        this.name = objName;
    }
}
