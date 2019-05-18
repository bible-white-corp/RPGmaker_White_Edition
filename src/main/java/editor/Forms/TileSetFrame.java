package editor.Forms;

import editor.Maps.World;

import javax.swing.*;

public class TileSetFrame extends JScrollPane {

    TileSetDisplay display;

    public TileSetFrame()
    {
        display = new TileSetDisplay();
        setViewportView(display);
    }
}

