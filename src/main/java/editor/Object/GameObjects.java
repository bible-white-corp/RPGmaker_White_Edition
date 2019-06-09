package editor.Object;

import editor.Editor;
import editor.ProjectTree.objType;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

/**
 * This class handles world's objects
 */
public class GameObjects {
    List<ObjectIntel> objs;///mapping of objects_animations by their given name
    List<Animation> animations;///mapping of all user's animations
    List<ObjectInstantiation> instantiations;//list of all instantiated objects on the map
    List<Sprite> spriteList;///list of sprites created until now
    transient List<SpriteSheet> spriteSheetList;///list of spritesSheets imported
    List<String> spritesSheetNames;
    List<ObjectInstantiation> inWorldObj;///list of objects instatiated until now in all levels
    ObjectInstantiation player;///the player (if any)

    public GameObjects() {
        objs = new Vector<>();
        spriteList = new Vector<>();
        animations = new Vector<>();
        instantiations = new Vector<>();
        spriteSheetList = new Vector<>();
        spritesSheetNames = new Vector<>();
        inWorldObj = new Vector<>();
    }

    public void export(String path){
        String location = path + "/SpritesSheets_Save";
        File f = new File(location);
        f.mkdirs();

        spritesSheetNames = new Vector<>();
        for (SpriteSheet sheet : spriteSheetList){
            spritesSheetNames.add(sheet.name);
            new Thread(() -> {
                try {
                    sheet.export(location);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    public void importSprites(String path) throws IOException {
        spriteSheetList = new Vector<>();
        String location = path + "/SpritesSheets_Save/";
        for (String str : spritesSheetNames){
            spriteSheetList.add(SpriteSheet.importSpriteSheet(location + str));
        }
    }

    public List<ObjectIntel> getObjs() {
        return objs;
    }

    public List<Animation> getAnimations() {
        return animations;
    }

    public List<Sprite> getSpriteList() {
        return spriteList;
    }

    public List<ObjectInstantiation> getInWorldObj() {
        return inWorldObj;
    }

    public ObjectInstantiation getPlayer() {

        for (ObjectInstantiation oi : inWorldObj)
        {
            if (oi != null && oi.getType() == objType.PLAYER)
                return oi;
        }

        return null;
    }

    public void setPlayer(ObjectInstantiation player) {
        this.player = player;
    }

    public void addObject(String name, boolean is_static, List<Integer> animList){
        ObjectIntel tmp = new ObjectIntel(name, is_static, animList);
        Editor.world.projectTree.addNewObject(tmp, objs.size());
        objs.add(tmp);
    }

    public void removeObject(int objectIntelIndex) {
        for (int i = 0; i < inWorldObj.size(); i++) {
            if (inWorldObj.get(i).getObjIntelIndex() == objectIntelIndex)
                inWorldObj.remove(i);
        }

        objs.remove(objectIntelIndex);
    }

    public void removeAnimation(int animIndex) {
        for (int i = 0; i < inWorldObj.size(); i++) {
            for (int j = 0; j < inWorldObj.get(i).getIntel().getAnimations().size(); j++) {
                if (inWorldObj.get(i).getIntel().getAnimations().get(j) == animIndex)
                    inWorldObj.get(i).getIntel().getAnimations().remove(j);
            }
        }

        animations.remove(animIndex);
    }

    public void addSpriteSheet(String path) throws IOException {
        SpriteSheet sheet = SpriteSheet.importSpriteSheet(path);
        Editor.world.projectTree.addNewSpritesSheet(sheet, spriteSheetList.size());
        spriteSheetList.add(sheet);
        if (!Editor.editFrame.editionFrame.is_set()){
            Editor.editFrame.editionFrame.setSheet(spriteSheetList.size() - 1);
        }
    }

    public void addSprite(Sprite sprite){
        Editor.world.projectTree.addNewSprite(sprite, spriteList.size());
        spriteList.add(sprite);
    }

    public List<SpriteSheet> getSpriteSheetList() {
        return spriteSheetList;
    }

    public void addAnimation(String animationName, List<Integer> sprites){
        Animation tmp = new Animation(animationName, sprites);
        Editor.world.projectTree.addNewAnimation(tmp, animations.size());
        animations.add(tmp);
    }

    public void addObjInstance(ObjectInstantiation instantiation){
        this.inWorldObj.add(instantiation); }

    public GameObjects duplicate() {

        GameObjects gameObjects = new GameObjects();

        gameObjects.objs = objs;
        gameObjects.spriteList = spriteList;
        gameObjects.instantiations = instantiations;
        gameObjects.animations = animations;
        gameObjects.spriteSheetList = spriteSheetList;
        gameObjects.spritesSheetNames = spritesSheetNames;

        gameObjects.inWorldObj = new Vector<>();

        for (ObjectInstantiation oi : inWorldObj)
            gameObjects.inWorldObj.add(oi != null ? oi.duplicate() : null);

        return gameObjects;
    }
}
