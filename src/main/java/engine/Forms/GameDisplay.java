package engine.Forms;

import editor.Maps.Level;
import editor.Tiles.TilePair;
import engine.Engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class GameDisplay extends JPanel implements Runnable {

    Thread thread = null;
    Level level;

    Rectangle window = new Rectangle(0,0,1000,500);

    Point focus = new Point(0,0);
    float zoom = 1;

    public GameDisplay()
    {

        this.setFocusable(true);
        this.requestFocusInWindow();

        addComponentListener(onResize);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

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

                computeWindow();
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

            computeWindow();
        }
    };

    public void computeWindow()
    {
        int width = (int)(10 * level.getTileWidth() * zoom);
        int height = (width * getHeight() / getWidth());

        window = new Rectangle(focus.x - width , focus.y -height , focus.x + width, focus.y + height);
    }

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

        g.drawImage(buffer,0,0,getWidth() ,getHeight(), window.x, window.y, window.width, window.height, null);
    }

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
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
            repaint();
        }
        thread = null;
    }
}

