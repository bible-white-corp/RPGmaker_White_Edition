package editor.Tiles;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;


public class TileSet {
    private int tile_x_size;
    private int tile_y_size;

    private int width;
    private int height;

    private int nb_tiles;
    private int tiles_per_line;
    private int nb_lines;
    private Vector<Tile> vector;
    private BufferedImage sprites;// Pour info le 0,0 est en haut a gauche dans une buffered image

    private TileSet(int tile_x_size, int tile_y_size) {
        this.tile_x_size = tile_x_size;
        this.tile_y_size = tile_y_size;
    }

    /**
     * This function is used to crate a new set of tiles from path
     * @param path path to the tiles file
     * @param x height of the tiles
     * @param y width pf the tiles
     * @return a custom TileSet
     * @throws IOException id IO error append
     * @throws IllegalArgumentException if path given is wrong
     */
    public static TileSet create(String path, int x, int y)
            throws IOException, IllegalArgumentException {

        File file = new File(path);
        if (!file.canRead()){
            throw new IllegalArgumentException("Corrupted file or unreadable file");
        }

        TileSet ts = new TileSet(x, y);
        ts.sprites = ImageIO.read(file);
        ts.width = ts.sprites.getWidth();
        ts.height = ts.sprites.getHeight();
        ts.tiles_per_line = ts.width / x;
        ts.nb_lines = ts.height / y;
        ts.nb_tiles = ts.nb_lines * ts.tiles_per_line;

        ts.vector = new Vector<>(ts.nb_tiles + 1);
        for (int i = 0; i < ts.nb_tiles; i++) {
            ts.vector.add(new Tile(i));
        }

        return ts;
    }

    /**
     * The purpose of this function is to return the block clicked by the user
     * @param x the width of the selected block
     * @param y the height of the selected block
     * @warning return also when is_tile = false
     * @return the block located at the coordinates
     * @throws IndexOutOfBoundsException if coordinates out_of_range
     */
    public Tile get(int x, int y)
            throws IndexOutOfBoundsException{
        if (x >= width || y >= height)
            throw new IndexOutOfBoundsException();
        int index = (y / tile_y_size) * tiles_per_line + x / tile_x_size;
        return vector.get(index);
    }

    /**
     * the purpose of this function is to be a getter to the private vector<Tile>
     * @warning return also when is_tile = false
     * @param index in the vector
     * @return the indexed tile
     * @throws IndexOutOfBoundsException if index out of range
     */
    public Tile get(int index)
        throws IndexOutOfBoundsException{
        if (index >= nb_tiles)
            throw new IndexOutOfBoundsException();
        return vector.get(index);
    }

    /**
     * This function returns all of the TRUE tiles in the user selected area
     * @param x upper left width
     * @param x_len length of the width selection
     * @param y upper right height
     * @param y_len length of the height selection
     * @return vector of TRUE tiles
     */
    public Vector<Tile> select(int x, int x_len, int y, int y_len){
        Vector<Tile> res = new Vector<>();
        for (; y < height && y < y_len; y += tile_y_size){
            for (; x < width && x < x_len; x += tile_x_size){
                Tile t = get(x, y);
                if (t.is_tile())
                    res.add(t);
            }
        }
        return res;
    }

    public BufferedImage getSprites() {
        return sprites;
    }
}
