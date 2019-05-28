package editor.Forms;

public enum Path{
    StandStill(0),
    Random(1),
    LookBackward(2),
    BackAndForth(3),
    TurnAround(4);

    private int i;

    Path(int i) {
        this.i = i;
    }

    public static String[] getPath() {
        Path[] val = values();
        String[] pathList = new String[val.length];

        for (int i = 0; i < val.length; i++) {
            pathList[i] = val[i].name();
        }

        return pathList;
    }
}
