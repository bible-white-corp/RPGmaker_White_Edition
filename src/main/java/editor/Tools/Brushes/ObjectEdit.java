package editor.Tools.Brushes;

import editor.Editor;
import editor.Maps.Level;
import editor.Object.ObjectInstantiation;
import editor.Tiles.TilePair;
import editor.Tools.Brush;
import editor.Tools.Selection;

import java.awt.*;

public class ObjectEdit extends Brush {

    private Brush old_brush;
    private ObjectInstantiation instance;

    public ObjectEdit(Brush old_brush, ObjectInstantiation instance)
    {
        this.old_brush = old_brush;
        this.instance = instance;
    }

    @Override
    public void clicked(Level level, Selection selection, int x_pixel, int y_pixel) {

        instance.finishInstantiation(Editor.getLayer_index(), new Point(x_pixel, y_pixel));

        Editor.setBrush(old_brush);
    }

    @Override
    public void dragged(Level level, Selection selection, int x_pixel, int y_pixel) {
        clicked(level, selection, x_pixel, y_pixel);
    }
}
