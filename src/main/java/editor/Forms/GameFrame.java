package editor.Forms;

import editor.Editor;
import editor.Maps.Level;
import editor.Object.ObjectInstantiation;
import editor.Tiles.TilePair;
import editor.Tools.Selection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameFrame extends JPanel {

    Level level;
    int levelIndex = -1;

    public void setLevel(int index) {

        this.level = Editor.world.levelList.get(index);
        levelIndex = index;
        this.repaint();
        if (level != null)
            this.level.addMapsListener(() -> GameFrame.this.repaint());
    }

    public Level getLevel() {
        return level;
    }

    public GameFrame() {

        setLevel(0);
        Editor.mainFrame = this;

        MouseAdapter mouseAdapter = new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent mouseEvent)
            {
               Editor.getBrush().Clicked(level, Editor.getSelection(), mouseEvent.getX(), mouseEvent.getY());
            }

            @Override
            public void mouseDragged(MouseEvent mouseEvent)
            {
                Editor.getBrush().Dragged(level, Editor.getSelection(), mouseEvent.getX(), mouseEvent.getY());
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent)
            {
                Editor.getBrush().Released();
            }
        };

        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.GRAY);
        g.fillRect(0,0, level.getWidth() * 32, level.getHeight() * 32);

        for (int l = 0; l < 10; ++l)
            for(int y = 0; y < level.getHeight(); ++y)
                for(int x = 0; x < level.getWidth(); ++x) {
                    TilePair tile = level.getTile(x, y, l);

                    if (tile != null)
                        tile.getTileSet().drawtile(tile, x * 32, y * 32, g);
                }

        for (ObjectInstantiation instantiation : Editor.world.worldObjects.getInWorldObj())
        {
            if (instantiation != null && instantiation.getLevelIndex() == Editor.world.levelList.indexOf(level))
                g.drawImage(instantiation.getIntel().getIdle().getFirstSprite().getImage(), instantiation.getPosition().x, instantiation.getPosition().y, null);
        }

        int w_pixel = level.getWidth() * level.getTileWidth();
        int h_pixel = level.getHeight() * level.getTileHeight();

        g.setColor(Color.WHITE);

        if (Editor.isShow_grid()) {

            for (int y = 0; y < level.getHeight(); ++y)
                g.drawLine(0, y * level.getTileHeight(), w_pixel, y * level.getTileHeight());

            for (int x = 0; x < level.getWidth(); ++x)
                g.drawLine(x * level.getTileWidth(), 0, x * level.getTileWidth(), h_pixel);
        }

        if (Editor.getSelection().isOnWorld())
        {
            Selection s = Editor.getSelection();

            g.setColor(Color.RED);
            g.drawRect(s.getOrigin().x, s.getOrigin().y,
                    s.getDimension().width * level.getTileWidth(),
                    s.getDimension().height * level.getTileHeight()
                    );
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(level.getWidth() * level.getTileWidth(), level.getHeight() * level.getTileHeight());
    }

    public int getLevelIndex() {
        return levelIndex;
    }
}
