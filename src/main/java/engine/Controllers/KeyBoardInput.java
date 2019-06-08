package engine.Controllers;

import javax.swing.event.EventListenerList;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.EventListener;

public class KeyBoardInput implements KeyListener {

    private boolean isPressed[] = new boolean[GameKey.Count];

    private EventListenerList listenerList = new EventListenerList();

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

    private void updateKey(KeyEvent keyEvent, boolean status){

        GameKey k = GameKey.getGameKey(keyEvent);

        if (k == null)
            return;

        isPressed[k.getIndex()] = status;
        isPressed[GameKey.Shift.getIndex()] = keyEvent.isShiftDown();
        isPressed[GameKey.Ctrl.getIndex()] = keyEvent.isControlDown();
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        updateKey(keyEvent, true);
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        updateKey(keyEvent, false);
    }

    public boolean IsPressed(GameKey key)
    {
        return isPressed[key.getIndex()];
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
    public abstract static class TeleporterInputListener extends  KeyBoardListener {}
}
