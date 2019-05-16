package editor;

import editor.Maps.Maps;
import editor.Tiles.Tile;
import editor.Tiles.TilePair;
import editor.Tiles.TileSet;
import editor.forms.EditFrame;
import editor.forms.InspectorFrame;

public class Editor {

    private static Maps current_map = null;
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

    public static void setCurrent_map(Maps current_map) {
        Editor.current_map = current_map;
    }

    public static Maps getCurrent_map() {
        return current_map;
    }

    public static void main(String[] args) {

        EditFrame frame = new EditFrame();
        frame.setVisible(true);
    }
}
