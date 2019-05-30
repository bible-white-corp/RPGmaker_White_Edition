package editor.Tools.Brushes;

import editor.Editor;
import editor.Maps.Level;
import editor.Object.ObjectInstantiation;
import editor.Tools.Brush;
import editor.Tools.Selection;

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

        //FixMe Tu as les coordonnes et le le layer c'est Editor.

        Editor.getLayer_index();

        Editor.setBrush(old_brush);
    }

    @Override
    public void dragged(Level level, Selection selection, int x_pixel, int y_pixel) {
        clicked(level, selection, x_pixel, y_pixel);
    }
}
