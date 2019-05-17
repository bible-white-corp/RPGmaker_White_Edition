package editor.Maps;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import editor.Tiles.Tile;
import editor.Tiles.TileSet;

import javax.imageio.ImageIO;
import java.io.*;
import java.util.List;
import java.util.Vector;

public class World {
    public List<Maps> mapsList;
    public transient List<TileSet> tileSetList;
    public List<String> tsNames;

    public World(){
        this.mapsList = new Vector<>();
        this.tileSetList = new Vector<>();
    }

    public List<String> getTsNames() {
        return tsNames;
    }

    public boolean createTileSet(String path, int x, int y){
        try {
            tileSetList.add(TileSet.create(path, x, y, tileSetList.size()));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean addMap(int height, int width, int tileHeight, int tileWidth, String name){
        mapsList.add(new Maps(height, width, tileHeight, tileWidth, name));
        return true;
    }

    public boolean importTileSet(String path){
        try {
            tileSetList.add(TileSet.importSet(path));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean exportMap(String path, String saveName) throws IOException {
        String location = path + "/" + saveName;
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
            ts.exportSet(location);
        }

        try {
            jsonWriter.close();
        } catch (java.io.IOException ioException) { }

        return true;
    }

    public static World importWorld(String path) throws IOException {
        FileReader f = new FileReader(path + "/world.json");
        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(f);
        World res = gson.fromJson(jsonReader, World.class);
        try {
            f.close();
        } catch (IOException e) {}

        res.tileSetList = new Vector<>();

        for (String str : res.tsNames){
            res.tileSetList.add(TileSet.importSet(path + "/" + str + "_Save"));
        }

        for (Maps w : res.mapsList){
            for (Tile t : w.getMap()){
                if (t != null){
                    t.setParent(res.tileSetList.get(t.parent_index));
                }
            }
        }

        return res;
    }

}
