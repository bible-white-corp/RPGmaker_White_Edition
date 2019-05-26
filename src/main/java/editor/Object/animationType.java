package editor.Object;

public enum animationType {

    IDLE(0),
    FORWARD(1),
    BACKWARD(2),
    LEFT(3),
    RIGHT(4);

    private final int value;

    animationType(final int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
