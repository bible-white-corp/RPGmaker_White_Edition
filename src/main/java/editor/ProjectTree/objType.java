package editor.ProjectTree;

import editor.Object.Categories.Item;
import editor.Object.Categories.NPC;
import editor.Object.Categories.Teleporter;
import editor.Object.ObjectInstantiation;

public enum objType{
    ITEM(0),
    NPC(1),
    TELEPORTER(2),
    PLAYER(3);

    private final int value;

    objType(final int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    public static objType getEquivalent(ObjectInstantiation instantiation){
        if (instantiation instanceof Item)
            return ITEM;
        else if (instantiation instanceof NPC)
            return NPC;
        else if (instantiation instanceof Teleporter)
            return TELEPORTER;
        else
            return PLAYER;
    }
}
