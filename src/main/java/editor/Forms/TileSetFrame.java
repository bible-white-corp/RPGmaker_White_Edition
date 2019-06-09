package editor.Forms;

import javax.swing.*;

public class TileSetFrame extends JScrollPane {

    public TileSetDisplay display;

    public TileSetFrame()
    {
        display = new TileSetDisplay(0);
        setViewportView(display);
    }
}

