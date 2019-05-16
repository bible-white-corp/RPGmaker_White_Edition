package editor.Maps;

import editor.Tiles.Tile;

import java.awt.*;
import java.util.Vector;

public class Maps {
    private Vector<Tile> map;

    public int width, height;
    private int tileHeight, tileWidth;
    private int nb_tiles;

    public Maps(int height, int width, int tileHeight, int tileWidth) {
        this.width = width;
        this.height = height;
        this.tileHeight = tileHeight;
        this.tileWidth = tileWidth;
        this.nb_tiles = height * width;
        map = new Vector<>(nb_tiles + 1);

        for (int i = 0; i < nb_tiles + 1; ++i)
            map.add(null);
    }

    public Tile get(int x, int y){
        if (x < 0 || y < 0 || x >= width || y >= height)
            throw new IndexOutOfBoundsException();
        int index = y * width + x;

        if (index >= map.size())
            return null;

        return map.get(index);
    }

    public void addTile(int x, int y, Tile t){
        if (x < 0 || y < 0 || x >= width * tileWidth|| y >= height * tileHeight)
            throw new IndexOutOfBoundsException();
        int index = (y / tileHeight) * width + x / tileWidth;
        map.set(index, t);
    }
}
