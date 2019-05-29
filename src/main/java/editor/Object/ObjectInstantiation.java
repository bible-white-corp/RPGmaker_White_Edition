package editor.Object;

import editor.Editor;
import editor.Forms.Path;
import editor.Tiles.TilePair;

public class ObjectInstantiation {
    private ObjectIntel objectIntel;
    private String name;
    private String dialog;
    private TilePair position;
    private Path path;

    ObjectInstantiation(ObjectIntel objectIntel, String objName, TilePair position){
        this.objectIntel = objectIntel;
        this.name = objName;
        this.position = position;

        //by default, to be changed later in storyFrame
        if (!objectIntel.is_static)
            this.dialog = "Hello!";
        this.path = Path.StandStill;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setDialog(String dialog) {
        this.dialog = dialog;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    //#TODO @Clara ici que tu peux bosser
}
