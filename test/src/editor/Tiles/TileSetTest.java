package editor.Tiles;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TileSetTest {

    @Test
    void Tiles()
    {
        Tile t = new Tile(0);
        assert t.getIndex() == 0;

        assert t.isWalkable();
        t.setWalkable(false);
        assertFalse(t.isWalkable());

        assert t.is_tile() ;
        t.set_isTile(false);
        assertFalse(t.is_tile());

        assert t.getLayer() == 0;
        t.setLayer(1);
        assert t.getLayer() == 1;

        t.setName("test");
        assert t.getName().equals("test");

        Tile t2 = new Tile(1);
        assertNotEquals(t,t2);
    }

    @Test
    void create() {
        TileSet ts = null;
        try {
            ts = TileSet.create(getClass().getResource("tiles.jpg").getPath(), 16,16);
        } catch (IOException e) {
            assert(false);
        }
        assert ts != null;
    }

    @Test
    void importSet() {
    }

    @Test
    void exportSet() {
    }

    @Test
    void get() {
        TileSet ts = null;
        try {
            ts = TileSet.create(getClass().getResource("tiles.jpg").getPath(), 16,16);
        } catch (IOException e) {
            assert(false);
        }
        Tile t1 = ts.get(0);
        assert t1.equals(new Tile(0));
        try {
            ts.get(-1);
            assert false;
        } catch (IndexOutOfBoundsException e) {
            assert true;
        }
        try {
            ts.get(30000);
            assert false;
        } catch (IndexOutOfBoundsException e) {
            assert true;
        }
        t1 = ts.get(50);
        assert t1.equals(new Tile(50));
    }

    @Test
    void get1() {
        TileSet ts = null;
        try {
            ts = TileSet.create(getClass().getResource("tiles.jpg").getPath(), 16,16);
        } catch (IOException e) {
            assert(false);
        }
        Tile t1 = ts.get(0, 0);
        assert t1.equals(new Tile(0));
        try {
            ts.get(-1,-1);
            assert false;
        } catch (IndexOutOfBoundsException e) {
            assert true;
        }
        try {
            ts.get(30000,30000);
            assert false;
        } catch (IndexOutOfBoundsException e) {
            assert true;
        }
        t1 = ts.get(50,50);
        assert t1.equals(new Tile(150));
    }

    @Test
    void select() {
    }

    @Test
    void getSprites() {
    }
}