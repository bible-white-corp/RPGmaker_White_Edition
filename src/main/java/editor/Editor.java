package editor;

import editor.Tiles.Tile;
import editor.Tiles.TilePair;
import editor.Tiles.TileSet;
import editor.forms.EditFrame;
import editor.forms.InspectorFrame;

public class Editor {

    private static TilePair selected_tile = null;
    private static InspectorFrame inspector;

    public static void setInspector(InspectorFrame inspector)
    {
        Editor.inspector = inspector;
    }

    public static void setSelectedTile(Tile tile, TileSet set)
    {
        selected_tile =  new TilePair(tile, set);
        inspector.refresh();
    }

    public static TilePair getSelected_tile() {
        return selected_tile;
    }

    public static void main(String[] args) {

        EditFrame frame = new EditFrame();
        frame.setVisible(true);
    }
}
