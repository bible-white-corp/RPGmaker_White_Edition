package editor.Object;

import editor.Editor;
import editor.Tiles.Tile;
import editor.Tiles.TilePair;

import java.util.List;
import java.util.Vector;

/**
 * This class is an animation with a name a vector of sprites (frames)
 */
public class Animation {
    private String name;
    private List<Integer> sprites;
    private int cur = 0;

    /**
     * @return the next frame for the animation
     */
    public Sprite getNext(){
        Sprite res = Editor.world.worldObjects.spriteList.get(cur++);
        if (cur == sprites.size())
            cur = 0;
        return res;
    }

    public List<Integer> getSprites() {
        return sprites;
    }

    public void setSprites(List<Integer> sprites) {
        this.sprites = sprites;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getNames(){
        List<String> res = new Vector<>();
        List<Sprite> list = Editor.world.worldObjects.getSpriteList();
        for (int i : sprites){
            res.add(list.get(i).toString());
        }
        return res;
    }

    @Override
    public String toString() {
        return name;
    }

    public Animation(String name, List<Integer> sprites) {
        this.name = name;
        this.sprites = sprites;
    }
}
