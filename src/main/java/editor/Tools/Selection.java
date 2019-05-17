package editor.Tools;

import editor.Tiles.Tile;

import javax.swing.event.EventListenerList;
import java.awt.*;
import java.util.EventListener;
import java.util.List;

public class Selection{

    private List<Tile> tiles = null;
    private Dimension dimension;

    private final EventListenerList listeners = new EventListenerList();

    public List<Tile> getTiles() {
        return tiles;
    }

    public void setSelection(List<Tile> tiles, Dimension dimension) {

        this.tiles = tiles;
        this.dimension = dimension;

        for (SelectionListener listener : listeners.getListeners(SelectionListener.class))
            listener.selectionChange();
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
}
