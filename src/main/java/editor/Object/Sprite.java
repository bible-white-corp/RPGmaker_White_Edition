package editor.Object;

import editor.Editor;

import java.awt.*;

public class Sprite {
    private int ssIndex;

    int x, y;
    String name;

    Dimension dimension;

    public Sprite(int x, int y, int width, int height, String name) {
        this.name = name;
        this.x = x;
        this.y = y;

        dimension = new Dimension(width, height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public SpriteSheet getSpriteSheet(){
        return Editor.world.worldObjects.spriteSheetList.get(ssIndex);
    }
}
