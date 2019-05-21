package editor.Maps;

import editor.Tiles.TilePair;

import java.util.List;
import java.util.Vector;

public class Layer {

    private List<TilePair> data;

    public Layer(int nb_tiles)
    {
        data = new Vector<>(nb_tiles);

        for (int i = 0; i < nb_tiles; ++i)
            data.add(null);
    }

    public TilePair getFromIndex(int index)
    {
        return data.get(index);
    }

    public void setFromIndex(TilePair t, int index)
    {
        data.set(index, t);
    }
}
