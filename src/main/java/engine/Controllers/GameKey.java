package engine.Controllers;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public enum GameKey {

    Left(0, KeyEvent.VK_LEFT),
    Right(1, KeyEvent.VK_RIGHT),
    Up(2, KeyEvent.VK_UP),
    Down(3, KeyEvent.VK_DOWN),
    Cam_Left(4, KeyEvent.VK_A),
    Cam_Right(5, KeyEvent.VK_D),
    Cam_Down(6, KeyEvent.VK_S),
    Cam_Up(7, KeyEvent.VK_W),
    Cam_Zoom_Plus(8, KeyEvent.VK_1),
    Cam_Zoom_Minus(9, KeyEvent.VK_2),
    Esc(10, KeyEvent.VK_ESCAPE),
    Shift(11, KeyEvent.VK_SHIFT),
    Ctrl(12, KeyEvent.VK_CONTROL);

    private int index;
    private int keyCode;

    private GameKey(int index, int keyCode){
        this.index = index;
        this.keyCode = keyCode;
    }

    public int getIndex(){
        return this.index;
    }

    public static GameKey getGameKey(KeyEvent keyEvent){
        Object[] array = Arrays.stream(GameKey.values()).filter(w -> w.keyCode == keyEvent.getKeyCode()).toArray();
        return array.length == 0 ? null : (GameKey) array[0];
    }

    public static int Count = GameKey.values().length;
}
