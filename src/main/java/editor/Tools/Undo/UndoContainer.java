package editor.Tools.Undo;

import editor.Tiles.TilePair;

import java.util.ArrayDeque;

public class UndoContainer {
    private ArrayDeque<DoneAction> queue;

    public UndoContainer() {
        queue = new ArrayDeque<>();
    }

    public void add(DoneAction action){
        queue.add(action);
    }

    public void initNewAction(int levelIndex, int layerIndex){
        queue.addFirst(new DoneAction(levelIndex, layerIndex));
    }

    public void addToLast(TilePair old, int index){
        queue.element().addAction(index, old);
    }

    public DoneAction pop(){
        return queue.pollFirst();
    }

    public int lastLayer(){
        return queue.peekFirst().getLayerIndex();
    }

    public void clear(){
        queue.clear();
    }

    public int size(){
        return queue.size();
    }
}
