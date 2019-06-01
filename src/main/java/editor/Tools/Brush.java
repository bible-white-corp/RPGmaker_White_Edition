package editor.Tools;

import editor.Editor;
import editor.Maps.Level;

import java.awt.*;

public abstract class Brush {

    protected Point first;
    protected Point last;
    private boolean dragin = true;

    public void Clicked(Level level, Selection selection, int x_pixel, int y_pixel) {

        first = new Point(x_pixel - x_pixel % level.getTileWidth(), y_pixel - y_pixel % level.getTileHeight());
        last = first;

        Editor.world.undo.initNewAction(Editor.mainFrame.getLevelIndex(),
                selection.getTiles().get(0).getTile().getLayer());

        clicked(level, selection, x_pixel, y_pixel);
    }

    public void Dragged(Level level, Selection selection, int x_pixel, int y_pixel) {

        Point current = new Point(x_pixel - x_pixel % level.getTileWidth(),
                y_pixel - y_pixel % level.getTileHeight());

        if (first == null)
            first = new Point(x_pixel, y_pixel);

        if (last == null || Math.abs(last.x - current.x) >= level.getTileWidth() * selection.getDimension().width ||
                Math.abs(last.y - current.y) >= level.getTileHeight() * selection.getDimension().height)
        {
            if (dragin){
                dragin = false;
                Editor.world.undo.initNewAction(Editor.mainFrame.getLevelIndex(),
                        selection.getTiles().get(0).getTile().getLayer());
            }
            last = current;
            dragged(level, selection, x_pixel, y_pixel);
        }
    }

    public void Released()
    {
        dragin = true;
        first = null;
        last = null;
    }

    protected abstract void clicked(Level level, Selection selection, int x_pixel, int y_pixel);

    protected abstract void dragged(Level level, Selection selection, int x_pixel, int y_pixel);

}
