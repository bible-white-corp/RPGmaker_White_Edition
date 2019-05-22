package editor.Forms;

import editor.Editor;
import editor.Maps.World;
import editor.Tiles.Tile;
import editor.Tiles.TilePair;
import editor.Tiles.TileSet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TileSetDisplay extends JPanel {

    TileSet tileSet;

    Point first = new Point(0,0);
    Point second = new Point(0,0);

    public TileSetDisplay(int index) {

        tileSet = Editor.world.tileSetList.get(index);

        MouseAdapter mouseAdapter = new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

                first = mouseEvent.getPoint();
                second = mouseEvent.getPoint();

                List<TilePair> list = new ArrayList<>();

                list.add(new TilePair(tileSet.get(first.x, first.y)));

                Editor.getSelection().setSelection(list, new Dimension(1,1));

                TileSetDisplay.this.repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {

                second = e.getPoint();

                List<TilePair> list = new ArrayList<>();

                int x1 = Math.min(first.x, second.x);
                int x2 = Math.max(first.x, second.x);
                int y1 = Math.min(first.y, second.y);
                int y2 = Math.max(first.y, second.y);

                x1 = x1 - x1 % tileSet.getTile_x_size();
                x2 = x2 - x2 % tileSet.getTile_x_size();

                y1 = y1 - y1 % tileSet.getTile_y_size();
                y2 = y2 - y2 % tileSet.getTile_y_size();

                for (int y = y1; y <= y2; y += tileSet.getTile_y_size())
                    for (int x = x1; x <= x2; x += tileSet.getTile_x_size())
                        list.add(new TilePair(tileSet.get(x,y)));

                Editor.getSelection().setSelection(list,
                        new Dimension((x2-x1) / tileSet.getTile_x_size() + 1 , (y2-y1) / tileSet.getTile_y_size() + 1));

                TileSetDisplay.this.repaint();
            }
        };

        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }

    public void changeTileSet(int index){
        if (index >= Editor.world.tileSetList.size()) {
            tileSet = null;
            this.repaint();
            return;
        }
        tileSet = Editor.world.tileSetList.get(index);
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (tileSet == null)
            return;

        g.drawImage(tileSet.getSprites(), 0, 0, null);

        int x1 = Math.min(first.x, second.x);
        int x2 = Math.max(first.x, second.x);
        int y1 = Math.min(first.y, second.y);
        int y2 = Math.max(first.y, second.y);

        x1 = x1 - x1 % tileSet.getTile_x_size();
        x2 = x2 - x2 % tileSet.getTile_x_size();

        y1 = y1 - y1 % tileSet.getTile_y_size();
        y2 = y2 - y2 % tileSet.getTile_y_size();

        g.setColor(new Color(0.5f,0.5f,0.5f,0.7f));
        g.fillRect(x1, y1, x2 + tileSet.getTile_x_size() - x1, y2 + tileSet.getTile_y_size() - y1);
    }

    @Override
    public Dimension getPreferredSize() {

        if (tileSet == null)
            return new Dimension(0,0);

        return new Dimension(tileSet.getSprites().getWidth(), tileSet.getSprites().getHeight());
    }
}
