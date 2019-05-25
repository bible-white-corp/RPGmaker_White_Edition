package editor.Object;

import editor.Editor;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Sprite {
    private int ssIndex;

    int x, y;
    String name;

    Dimension dimension;

    public Sprite(int x, int y, int width, int height, String name, int index) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.ssIndex = index;

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

    @Override
    public String toString() {
        return name;
    }

    public SpriteSheet getSS(){
        return Editor.world.worldObjects.spriteSheetList.get(ssIndex);
    }

    public void setName(String name) {
        this.name = name;
    }

    public BufferedImage getImage(){
        return Editor.world.worldObjects.spriteSheetList.get(ssIndex).img.getSubimage(x, y,
                dimension.width, dimension.height);
    }
}
