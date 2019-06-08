package editor.Tools.Brushes;

import editor.Editor;
import editor.Maps.Level;
import editor.Object.ObjectInstantiation;
import editor.Tools.Brush;
import editor.Tools.Selection;

import javax.swing.*;
import java.awt.*;

public class PathEdit extends Brush {

    private Brush old_brush;
    private ObjectInstantiation instance;

    public PathEdit(Brush old_brush, ObjectInstantiation instance)
    {
        this.old_brush = old_brush;
        this.instance = instance;
    }

    @Override
    public void clicked(Level level, Selection selection, int x_pixel, int y_pixel) {

        instance.getPath().addPoint(new Point(x_pixel, y_pixel));
        Editor.setBrush(old_brush);
        JOptionPane.showMessageDialog(Editor.editFrame, "Path successfully set",
                "Movements", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void dragged(Level level, Selection selection, int x_pixel, int y_pixel) {
        clicked(level, selection, x_pixel, y_pixel);
    }
}
