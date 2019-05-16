package editor.forms;

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

        maps = new Maps(100,100,32,32);
        Editor.setCurrent_map(maps);

        GameFrame game = this;

        addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

                maps.addTile(mouseEvent.getPoint().x, mouseEvent.getPoint().y, Editor.getSelected_tile().tile);
                game.repaint();
            }

            @Override
            public void mouseDragged(MouseEvent mouseEvent) {

                maps.addTile(mouseEvent.getPoint().x, mouseEvent.getPoint().y, Editor.getSelected_tile().tile);
                game.repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.GRAY);
        g.fillRect(0,0, maps.width * 32, maps.height * 32);

        for(int y = 0; y < maps.height; ++y)
            for(int x = 0; x < maps.width; ++x)
            {
                Tile tile = maps.get(x, y );

                if (tile != null)
                    tile.getParent().drawtile(tile, x * 32, y * 32, g);
            }
    }
}
