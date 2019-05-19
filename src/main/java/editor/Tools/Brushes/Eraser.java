package editor.Tools.Brushes;

import editor.Maps.Level;
import editor.Tools.Selection;

public class Eraser extends PaintBrush {

    @Override
    protected void setTile(Level level, Selection selection, int x_pixel, int y_pixel, int m_x, int m_y)
    {
        level.setTilePixel(null, x_pixel, y_pixel);
    }

}
