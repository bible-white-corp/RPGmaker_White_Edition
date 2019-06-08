package editor.Tools.Undo;

import editor.Editor;
import editor.Maps.Layer;
import editor.Maps.Level;
import editor.Tiles.TilePair;

import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

public class DoneAction {
    private int levelIndex;
    private int layerIndex;
    private Vector<actionParam> changes;

    public DoneAction(int levelIndex, int layer) {
        this.levelIndex = levelIndex;
        this.layerIndex = layer;
        changes = new Vector<>();
    }

    public void addAction(int index, TilePair old){
        changes.add(new actionParam(old, index));
    }

    public DoneAction cancelAction(){
        Level level = Editor.world.levelList.get(levelIndex);
        DoneAction save = new DoneAction(levelIndex, layerIndex);

        actionParam entry;
        for (int i = changes.size() - 1; i >= 0; i--){
            entry = changes.get(i);
            save.addAction(entry.index,level.getFromIndex(entry.index, layerIndex));
            level.setFromIndex(entry.tilePair, entry.index, layerIndex);
        }
        return save;
    }

    public int getLayerIndex() {
        return layerIndex;
    }

    public int size(){
        return changes.size();
    }
}