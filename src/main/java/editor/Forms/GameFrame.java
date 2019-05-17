package editor.Forms;

import editor.Editor;
import editor.Maps.Maps;
import editor.Tiles.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameFrame extends JPanel {

    Maps maps;

    public GameFrame() {

        loadMap("");

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

    private void loadMap(String path)
    {
        if (path == null || path == "") {

            maps = new Maps(100,100,32,32);
        }
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
            for(int x = 0; x < maps.getWidth(); ++x)
            {
                Tile tile = maps.getTile(x, y );

                if (tile != null)
                    tile.getParent().drawtile(tile, x * 32, y * 32, g);
            }
    }
}
