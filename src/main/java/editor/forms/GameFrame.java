package editor.forms;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameFrame extends JPanel {

    private BufferedImage bgImage;

    public GameFrame() {

        try
        {
            bgImage = ImageIO.read(new File("src/main/resources/images/test.jpg"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
            g.drawImage(bgImage, 0, 0, null);
    }
}
