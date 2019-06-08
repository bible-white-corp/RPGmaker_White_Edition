package editor.Tools;

import editor.Editor;
import editor.Tiles.TilePair;

import javax.swing.event.EventListenerList;
import java.awt.*;
import java.util.EventListener;
import java.util.List;

public class Selection{

    private List<TilePair> tiles = null;
    private Dimension dimension;

    private boolean isOnWorld = false;
    private Point origin = null;

    private final EventListenerList listeners = new EventListenerList();

    public List<TilePair> getTiles() {
        return tiles;
    }

    public void setSelection(List<TilePair> tiles, Dimension dimension) {

        this.tiles = tiles;
        this.dimension = dimension;
        this.isOnWorld = false;

        for (SelectionListener listener : listeners.getListeners(SelectionListener.class))
            listener.selectionChange();

        Editor.mainFrame.repaint();
    }

    public void setSelectionOnWorld(List<TilePair> tiles, Dimension dimension, Point origin) {

        this.tiles = tiles;
        this.dimension = dimension;
        this.isOnWorld = true;
        this.origin = origin;

        for (SelectionListener listener : listeners.getListeners(SelectionListener.class))
            listener.selectionChange();

        Editor.mainFrame.repaint();
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void addSelectionListener(SelectionListener listener)
    {
        listeners.add(SelectionListener.class, listener);
    }

    public void removeSelectionListener(SelectionListener listener)
    {
        listeners.remove(SelectionListener.class, listener);
    }

    public interface SelectionListener extends EventListener {

        void selectionChange();
    }

    public boolean isOnWorld() {
        return isOnWorld;
    }

    public Point getOrigin() {
        return origin;
    }
}
