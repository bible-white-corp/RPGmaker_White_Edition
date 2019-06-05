package engine;

import editor.Maps.World;
import engine.Forms.EngineFrame;

import java.io.IOException;

public class Engine {

    public static World world;
    private static EngineFrame engineFrame;

    public static EngineFrame getEngineFrame() {
        return engineFrame;
    }

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

        engineFrame = new EngineFrame();
        engineFrame.setVisible(true);
        engineFrame.start();
    }
}
