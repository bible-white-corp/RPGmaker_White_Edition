package editor;

import editor.Forms.EditFrame;
import editor.Forms.GameFrame;
import editor.Maps.World;
import editor.Tools.Brush;
import editor.Tools.Brushes.BucketFill;
import editor.Tools.Brushes.Pencil;
import editor.Tools.Selection;

public class Editor {

    private static Selection selection = new Selection();
    private static Brush brush = new BucketFill();

    public static World world;
    public static GameFrame mainFrame;

    public static Brush getBrush() {
        return brush;
    }

    public static Selection getSelection() {
        return selection;
    }

    public static void main(String[] args) {
        world = new World("Hello");

        world.addMap(100,100,32,32, "MyMap");
        world.createTileSet("src/main/resources/images/tileset_rpg.png", 32, 32);

        EditFrame frame = new EditFrame();
        frame.setVisible(true);
    }
}
