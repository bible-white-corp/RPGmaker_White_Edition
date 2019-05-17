package editor.Forms;

import javax.swing.*;

public class TileSetFrame extends JScrollPane {

    TileSetDisplay display = new TileSetDisplay("src/main/resources/images/tileset_rpg.png");

    public TileSetFrame()
    {
        setViewportView(display);
    }
}

