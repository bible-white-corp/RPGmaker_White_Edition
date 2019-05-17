package editor.Forms;

import editor.Maps.World;

import javax.swing.*;

public class TileSetFrame extends JScrollPane {

    World world;
    TileSetDisplay display;

    public TileSetFrame(World world)
    {
        display = new TileSetDisplay(world);
        setViewportView(display);
        this.world = world;
    }
}

