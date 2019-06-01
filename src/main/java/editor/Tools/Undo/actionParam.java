package editor.Tools.Undo;

import editor.Tiles.TilePair;

public class actionParam {
    public TilePair tilePair;
    public int index;

    public actionParam(TilePair tilePair, int index) {
        this.tilePair = tilePair;
        this.index = index;
    }
}
