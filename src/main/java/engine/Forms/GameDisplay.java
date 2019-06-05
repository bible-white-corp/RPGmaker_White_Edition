package engine.Forms;

import editor.Maps.Level;
import editor.Object.ObjectInstantiation;
import editor.Tiles.TilePair;
import engine.Controllers.Camera;
import engine.Controllers.GameKey;
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
    private long elapse_time = 20;

    public GameDisplay()
    {

        this.setFocusable(true);
        this.requestFocusInWindow();

        addComponentListener(onResize);

        addKeyListener(keyBoardInput);

        keyBoardInput.addKeyBoardListener(new KeyBoardInput.CameraInputListener() {

            private boolean isCamKeyPressed()
            {
                return  input.IsPressed(GameKey.Cam_Down) ||
                        input.IsPressed(GameKey.Cam_Zoom_Minus) ||
                        input.IsPressed(GameKey.Cam_Zoom_Plus) ||
                        input.IsPressed(GameKey.Cam_Up) ||
                        input.IsPressed(GameKey.Cam_Left) ||
                        input.IsPressed(GameKey.Cam_Right);
            }

            @Override
            public void computeKey() {

                float zoom = camera.getZoom();

                if (isCamKeyPressed()) {

                    Point focus = camera.getFocus();

                    if (input.IsPressed(GameKey.Cam_Up))
                        focus.y -= 10;

                    if (input.IsPressed(GameKey.Cam_Down))
                        focus.y += 10;

                    if (input.IsPressed(GameKey.Cam_Left))
                        focus.x -= 10;

                    if (input.IsPressed(GameKey.Cam_Right))
                        focus.x += 10;

                    camera.setFocus(focus);
                }

                if (input.IsPressed(GameKey.Cam_Zoom_Plus))
                    zoom *= 1.2;

                if (input.IsPressed(GameKey.Cam_Zoom_Minus))
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

                buffer_graph.drawImage(instantiation.getCurrentSprite().getImage(), instantiation.getPosition().x, instantiation.getPosition().y, null);
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
                Thread.sleep(elapse_time);
            }
            catch (InterruptedException e)
            {

            }

            keyBoardInput.tick();
            repaint();
        }
    }
}

