package editor.Object;

import editor.Tiles.Tile;

import java.util.List;
import java.util.Vector;

/**
 * This class is an animation with a name a vector of sprites (frames)
 */
public class Animation {
    private String name;
    private List<Tile> sprites;
    private int cur = 0;

    /**
     * @return the next frame for the animation
     */
    public Tile getNext(){
        Tile res = sprites.get(cur++);
        if (cur == sprites.size())
            cur = 0;
        return res;
    }

    public List<Tile> getSprites() {
        return sprites;
    }

    public void setSprites(Vector<Tile> sprites) {
        this.sprites = sprites;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Animation(String name, List<Tile> sprites) {
        this.name = name;
        this.sprites = sprites;
    }
}
