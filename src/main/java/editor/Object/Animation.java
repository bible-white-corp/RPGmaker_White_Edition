package editor.Object;

import editor.Tiles.Tile;
import editor.Tiles.TilePair;

import java.util.List;
import java.util.Vector;

/**
 * This class is an animation with a name a vector of sprites (frames)
 */
public class Animation {
    private String name;
    private List<TilePair> sprites;
    private int cur = 0;

    /**
     * @return the next frame for the animation
     */
    public TilePair getNext(){
        TilePair res = sprites.get(cur++);
        if (cur == sprites.size())
            cur = 0;
        return res;
    }

    public List<TilePair> getSprites() {
        return sprites;
    }

    public void setSprites(List<TilePair> sprites) {
        this.sprites = sprites;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Animation(String name, List<TilePair> sprites) {
        this.name = name;
        this.sprites = sprites;
    }
}
