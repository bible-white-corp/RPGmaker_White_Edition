package editor.Maps;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import editor.Editor;
import editor.Tiles.Tile;
import editor.Tiles.TilePair;
import editor.Tiles.TileSet;
import editor.Tools.Selection;

import javax.imageio.ImageIO;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.util.Vector;

public class Level {

    private List<Layer> layers;

    private int width, height;
    private int tileHeight, tileWidth;

    private String name;

    private transient EventListenerList listeners;

    public Level(int height, int width, int tileHeight, int tileWidth, String name) {

        listeners = new EventListenerList();
        layers = new ArrayList<>();

        this.width = width;
        this.height = height;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.name = name;

        int nb_tiles = width * height;

        for (int i = 0; i < 10; ++i)
            layers.add(new Layer(nb_tiles));
    }

    public String getName() {
        return name;
    }

    private int getIndex(int x, int y) {

        if (x < 0 || y < 0 || x >= width || y >= height)
            throw new IndexOutOfBoundsException();

        int index = y * width + x;

        if (index < 0 || index >= width * height)
            throw new IndexOutOfBoundsException();

        return index;
    }

    private int getIndexPixel(int x_pixel, int y_pixel)
    {
        return getIndex(x_pixel / tileWidth, y_pixel / tileHeight);
    }

    private TilePair getFromIndex(int index, int layer)
    {
        return layers.get(layer).getFromIndex(index);
    }

    private void setFromIndex(TilePair t, int index) {

        if (t == null)
            layers.get(Editor.getLayer_index()).setFromIndex(null, index);
        else
            layers.get(Editor.world.getTileFromPair(t).getLayer()).setFromIndex(t, index);

        for (MapsListener listener : listeners.getListeners(MapsListener.class))
            listener.mapsChangee();
    }

    public TilePair getTile(int x, int y){

        return getFromIndex(getIndex(x, y), Editor.getLayer_index());
    }

    public TilePair getTile(int x, int y, int layer){

        return getFromIndex(getIndex(x, y), layer);
    }

    public void setTile(TilePair t, int x, int y){

        setFromIndex(t, getIndex(x, y));
    }

    public TilePair getTilePixel(int x_pixel, int y_pixel){

        return getFromIndex(getIndexPixel(x_pixel, y_pixel), Editor.getLayer_index());
    }

    public void setTilePixel(TilePair t, int x_pixel, int y_pixel){

        setFromIndex(t, getIndexPixel(x_pixel, y_pixel));
    }

    public void addSelection(Selection selection, int x_pixel, int y_pixel) {

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

    public void initializeListener()
    {
        listeners = new EventListenerList();
    }

    public interface MapsListener extends EventListener {

        void mapsChangee();
    }

    @Override
    public String toString() {
        return this.name;
    }
}
