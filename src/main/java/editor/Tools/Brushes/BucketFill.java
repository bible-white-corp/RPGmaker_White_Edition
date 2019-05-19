package editor.Tools.Brushes;

import editor.Maps.Level;
import editor.Tiles.Tile;
import editor.Tools.Brush;
import editor.Tools.Selection;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class BucketFill extends Brush {

    private Tile background;

    @Override
    public void clicked(Level level, Selection selection, int x_pixel, int y_pixel) {

        background = level.getTilePixel(x_pixel, y_pixel);

        Set<Point> points = new HashSet<>();

        points.add(new Point(x_pixel, y_pixel));

        while(points.size() > 0)
        {
            Point cur = points.iterator().next();
            points.remove(cur);

            try {

                if (level.getTilePixel(cur.x, cur.y) == background) {
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
