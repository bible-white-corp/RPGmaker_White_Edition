package editor.Tools.Brushes;

import editor.Maps.Level;
import editor.Tools.Selection;

import java.awt.*;

public class LineBrush extends Pencil {

    public void Dragged(Level level, Selection selection, int x_pixel, int y_pixel) {

        Point current = new Point(x_pixel - x_pixel % level.getTileWidth(),
                y_pixel - y_pixel % level.getTileHeight());

        if (first == null)
            first = new Point(x_pixel, y_pixel);

        int delta_x = Math.abs(first.x - current.x);
        int delta_y = Math.abs(first.y - current.y);

        if (last == null || (delta_y == 0 &&
                Math.abs(last.x - current.x) >= level.getTileWidth() * selection.getDimension().width) ||
                ( delta_x == 0 &&
                Math.abs(last.y - current.y) >= level.getTileHeight() * selection.getDimension().height))
        {
            dragged(level, selection, x_pixel, y_pixel);
            last = current;
        }
    }
}
