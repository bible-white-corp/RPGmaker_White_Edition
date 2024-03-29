package editor.Tiles;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import editor.Tools.Selection;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.InvalidPathException;
import java.util.Vector;


public class TileSet {
    private int tile_x_size;
    private int tile_y_size;

    private int width;
    private int height;

    private String name;
    int number;//index of the TileSet in the TileSet[] of the world
    private int nb_tiles;
    private int tiles_per_line;
    private int nb_lines;
    private Vector<Tile> vector;
    private transient BufferedImage sprites;// Pour info le 0,0 est en haut a gauche dans une buffered image

    private TileSet(int tile_x_size, int tile_y_size) {
        this.tile_x_size = tile_x_size;
        this.tile_y_size = tile_y_size;
    }

    public int getWidth() {
        return width;
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    /**
     * Load TileSet from path
     * @param path the path given by the user to found the save
     * @return the built TileSet
     * @throws FileNotFoundException if path leads to nothing
     */
    public static TileSet importSet(String path)
            throws IOException {
        FileReader f = new FileReader(path + "/config.json");
        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(f);
        TileSet res = gson.fromJson(jsonReader, TileSet.class);

        res.sprites = ImageIO.read(new File(path + "/sprites.png"));
        return res;
    }

    public int getNb_tiles() {
        return nb_tiles;
    }

    /**
     * Save the TileSet to a json file named with path
     * @param path the path given by the user to the save, must be DIR
     */
    public boolean exportSet(String path) throws IOException {
        String location = path + "/" + name + "_Save";
        File f = new File(location);
        f.mkdirs();

        Gson gson = new Gson();
        FileWriter fileWriter;
        fileWriter = new FileWriter(location + "/config.json");

        JsonWriter jsonWriter = new JsonWriter(fileWriter);
        gson.toJson(this, fileWriter);

        File outImage = new File(location + "/sprites.png");

        ImageIO.write(sprites, "png", outImage);

        try {
            jsonWriter.close();
        } catch (java.io.IOException ioException) { }
        return true;
    }

    /**
     * This function is used to crate a new set of tiles from path
     * @param path path to the tiles file
     * @param x height of the tiles
     * @param y width pf the tiles
     * @return a custom TileSet
     * @throws IOException id IO error append
     * @throws InvalidPathException if path given is wrong
     */
    public static TileSet create(String path, int x, int y, int count)
            throws IOException, InvalidPathException {

        File file = new File(path);
        if (!file.canRead()){
            throw new InvalidPathException(path, "cannot open and read at this path");
        }

        TileSet ts = new TileSet(x, y);
        ts.name = file.getName();
        ts.sprites = ImageIO.read(file);
        ts.width = ts.sprites.getWidth();
        ts.height = ts.sprites.getHeight();
        ts.tiles_per_line = ts.width / x;
        ts.number = count;
        ts.nb_lines = ts.height / y;
        ts.nb_tiles = ts.nb_lines * ts.tiles_per_line;

        ts.vector = new Vector<>(ts.nb_tiles + 1);
        for (int i = 0; i < ts.nb_tiles; i++) {
            ts.vector.add(new Tile(i, ts));
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
        if (x >= width || y >= height || x < 0 || y < 0)
            throw new IndexOutOfBoundsException();
        int index = (y / tile_y_size) * tiles_per_line + x / tile_x_size;
        return vector.get(index);
    }

    /**
     *
     * the purpose of this function is to be a getter to the private vector<Tile>
     * @warning return also when is_tile = false
     * @return the indexed tile
     * @throws IndexOutOfBoundsException if index out of range
     */
    public Tile get()
        throws IndexOutOfBoundsException {
        return get();//#TODO c'est quoi cette merde @Fife
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
        if (index >= nb_tiles || index < 0)
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
                res.add(t);
            }
        }
        return res;
    }

    public BufferedImage getSprites() {
        return sprites;
    }

    public int getTile_x_size() {
        return tile_x_size;
    }

    public int getTile_y_size() {
        return tile_y_size;
    }

    /**
     * draw the selected tile at the coordinates x,y on the graph
     *
     * @param t     the tile to be printed
     * @param x     the x position on the graph
     * @param y     the y position on the graph
     * @param graph where to print
     */
    public void drawtile(TilePair t, int x, int y, Graphics graph) {

        if( t == null)
            return;

        int tile_y = (t.tileIndex / tiles_per_line) * tile_y_size;
        int tile_x = (t.tileIndex % tiles_per_line) * tile_x_size;

        graph.drawImage(sprites,
                x, y, x + tile_x_size, y + tile_y_size,
                tile_x, tile_y,tile_x +  tile_x_size, tile_y + tile_y_size,
                null);
    }

    /**
     * draw the selected tiles at the x,y coordinates on the graph
     *
     * @param selection    the selection
     * @param x     the starting x coordinate
     * @param y     the starting y coordinate
     * @param graph where to print
     * @warning The size of the graph is not checked! Watchout for bounds yourself!
     */
    public static void drawselection(Selection selection, int x, int y, int w, int h, Graphics graph) {

        int wt = 1;
        int ht = 1;

        for (TilePair tp : selection.getTiles())
            if (tp != null)
            {
                wt = tp.getTileSet().tile_x_size;
                ht = tp.getTileSet().tile_y_size;
                break;
            }

        BufferedImage buffer = new BufferedImage(selection.getDimension().width * wt,
                selection.getDimension().height * ht, BufferedImage.TYPE_4BYTE_ABGR);

        Graphics graphics = buffer.createGraphics();

        for (int i = 0; i < selection.getDimension().height; i++) {
            for (int j = 0; j < selection.getDimension().width; j++) {

                TilePair tile = selection.getTiles().get(i * selection.getDimension().width + j);

                if (tile == null)
                    continue;

                tile.getTileSet().drawtile(tile, x + j * wt, y + i * ht, graphics);
            }
        }

        graph.drawImage(buffer.getScaledInstance(w,h,Image.SCALE_DEFAULT),0,0, null);
    }

    @Override
    public String toString() {
        return name;
    }
}
