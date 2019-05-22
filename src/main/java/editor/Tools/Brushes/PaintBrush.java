package editor.Tools.Brushes;

import editor.Editor;
import editor.Maps.Level;
import editor.Tools.Brush;
import editor.Tools.Selection;

public class PaintBrush extends Brush {

    @Override
    public void clicked(Level level, Selection selection, int x_pixel, int y_pixel) {

        int brush_size = Editor.getBrush_size();

        float r2 = brush_size * brush_size;

        for (int x = -(brush_size - 1); x < brush_size; ++x) for (int y = -(brush_size - 1); y < brush_size; ++y)
        {
            if ( (float)(x*x + y*y) / r2 > 0.45)
                continue;

            int m_x = x % selection.getDimension().width;
            int m_y = y % selection.getDimension().height;

            while (m_x < 0)
                m_x += selection.getDimension().width;

            while (m_y < 0)
                m_y += selection.getDimension().height;

            setTile(level, selection, x_pixel + x * level.getTileWidth(), y_pixel + y * level.getTileHeight(), m_x, m_y);
        }
    }

    protected void setTile(Level level, Selection selection, int x_pixel, int y_pixel, int m_x, int m_y)
    {
        level.setTilePixel(selection.getTiles().get(m_x + m_y * selection.getDimension().width), x_pixel, y_pixel);
    }

    @Override
    public void dragged(Level level, Selection selection, int x_pixel, int y_pixel) {

        clicked(level, selection, x_pixel, y_pixel);
    }
}
