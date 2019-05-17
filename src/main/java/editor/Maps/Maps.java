package editor.Maps;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import editor.Tiles.Tile;
import editor.Tiles.TileSet;
import editor.Tools.Selection;

import javax.imageio.ImageIO;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.io.*;
import java.util.EventListener;
import java.util.List;
import java.util.Vector;

public class Maps {

    private List<Tile> map;

    private int width, height;
    private int tileHeight, tileWidth;
    private String name;

    private transient final EventListenerList listeners = new EventListenerList();

    public Maps(int height, int width, int tileHeight, int tileWidth, String name) {

        this.width = width;
        this.height = height;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;

        int nb_tiles = width * height;

        map = new Vector<>(nb_tiles);

        for (int i = 0; i < nb_tiles; ++i)
            map.add(null);
    }

    public String getName() {
        return name;
    }

    public List<Tile> getMap() {
        return map;
    }

    private int getIndex(int x, int y) {

        if (x < 0 || y < 0 || x >= width || y >= height)
            throw new IndexOutOfBoundsException();

        int index = y * width + x;

        if (index < 0 || index >= map.size())
            throw new IndexOutOfBoundsException();

        return index;
    }

    private int getIndexPixel(int x_pixel, int y_pixel)
    {
        return getIndex(x_pixel / tileWidth, y_pixel / tileHeight);
    }

    private Tile getFromIndex(int index)
    {
        return map.get(index);
    }

    private void setFromIndex(Tile t, int index) {

        map.set(index, t);

        for (MapsListener listener : listeners.getListeners(MapsListener.class))
            listener.mapsChangee();
    }

    public Tile getTile(int x, int y){

        return getFromIndex(getIndex(x, y));
    }

    public void setTile(Tile t, int x, int y){

        setFromIndex(t, getIndex(x, y));
    }

    public Tile getTilePixel(int x_pixel, int y_pixel){

        return getFromIndex(getIndexPixel(x_pixel, y_pixel));
    }

    public void setTilePixel(Tile t, int x_pixel, int y_pixel){

        setFromIndex(t, getIndexPixel(x_pixel, y_pixel));
    }

    public void addSelection(Selection selection, int x_pixel, int y_pixel)
    {
        for (int i = 0; i < selection.getDimension().width; ++i)
            for (int j = 0; j < selection.getDimension().height; ++j)
                setTilePixel(
                        selection.getTiles().get(i + j * selection.getDimension().width),
                        x_pixel + i * tileWidth, y_pixel + j * tileHeight);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public void addMapsListener(MapsListener listener)
    {
        listeners.add(MapsListener.class, listener);
    }

    public void removeMapsListener(MapsListener listener)
    {
        listeners.remove(MapsListener.class, listener);
    }

    public interface MapsListener extends EventListener {

        void mapsChangee();
    }
}
