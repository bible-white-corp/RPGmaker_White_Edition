package editor.Tools.Brushes;

import editor.Editor;
import editor.Maps.Level;
import editor.Tiles.TilePair;
import editor.Tools.Brush;
import editor.Tools.Selection;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SelectBrush extends Brush {

    @Override
    public void clicked(Level level, Selection selection, int x_pixel, int y_pixel) {

        List<TilePair> list = new ArrayList<>();

        list.add(level.getTilePixel(first.x, first.y));

        Editor.getSelection().setSelectionOnWorld(list, new Dimension(1,1), new Point(first.x, first.y));
    }

    @Override
    public void Dragged(Level level, Selection selection, int x_pixel, int y_pixel) {

        Point current = new Point(x_pixel - x_pixel % level.getTileWidth(),
                y_pixel - y_pixel % level.getTileHeight());

        if (first == null)
            first = new Point(x_pixel, y_pixel);

        if (last == null || Math.abs(last.x - current.x) >= level.getTileWidth() ||
                Math.abs(last.y - current.y) >= level.getTileHeight())
        {
            last = current;
            dragged(level, selection, x_pixel, y_pixel);
        }
    }

    @Override
    public void dragged(Level level, Selection selection, int x_pixel, int y_pixel) {

        List<TilePair> list = new ArrayList<>();

        int x1 = Math.min(first.x, last.x);
        int x2 = Math.max(first.x, last.x);
        int y1 = Math.min(first.y, last.y);
        int y2 = Math.max(first.y, last.y);

        for (int y = y1; y <= y2; y += level.getTileHeight())
            for (int x = x1; x <= x2; x += level.getTileWidth())
                list.add(level.getTilePixel(x,y));

        Editor.getSelection().setSelectionOnWorld(list,
                new Dimension((x2-x1) / level.getTileWidth() + 1, (y2-y1) / level.getTileHeight() + 1), new Point(x1,y1));
    }
}
