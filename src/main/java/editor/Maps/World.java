package editor.Maps;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import editor.Editor;
import editor.Object.GameObjects;
import editor.ProjectTree.ObjTreeLevel;
import editor.ProjectTree.PTree;
import editor.Tiles.Tile;
import editor.Tiles.TilePair;
import editor.Tiles.TileSet;
import editor.Tools.Undo.UndoContainer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
    transient public ObjTreeLevel objTree;
    transient public UndoContainer undo = new UndoContainer();
    transient public UndoContainer redo = new UndoContainer();

    public World(String projectName) {
        this.projectName = projectName;
        this.levelList = new Vector<>();
        this.tileSetList = new Vector<>();
        this.worldObjects = new GameObjects();
        this.projectTree = new PTree(projectName);
        this.objTree = new ObjTreeLevel();
    }

    public World clone() {

        World world = new World(this.projectName + "_Launch");

        world.levelList = levelList;
        world.tileSetList = tileSetList;
        world.tsNames = tsNames;

        world.worldObjects = worldObjects.duplicate();

        return world;
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

        worldObjects.export(location);//to export spritesSheets

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
        res.undo = new UndoContainer();
        res.redo = new UndoContainer();
        try {
            res.savePath = path.split(res.projectName)[0];
            res.projectTree = Editor.world.projectTree;
            res.objTree = Editor.world.objTree;
        } catch (Exception e) {}
        res.worldObjects.importSprites(path);
        return res;
    }

    public Tile getTileFromPair(TilePair pair)
    {
        return tileSetList.get(pair.tileSetIndex).get(pair.tileIndex);//#TODO virer ca
    }

    public void reset(String name){
        this.projectName = name;
        this.levelList = new Vector<>();
        this.tileSetList = new Vector<>();
        this.worldObjects = new GameObjects();
        this.projectTree.reload();
        this.savePath = null;
    }
    //#TODO imports objects
    //#TODO load in inspector objects
}
