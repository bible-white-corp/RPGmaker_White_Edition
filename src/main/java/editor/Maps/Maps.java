package editor.Maps;

import editor.Tiles.Tile;

import java.awt.*;
import java.util.Vector;

public class Maps {

    private Vector<Tile> map;

    private int width, height;
    private int tileHeight, tileWidth;

    public Maps(int height, int width, int tileHeight, int tileWidth) {

        this.width = width;
        this.height = height;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;

        int nb_tiles = width * height;

        map = new Vector<>(nb_tiles);

        for (int i = 0; i < nb_tiles; ++i)
            map.add(null);
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

    public Tile getTile(int x, int y){

        return map.get(getIndex(x, y));
    }

    public void setTile(Tile t, int x, int y){

        map.set(getIndex(x, y), t);
    }

    public Tile getTilePixel(int x_pixel, int y_pixel){

        return map.get(getIndexPixel(x_pixel, y_pixel));
    }

    public void setTilePixel(Tile t, int x_pixel, int y_pixel){

        map.set(getIndex(x_pixel, y_pixel), t);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
