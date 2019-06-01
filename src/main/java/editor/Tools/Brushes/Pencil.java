package editor.Tools.Brushes;

import editor.Editor;
import editor.Maps.Level;
import editor.Tiles.Tile;
import editor.Tiles.TilePair;
import editor.Tools.Brush;
import editor.Tools.Selection;

public class Pencil extends Brush {

    @Override
    public void clicked(Level level, Selection selection, int x_pixel, int y_pixel) {

        boolean hasBeenEdit = false;

        for (int i = 0; i < selection.getDimension().width; ++i)
            for (int j = 0; j < selection.getDimension().height; ++j)
            {
                TilePair t = selection.getTiles().get(i + j * selection.getDimension().width);

                if (t != null) {
                    if (!hasBeenEdit){
                        hasBeenEdit = true;
                        Editor.world.undo.initNewAction(Editor.mainFrame.getLevelIndex(), t.getTile().getLayer());
                    }
                    level.setTilePixel(t, x_pixel + i * level.getTileWidth(),
                            y_pixel + j * level.getTileHeight());
                }
            }
    }

    @Override
    public void dragged(Level level, Selection selection, int x_pixel, int y_pixel) {

        clicked(level,selection,x_pixel,y_pixel);
    }
}
