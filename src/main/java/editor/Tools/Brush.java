package editor.Tools;

import editor.Maps.Level;

public abstract class Brush {

    public abstract void paintSelection(Level level, Selection selection, int x_pixel, int y_pixel);
}
