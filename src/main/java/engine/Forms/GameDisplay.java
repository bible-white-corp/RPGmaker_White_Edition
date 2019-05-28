package engine.Forms;

import editor.Maps.Level;
import editor.Tiles.TilePair;
import engine.Engine;

import javax.swing.*;
import java.awt.*;

public class GameDisplay extends JPanel implements Runnable {

    Thread thread = null;
    Level level;

    public GameDisplay()
    {
        setLevel(0);
        start();
    }

    public void setLevel(int index) {

        this.level = Engine.world.levelList.get(index);
        this.repaint();

        if (level != null)
            this.level.addMapsListener(() -> GameDisplay.this.repaint());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (level == null)
            return;

        g.setColor(Color.GRAY);
        g.fillRect(0,0, level.getWidth() * 32, level.getHeight() * 32);

        for (int l = 0; l < 10; ++l)
            for(int y = 0; y < level.getHeight(); ++y)
                for(int x = 0; x < level.getWidth(); ++x) {
                    TilePair tile = level.getTile(x, y, l);

                    if (tile != null)
                        tile.getTileSet().drawtile(tile, x * 32, y * 32, g);
                }
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

