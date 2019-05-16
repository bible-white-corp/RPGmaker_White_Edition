package editor.Tiles;

public class Tile {
    private boolean is_tile = false;
    private boolean is_walkable = true;
    private String name;
    private long layer = 0;
    private int index;

    public boolean is_tile() {
        return is_tile;
    }

    public void set_isTile(boolean is_tile) {
        this.is_tile = is_tile;
    }


    public boolean isWalkable() {
        return is_walkable;
    }

    public void setWalkable(boolean is_walkable) {
        this.is_walkable = is_walkable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getLayer() {
        return layer;
    }

    public void setLayer(long layer) {
        this.layer = layer;
    }

    @Override
    public boolean equals(Object obj) {
            return obj instanceof Tile && ((Tile) obj).index == index;
    }

    public int getIndex() {
        return index;
    }

    public Tile(int index) {
        this.index = index;
    }
}