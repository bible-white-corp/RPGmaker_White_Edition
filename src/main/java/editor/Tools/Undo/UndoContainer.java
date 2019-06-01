package editor.Tools.Undo;

import editor.Tiles.TilePair;

import java.util.ArrayDeque;
import java.util.Queue;

public class UndoContainer {
    private Queue<DoneAction> queue;

    public UndoContainer() {
        queue = new ArrayDeque<>();
    }

    public void add(DoneAction action){
        queue.add(action);
    }

    public void initNewAction(int levelIndex, int layerIndex){
        queue.add(new DoneAction(levelIndex, layerIndex));
    }

    public void addToLast(TilePair old, int index){
        queue.element().addAction(index, old);
    }

    public DoneAction pop(){
        return queue.poll();
    }

    public int lastLayer(){
        return queue.element().getLayerIndex();
    }

    public void clear(){
        queue.clear();
    }
}
