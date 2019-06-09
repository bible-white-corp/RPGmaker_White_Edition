package engine;

import editor.Editor;
import editor.Maps.World;
import engine.Forms.EngineFrame;

import java.io.IOException;
import java.nio.file.Paths;

public class Engine {

    public static World world;
    private static EngineFrame engineFrame;
    public static boolean gamemode = false;

    public static EngineFrame getEngineFrame() {
        return engineFrame;
    }

    public static void main(String[] args) {

        try
        {
            String pName = Paths.get(Engine.class.getProtectionDomain().getCodeSource()
                    .getLocation().getPath()).getFileName().toString().split("_BWC.jar")[0];
            System.out.println(pName);
            gamemode = true;
            world = World.importWorld(pName);
            launchWorld(world);
        }
        catch (IOException e) {

            e.printStackTrace();
        }

    }

    public static void launchWorld(World world)
    {
        Engine.world = world;
        Editor.world = world;
        engineFrame = new EngineFrame(50,2);
        engineFrame.setVisible(true);
        engineFrame.start();
    }
}