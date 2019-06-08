package editor.Tools.Brushes;

import editor.Editor;
import editor.Maps.Level;
import editor.Object.ObjectInstantiation;
import editor.ProjectTree.objType;
import editor.Tools.Brush;
import editor.Tools.Selection;

import javax.swing.*;
import java.awt.*;

public class ObjectMove extends Brush {

    private Brush old_brush;
    private ObjectInstantiation instance;

    public ObjectMove(Brush old_brush, ObjectInstantiation instance)
    {
        this.old_brush = old_brush;
        this.instance = instance;
    }

    @Override
    public void clicked(Level level, Selection selection, int x_pixel, int y_pixel) {
        instance.setPosition(new Point(x_pixel, y_pixel));
        Editor.setBrush(old_brush);
        if (instance.getType() == objType.NPC)
            instance.getPath().setOrigin(new Point(x_pixel, y_pixel));
        Editor.mainFrame.repaint();
        JOptionPane.showMessageDialog(Editor.editFrame, "Object successfully moved",
                "Re-Locate", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void dragged(Level level, Selection selection, int x_pixel, int y_pixel) {
        clicked(level, selection, x_pixel, y_pixel);
    }
}
