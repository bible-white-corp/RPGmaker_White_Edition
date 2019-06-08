package editor.Object.PathFinding;

public enum MoveType {
    idle("idle"),
    random("random"),
    simple_trip("simple_trip"),
    two_way("two_way"),
    infinite("infinite");

    private String name;

    private MoveType(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean needMov(){
        return this == simple_trip || this == two_way || this == infinite;
    }
}
