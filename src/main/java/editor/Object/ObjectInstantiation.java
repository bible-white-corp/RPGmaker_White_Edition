package editor.Object;

import editor.Editor;
import editor.Tiles.TilePair;

public class ObjectInstantiation {
    ObjectIntel objectIntel;
    String name;
    String dialog;
    TilePair position;

    ObjectInstantiation(ObjectIntel objectIntel, String objName, TilePair position){
        this.objectIntel = objectIntel;
        this.name = objName;
        this.position = position;
        if (!objectIntel.is_static)
            this.dialog = "Hello!";
    }

    @Override
    public String toString() {
        return name;
    }

    public ObjectIntel getObjectIntel() {
        return objectIntel;
    }

    public String getName() {
        return name;
    }

    public String getDialog() {
        return dialog;
    }

    public TilePair getPosition() {
        return position;
    }

    //#TODO @Clara ici que tu peux bosser
}
