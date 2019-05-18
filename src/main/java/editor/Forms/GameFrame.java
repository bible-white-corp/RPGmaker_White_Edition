package editor.Forms;

import editor.Editor;
import editor.Maps.Level;
import editor.Maps.World;
import editor.Tiles.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameFrame extends JPanel {

    Level level;

    public void setLevel(Level level) {
        this.level = level;
    }

    public GameFrame() {

        level = Editor.world.levelList.get(0);
        Editor.mainFrame = this;

        MouseAdapter mouseAdapter = new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent mouseEvent) { GameFrame.this.paintSelection(mouseEvent);}

            @Override
            public void mouseDragged(MouseEvent mouseEvent) { GameFrame.this.paintSelection(mouseEvent);}
        };

        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);

        level.addMapsListener(() -> GameFrame.this.repaint());
    }


    public void paintSelection(MouseEvent mouseEvent)
    {
        level.addSelection(Editor.getSelection(), mouseEvent.getPoint().x, mouseEvent.getPoint().y);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.GRAY);
        g.fillRect(0,0, level.getWidth() * 32, level.getHeight() * 32);

        for(int y = 0; y < level.getHeight(); ++y)
            for(int x = 0; x < level.getWidth(); ++x) {
                Tile tile = level.getTile(x, y);

                if (tile != null)
                    tile.getParent().drawtile(tile, x * 32, y * 32, g);
            }

        int w_pixel = level.getWidth() * level.getTileWidth();
        int h_pixel = level.getHeight() * level.getTileHeight();

        g.setColor(Color.WHITE);

        for (int y = 0; y < level.getHeight(); ++y)
            g.drawLine(0,y * level.getTileHeight(), w_pixel, y * level.getTileHeight());

        for (int x = 0; x < level.getWidth(); ++x)
            g.drawLine(x * level.getTileWidth(),0, x * level.getTileWidth(), h_pixel);
    }
}
