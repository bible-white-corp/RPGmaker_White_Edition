package engine;

import editor.Maps.World;
import engine.Forms.EngineFrame;

import java.io.IOException;

public class Engine {

    public static World world;

    public static void main(String[] args) {

        try
        {
            world = World.importWorld("Soutenance/");

            launchWorld(world);
        }
        catch (IOException e) {

            e.printStackTrace();
        }

    }

    public static void launchWorld(World world)
    {
        Engine.world = world;

        EngineFrame frame = new EngineFrame();
        frame.setVisible(true);
        frame.start();
    }
}
