package editor.Forms;

import editor.Editor;
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

