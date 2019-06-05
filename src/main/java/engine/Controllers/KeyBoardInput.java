package engine.Controllers;

import javax.swing.event.EventListenerList;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.EventListener;

public class KeyBoardInput implements KeyListener {

    private boolean isPressed[] = new boolean[GameKey.Count];

    private EventListenerList listenerList = new EventListenerList();

    private int key_to_enum[] = {
            KeyEvent.VK_LEFT,
            KeyEvent.VK_RIGHT,
            KeyEvent.VK_UP,
            KeyEvent.VK_DOWN,
            KeyEvent.VK_A,
            KeyEvent.VK_D,
            KeyEvent.VK_S,
            KeyEvent.VK_W,
            KeyEvent.VK_1,
            KeyEvent.VK_2,
            KeyEvent.VK_ESCAPE,
    };

    public void tick()
    {
        for (KeyBoardListener listener : listenerList.getListeners(KeyBoardListener.class))
        {
            listener.computeKey();
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    private int index_keyEvent(KeyEvent keyEvent)
    {
        for (int i = 0; i < GameKey.Count; ++i)
            if (key_to_enum[i] == keyEvent.getKeyCode())
                return  i;

        return -1;
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

        int v = index_keyEvent(keyEvent);

        if (v >= 0)
            isPressed[v] = true;
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

        int v = index_keyEvent(keyEvent);

        if (v >= 0)
            isPressed[v] = false;
    }

    public boolean IsPressed(GameKey key)
    {
        return isPressed[key.ordinal()];
    }

    public void addKeyBoardListener(KeyBoardListener listener)
    {
        listener.input = this;
        listenerList.add(KeyBoardListener.class, listener);
    }

    public void removeKeyBoardListener(KeyBoardListener listener)
    {
        listener.input = null;
        listenerList.remove(KeyBoardListener.class, listener);
    }

    private static abstract class KeyBoardListener implements EventListener {

        public KeyBoardInput input;

        public abstract void computeKey();
    }

    public abstract static class PlayerInputListener extends KeyBoardListener {}
    public abstract static class CameraInputListener extends KeyBoardListener {}
    public abstract static class MenuInputListener extends  KeyBoardListener {}
}
