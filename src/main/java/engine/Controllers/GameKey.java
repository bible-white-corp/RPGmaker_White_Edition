package engine.Controllers;

public enum GameKey {

    Left,
    Right,
    Up,
    Down,
    Cam_Left,
    Cam_Right,
    Cam_Down,
    Cam_Up,
    Cam_Zoom_Plus,
    Cam_Zoom_Minus;

    public static int Count = GameKey.values().length;
}
