package engine.Forms;

import editor.Maps.Level;
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

            @Override
            public void keyPressed(KeyEvent e) {

                Point focus = camera.getFocus();
                float zoom = camera.getZoom();

                if (e.getKeyCode() == KeyEvent.VK_UP)
                    focus.y -= 10;

                if (e.getKeyCode() == KeyEvent.VK_DOWN)
                    focus.y += 10;

                if (e.getKeyCode() == KeyEvent.VK_LEFT)
                    focus.x -= 10;

                if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                    focus.x += 10;

                if (e.getKeyCode() == KeyEvent.VK_1)
                    zoom *= 1.2;

                if (e.getKeyCode() == KeyEvent.VK_2)
                    zoom /= 1.2;

                camera.setFocus(focus);
                camera.setZoom(zoom);
            }
        });

        setLevel(0);
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

