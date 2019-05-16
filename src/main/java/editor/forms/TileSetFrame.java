package editor.forms;

import editor.Editor;
import editor.Tiles.Tile;
import editor.Tiles.TileSet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class TileSetFrame extends JScrollPane {

    TileSetDisplay display = new TileSetDisplay("src/main/resources/images/tileset_rpg.png");

    public TileSetFrame()
    {
        setViewportView(display);
    }
}

class TileSetDisplay extends JPanel {

    TileSet tileSet;

    Point select = new Point(0,0);

    public TileSetDisplay(String path) {

        try
        {
            tileSet = TileSet.create(path, 32 , 32);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        TileSetDisplay parent = this;

        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

                select = mouseEvent.getPoint();
                Editor.setSelectedTile(tileSet.get(select.x,select.y), tileSet);

                parent.repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        g.drawImage(tileSet.getSprites(), 0, 0, null);
        g.fillRect(select.x - select.x % tileSet.getTile_x_size(),select.y - select.y % tileSet.getTile_y_size(),
                tileSet.getTile_x_size(),tileSet.getTile_y_size());
    }

    public Dimension getPreferredSize() {
        return new Dimension(tileSet.getSprites().getWidth(), tileSet.getSprites().getHeight());
    }
}
