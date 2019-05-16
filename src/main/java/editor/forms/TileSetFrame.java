package editor.forms;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TileSetFrame extends JScrollPane {

    TileSetDisplay display = new TileSetDisplay("src/main/resources/images/tileset_rpg.png");

    public TileSetFrame()
    {
        setViewportView(display);
    }
}

class TileSetDisplay extends JPanel {

    private BufferedImage bgImage;

    private int x;
    private int y;
    private int width = 32;
    private int height = 32;

    public TileSetDisplay(String path) {

        try
        {
            bgImage = ImageIO.read(new File(path));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        TileSetDisplay parent = this;

        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                x = mouseEvent.getPoint().x;
                y = mouseEvent.getPoint().y;

                parent.repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        g.drawImage(bgImage, 0, 0, null);
        g.fillRect(x - x % width,y - y % height,width,height);
    }

    public Dimension getPreferredSize() {
        return new Dimension(bgImage.getWidth(), bgImage.getHeight());
    }
}
