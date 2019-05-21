package editor.Forms;

import editor.Maps.World;

import javax.swing.*;

public class TileSetFrame extends JScrollPane {

    public TileSetDisplay display;

    public TileSetFrame()
    {
        display = new TileSetDisplay(0);
        setViewportView(display);
    }
}

