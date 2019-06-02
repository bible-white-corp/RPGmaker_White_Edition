package engine.Controllers;

import javax.swing.event.EventListenerList;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.EventListener;

public class KeyBoardInput implements KeyListener {

    private EventListenerList listeners = new EventListenerList();

    @Override
    public void keyTyped(KeyEvent keyEvent) {

        for (KeyBoardListener listener : listeners.getListeners(KeyBoardListener.class))
            listener.keyTyped(keyEvent);
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

        for (KeyBoardListener listener : listeners.getListeners(KeyBoardListener.class))
            listener.keyPressed(keyEvent);
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

        for (KeyBoardListener listener : listeners.getListeners(KeyBoardListener.class))
            listener.keyReleased(keyEvent);
    }

    public void addKeyBoardListener(KeyBoardListener listener)
    {
        listeners.add(KeyBoardListener.class, listener);
    }

    public void removeKeyBoardListener(KeyBoardListener listener)
    {
        listeners.remove(KeyBoardListener.class, listener);
    }


    private static abstract class KeyBoardListener implements EventListener {

        public void keyPressed(KeyEvent keyEvent) {}
        public void keyTyped(KeyEvent keyEvent) {}
        public void keyReleased(KeyEvent keyEvent) {}
    }

    public abstract static class PlayerInputListener extends KeyBoardListener {}
    public abstract static class CameraInputListener extends KeyBoardListener {}
    public abstract static class MenuInputListener extends  KeyBoardListener {}
}
