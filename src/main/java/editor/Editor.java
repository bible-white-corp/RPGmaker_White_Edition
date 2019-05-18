package editor;

import editor.Forms.EditFrame;
import editor.Maps.World;
import editor.Tools.Brush;
import editor.Tools.Brushes.Pencil;
import editor.Tools.Selection;

public class Editor {

    private static Selection selection = new Selection();
    private static Brush brush = new Pencil();

    public static Brush getBrush() {
        return brush;
    }

    public static Selection getSelection() {
        return selection;
    }

    public static void main(String[] args) {
        World w = new World("Hello");

        w.addMap(100,100,32,32, "MyMap");
        w.createTileSet("src/main/resources/images/tileset_rpg.png", 32, 32);

        EditFrame frame = new EditFrame(w);
        frame.setVisible(true);
    }
}
