package editor;

import editor.Forms.EditFrame;
import editor.Maps.World;
import editor.Tools.Selection;

public class Editor {

    private static Selection selection = new Selection();

    public static Selection getSelection() {
        return selection;
    }

    public static void main(String[] args) {
        World w = new World();
        w.addMap(100,100,32,32, "MyMap");
        w.createTileSet("src/main/resources/images/tileset_rpg.png", 32, 32);
        EditFrame frame = new EditFrame(w);
        frame.setVisible(true);
    }
}
