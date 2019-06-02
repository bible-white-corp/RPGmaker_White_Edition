package engine.Forms;

import editor.Maps.Level;
import editor.Object.ObjectInstantiation;
import editor.Tiles.TilePair;
import engine.Controllers.Camera;
import engine.Controllers.KeyBoardInput;
import engine.Engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class GameDisplay extends JPanel implements Runnable {

    KeyBoardInput keyBoardInput = new KeyBoardInput();
    Camera camera = new Camera();
    Level level = null;

    public GameDisplay()
    {

        this.setFocusable(true);
        this.requestFocusInWindow();

        addComponentListener(onResize);

        addKeyListener(keyBoardInput);

        keyBoardInput.addKeyBoardListener(new KeyBoardInput.CameraInputListener() {

            private boolean isCamKey(int e)
            {
                return e == KeyEvent.VK_W || e == KeyEvent.VK_A || e == KeyEvent.VK_S || e == KeyEvent.VK_D;
            }

            @Override
            public void keyPressed(KeyEvent e) {

                float zoom = camera.getZoom();

                if (isCamKey(e.getKeyCode())) {

                    Point focus = camera.getFocus();

                    if (e.getKeyCode() == KeyEvent.VK_W)
                        focus.y -= 10;

                    if (e.getKeyCode() == KeyEvent.VK_A)
                        focus.y += 10;

                    if (e.getKeyCode() == KeyEvent.VK_S)
                        focus.x -= 10;

                    if (e.getKeyCode() == KeyEvent.VK_D)
                        focus.x += 10;

                    camera.setFocus(focus);
                }

                if (e.getKeyCode() == KeyEvent.VK_1)
                    zoom *= 1.2;

                if (e.getKeyCode() == KeyEvent.VK_2)
                    zoom /= 1.2;

                camera.setZoom(zoom);
            }
        });

        setLevel(0);
    }

    public KeyBoardInput getKeyBoardInput() {
        return keyBoardInput;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setLevel(int index) {

        this.level = Engine.world.levelList.get(index);
        this.repaint();

        if (level != null)
            this.level.addMapsListener(() -> GameDisplay.this.repaint());
    }

    ComponentListener onResize = new ComponentAdapter() {
        @Override
        public void componentResized(ComponentEvent e) {
            super.componentResized(e);

            camera.setRatio((float)getHeight() / (float)getWidth());
        }
    };

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (level == null)
            return;

        int pixel_width = level.getWidth() * level.getTileWidth();
        int pixel_height = level.getHeight() * level.getTileHeight();

        g.setColor(Color.GRAY);
        g.fillRect(0,0, pixel_width , pixel_height);

        BufferedImage buffer = new BufferedImage(pixel_width,pixel_height, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics buffer_graph = buffer.getGraphics();

        for (int l = 0; l < 10; ++l)
            for(int y = 0; y < level.getHeight(); ++y)
                for(int x = 0; x < level.getWidth(); ++x) {
                    TilePair tile = level.getTile(x, y, l);

                    if (tile != null)
                        tile.getTileSet().drawtile(tile, x * level.getTileWidth(), y * level.getTileHeight(), buffer_graph);
                }

        for (ObjectInstantiation instantiation : Engine.world.worldObjects.getInWorldObj()) {

            if (instantiation != null && instantiation.getLevelIndex() == Engine.world.levelList.indexOf(level)) {

                buffer_graph.drawImage(instantiation.getIntel().getIdle().getFirstSprite().getImage(), instantiation.getPosition().x, instantiation.getPosition().y, null);
            }
        }
        g.drawImage(buffer,0,0,getWidth() ,getHeight(),
                camera.getFirst().x, camera.getFirst().y,
                camera.getSecond().x, camera.getSecond().y, null);
    }

    Thread thread = null;

    public void start() {

        if (thread == null) {

            thread = new Thread(this);
            thread.start();
        }
    }

    public void stop() {

        thread = null;
    }

    @Override
    public void run() {

        while (thread != null) {

            try
            {
                Thread.sleep(20);
            }
            catch (InterruptedException e)
            {

            }

            repaint();
        }
    }
}

