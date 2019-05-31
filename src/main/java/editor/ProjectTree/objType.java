package editor.ProjectTree;

public enum objType{
    ITEM(0),
    NPC(1),
    TELEPORTER(2),
    PLAYER(3);

    private final int value;

    objType(final int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
