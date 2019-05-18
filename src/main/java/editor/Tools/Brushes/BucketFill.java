package editor.Tools.Brushes;

import editor.Maps.Level;
import editor.Tiles.Tile;
import editor.Tools.Brush;
import editor.Tools.Selection;

public class BucketFill extends Brush {

    private Tile background;

    @Override
    public void clicked(Level level, Selection selection, int x_pixel, int y_pixel) {

        background = level.getTilePixel(x_pixel, y_pixel);

        fill(level, selection, x_pixel, y_pixel);
    }

    private void fill(Level level, Selection selection, int x_pixel, int y_pixel)
    {
        try {

            if (level.getTilePixel(x_pixel, y_pixel) == background) {
                level.setTilePixel(selection.getTiles().get(0), x_pixel, y_pixel);

                fill(level, selection, x_pixel + level.getTileWidth(), y_pixel);
                fill(level, selection, x_pixel - level.getTileWidth(), y_pixel);
                fill(level, selection, x_pixel, y_pixel + level.getTileHeight());
                fill(level, selection, x_pixel, y_pixel - level.getTileHeight());
            }
        }
        catch (Exception e)
        {

        }
    }

    @Override
    public void dragged(Level level, Selection selection, int x_pixel, int y_pixel) {

    }
}
