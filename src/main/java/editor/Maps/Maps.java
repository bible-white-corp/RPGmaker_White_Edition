package editor.Maps;

import editor.Tiles.Tile;

import java.awt.*;
import java.util.Vector;

public class Maps {
    private Vector<Tile> map;

    private int width, height;
    private int tileHeight, tileWidth;
    private int nb_tiles;

    public Maps(int height, int width, int tileHeight, int tileWidth) {
        this.width = width;
        this.height = height;
        this.nb_tiles = height * width;
        map = new Vector<>(nb_tiles + 1);
    }

    public Tile get(int x, int y){
        if (x < 0 || y < 0 || x >= width || y >= height)
            throw new IndexOutOfBoundsException();
        int index = (y / tileHeight) * width + x / tileWidth;
        return map.get(index);
    }

    public void addTile(int x, int y, Tile t){
        if (x < 0 || y < 0 || x >= width || y >= height)
            throw new IndexOutOfBoundsException();
        int index = (y / tileHeight) * width + x / tileWidth;
        map.set(index, t);
    }
}
