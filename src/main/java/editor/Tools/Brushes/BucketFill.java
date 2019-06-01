package editor.Tools.Brushes;

import editor.Editor;
import editor.Maps.Level;
import editor.Tiles.Tile;
import editor.Tiles.TilePair;
import editor.Tools.Brush;
import editor.Tools.Selection;
import editor.Tools.Undo.UndoContainer;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class BucketFill extends Brush {

    private TilePair background;

    @Override
    public void clicked(Level level, Selection selection, int x_pixel, int y_pixel) {

        Tile t = Editor.world.getTileFromPair(selection.getTiles().get(0));

        background = level.getTilePixel(x_pixel, y_pixel, t.getLayer());

        if (background != null && background.equals(selection.getTiles().get(0)))
            return;

        Set<Point> points = new HashSet<>();

        points.add(new Point(x_pixel, y_pixel));

        boolean hasBeenEdited = false;

        while(points.size() > 0)
        {
            Point cur = points.iterator().next();
            points.remove(cur);

            try {

                TilePair tile = level.getTilePixel(cur.x, cur.y, t.getLayer());

                if ((tile == null && background == null) || tile.equals(background)) {
                    level.setTilePixel(selection.getTiles().get(0), cur.x, cur.y);

                    points.add(new Point(cur.x + level.getTileWidth(), cur.y));
                    points.add(new Point(cur.x - level.getTileWidth(), cur.y));
                    points.add(new Point(cur.x, cur.y + level.getTileHeight()));
                    points.add(new Point(cur.x, cur.y - level.getTileHeight()));
                }
            }
            catch (Exception e)
            {

            }
        }
    }

    @Override
    public void dragged(Level level, Selection selection, int x_pixel, int y_pixel) {

    }
}
