package editor.Maps;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import editor.Editor;
import editor.Forms.GameFrame;
import editor.Object.GameObjects;
import editor.ProjectTree.PTree;
import editor.Tiles.Tile;
import editor.Tiles.TileSet;

import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.Vector;

public class World {
    public List<Level> levelList;
    public transient List<TileSet> tileSetList;
    public List<String> tsNames;
    public String projectName;
    public GameObjects worldObjects;
    transient String savePath;
    transient public PTree projectTree;

    public World(String projectName) {
        this.projectName = projectName;
        this.levelList = new Vector<>();
        this.tileSetList = new Vector<>();
        this.worldObjects = new GameObjects();
        this.projectTree = new PTree(projectName);
    }

    public List<String> getTsNames() {
        return tsNames;
    }

    public boolean createTileSet(String path, int x, int y) {
        try {
            TileSet tmp = TileSet.create(path, x, y, tileSetList.size());
            Editor.world.projectTree.addNewTileSet(tmp, tileSetList.size());
            tileSetList.add(tmp);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean addMap(int height, int width, int tileHeight, int tileWidth, String name) {
        Level tmp = new Level(height, width, tileHeight, tileWidth, name);
        Editor.world.projectTree.addNewLevel(tmp, levelList.size());
        return levelList.add(tmp);
    }

    public boolean importTileSet(String path) {
        try {
            TileSet tmp = TileSet.importSet(path);
            Editor.world.projectTree.addNewTileSet(tmp, tileSetList.size());
            return tileSetList.add(tmp);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean quick_export(){
        return exportMap(savePath);
    }

    public boolean has_quickPath(){
        return savePath != null && !savePath.isEmpty();
    }

    public boolean exportMap(String path) {
        String location = path + "/" + projectName;
        File f = new File(location);
        f.mkdirs();

        tsNames = new Vector<>();
        for (TileSet tileSet : tileSetList)
            tsNames.add(tileSet.getName());

        Gson gson = new Gson();
        FileWriter fileWriter;

        try {
            fileWriter = new FileWriter(location + "/world.json");
        } catch (IOException e) {

            e.printStackTrace();
            return false;
        }

        JsonWriter jsonWriter = new JsonWriter(fileWriter);
        gson.toJson(this, fileWriter);
        for (TileSet ts : tileSetList) {
            new Thread(() -> {
                try {
                    ts.exportSet(location);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();//#TODO recuperer les booleans de statut.
        }

        try {
            fileWriter.close();
        } catch (IOException e) {
        }
        savePath = path;

        return true;
    }

    public static World importWorld(String path) throws IOException {
        FileReader f = new FileReader(path + "/world.json");
        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(f);
        World res = gson.fromJson(jsonReader, World.class);

        res.tileSetList = new Vector<>();

        for (String str : res.tsNames) {
            res.tileSetList.add(TileSet.importSet(path + "/" + str + "_Save"));
        }

        for (Level w : res.levelList) {

            w.initializeListener();
        }

        try {
            f.close();
        } catch (IOException e) { }
        res.savePath = path;
        res.projectTree = Editor.world.projectTree;
        return res;
    }

}
