package editor.Tiles;

import editor.Editor;

public class TilePair {
    public int tileSetIndex;
    public int tileIndex;

    public TilePair(Tile tile) {
        tileSetIndex = tile.parent_index;
        tileIndex = tile.getIndex();
    }

    public Tile getTile(){
        return getTileSet().get(tileIndex);
    }

    public TileSet getTileSet(){
        return Editor.world.tileSetList.get(tileSetIndex);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof TilePair && ((TilePair) obj).tileIndex == tileIndex &&
                ((TilePair) obj).tileSetIndex == tileSetIndex;
    }
}
