package editor.Tools.Undo;

import editor.Editor;
import editor.Maps.Layer;
import editor.Tiles.TilePair;

import java.util.Map;
import java.util.TreeMap;

public class DoneAction {
    private int levelIndex;
    private int layerIndex;
    private Map<Integer,TilePair> changes;

    public DoneAction(int levelIndex, int layer) {
        this.levelIndex = levelIndex;
        this.layerIndex = layer;
        changes = new TreeMap<>();
    }

    public void addAction(int index, TilePair old){
        changes.put(index, old);
    }

    public DoneAction cancelAction(){
        Layer layer = Editor.world.levelList.get(levelIndex).getLayers().get(layerIndex);
        DoneAction save = new DoneAction(levelIndex, layerIndex);

        for (Map.Entry<Integer, TilePair> entry : changes.entrySet()){
            save.addAction(entry.getKey(),layer.getFromIndex(entry.getKey()));
            layer.setFromIndex(entry.getValue(), entry.getKey());
        }

        return save;
    }

    public int getLayerIndex() {
        return layerIndex;
    }
}