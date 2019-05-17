package editor.Forms;

import editor.Editor;
import editor.Maps.Maps;
import editor.Maps.World;
import editor.Tiles.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameFrame extends JPanel {

    World w;
    Maps maps;

    public GameFrame(World w) {

        this.w = w;
        maps = w.mapsList.get(0);
        w.mainFrame = this;

        MouseAdapter mouseAdapter = new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent mouseEvent) { GameFrame.this.paintSelection(mouseEvent);}

            @Override
            public void mouseDragged(MouseEvent mouseEvent) { GameFrame.this.paintSelection(mouseEvent);}
        };

        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);

        maps.addMapsListener(() -> GameFrame.this.repaint());
    }


    public void paintSelection(MouseEvent mouseEvent)
    {
        maps.addSelection(Editor.getSelection(), mouseEvent.getPoint().x, mouseEvent.getPoint().y);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.GRAY);
        g.fillRect(0,0, maps.getWidth() * 32, maps.getHeight() * 32);

        for(int y = 0; y < maps.getHeight(); ++y)
            for(int x = 0; x < maps.getWidth(); ++x) {
                Tile tile = maps.getTile(x, y);

                if (tile != null)
                    tile.getParent().drawtile(tile, x * 32, y * 32, g);
            }

        int w_pixel = maps.getWidth() * maps.getTileWidth();
        int h_pixel = maps.getHeight() * maps.getTileHeight();

        g.setColor(Color.WHITE);

        for (int y = 0; y < maps.getHeight(); ++y)
            g.drawLine(0,y * maps.getTileHeight(), w_pixel, y * maps.getTileHeight());

        for (int x = 0; x < maps.getWidth(); ++x)
            g.drawLine(x * maps.getTileWidth(),0, x * maps.getTileWidth(), h_pixel);
    }
}
