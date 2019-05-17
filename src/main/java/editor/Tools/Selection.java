package editor.Tools;

import editor.Tiles.Tile;

import javax.swing.event.EventListenerList;
import java.util.EventListener;

public class Selection{

    private Tile tile = null;

    private final EventListenerList listeners = new EventListenerList();

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {

        this.tile = tile;

        for (SelectionListener listener : listeners.getListeners(SelectionListener.class))
            listener.selectionChange();
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
