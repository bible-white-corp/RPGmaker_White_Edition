package editor.Object;

import editor.Editor;
import editor.Maps.Level;
import editor.Object.PathFinding.MoveType;
import editor.Object.PathFinding.PathSettings;
import editor.ProjectTree.objType;
import editor.Tiles.TilePair;

import java.awt.*;
import java.util.List;

public class ObjectInstantiation {
    protected int objIntelIndex;
    protected int levelIndex = Editor.mainFrame.getLevelIndex();
    protected String name;
    protected int layer;
    protected Point position;
    int index;
    int sibling_index = -1;
    int sibling_map_index;
    objType type;
    private String dialog;
    transient private int nextDest = 0;
    animationType curAnimType;

    transient int current_animation = 0;
    PathSettings path;


    public objType getType() {
        return type;
    }

    public ObjectInstantiation(int objIntelIndex, String name, objType type) {
        this.objIntelIndex = objIntelIndex;
        this.name = name;
        this.type = type;
        if (type == objType.NPC) {
            this.dialog = "Hello!";
        }
    }

    public PathSettings getPath() {
        if (path == null)
            path = new PathSettings(MoveType.idle, position, levelIndex, index);
        return path;
    }


    @Override
    public String toString() {
        return name;
    }

    public ObjectIntel getIntel(){
        return Editor.world.worldObjects.objs.get(objIntelIndex);
    }

    public Sprite getCurrentSprite()
    {
        return getIntel().getAnimation(current_animation).getSprite();
    }

    public String getName() {
        return name;
    }

    public Point getPosition() {
        return position;
    }

    public int getLevelIndex() {
        return levelIndex;
    }

    public int getObjIntelIndex() { return objIntelIndex; }

    public void setLevelIndex(int levelIndex) {
        this.levelIndex = levelIndex;
    }

    public int getIndex() {
        return index;
    }

    public void setDialog(String dialog) {
        this.dialog = dialog;
    }

    public String getDialog() {
        if (type != objType.NPC)
            return null;
        return dialog;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void finishInstantiation(int layer, Point position){
        this.layer = layer;
        this.index = Editor.world.worldObjects.getInWorldObj().size();
        this.position = position;
        if (this.type == objType.PLAYER) {
            if (Editor.world.worldObjects.getPlayer() != null)
                Editor.world.worldObjects.getInWorldObj().set(Editor.world.worldObjects.player.getIndex(), null);
            Editor.world.worldObjects.setPlayer(this);
            Editor.world.objTree.removeAllPlayers();
        }
        Editor.world.objTree.addObj(this, Editor.world.worldObjects.getInWorldObj().size(), type);
        Editor.world.worldObjects.addObjInstance(this);
        path = new PathSettings(MoveType.idle, position, levelIndex, index);
    }

    public void setSibling(int sibling_index, int sibling_map_index) {
        this.sibling_index = sibling_index;
        this.sibling_map_index = sibling_map_index;
    }

    public Level getSiblingLevel(){
        if (sibling_map_index == -1 || sibling_index == -1)
            return null;
        return Editor.world.levelList.get(sibling_map_index);
    }

    public ObjectInstantiation getSibling(){
        if (sibling_index == -1 || sibling_map_index == -1 )
            return null;
        return Editor.world.worldObjects.getInWorldObj().get(sibling_index);
    }

    public void setPosition(Point coords) {
        this.position.x = coords.x;
        this.position.y = coords.y;
    }

    public void setAnimation(animationType type) {
        current_animation = type.getValue();
        curAnimType = type;
    }

    public animationType getCurAnimType() {
        if (curAnimType == null)
            curAnimType = animationType.IDLE;
        return curAnimType;
    }

    public Animation getCurrentAnimation() {
        return getIntel().getAnimation(current_animation);
    }
}