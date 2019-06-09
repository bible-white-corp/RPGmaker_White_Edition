package editor.Object;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;

public class SpriteSheet {
    BufferedImage img;
    String name;

    public SpriteSheet(String path) throws IOException {
        File file = new File(path);
        if (!file.canRead()){
            throw new InvalidPathException(path, "cannot open and read at this path");
        }
        img = ImageIO.read(file);
        name = file.getName();
    }

    public BufferedImage getImg(){
        return img;
    }

    public void export(String path) throws IOException {
        File file = new File(path + "/" + name);
        ImageIO.write(img, "png", file);
    }

    public static SpriteSheet importSpriteSheet(String path) throws IOException {
        return new SpriteSheet(path);
    }

    @Override
    public String toString() {
        return name;
    }
}
