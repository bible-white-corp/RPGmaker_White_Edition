package editor.ProjectTree;

import editor.Editor;
import editor.Maps.Level;
import editor.Object.*;
import editor.Object.actions.instantiateObject;
import editor.Tiles.TileSet;
import editor.Tools.Brushes.ObjectEdit;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class PTree {

    public JTree myTree;

    private DefaultMutableTreeNode objects = new DefaultMutableTreeNode("Objects", true);
    private DefaultMutableTreeNode tileSets = new DefaultMutableTreeNode("TileSets", true);
    private DefaultMutableTreeNode levels = new DefaultMutableTreeNode("Levels", true);
    private DefaultMutableTreeNode spriteSheets = new DefaultMutableTreeNode("SpriteSheets", true);
    private DefaultMutableTreeNode sprites = new DefaultMutableTreeNode("Sprites", true);
    private DefaultMutableTreeNode animations = new DefaultMutableTreeNode("Animations", true);

    public PTree(String pName) {

        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(pName);
        DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);

        rootNode.add(tileSets);
        rootNode.add(levels);
        rootNode.add(spriteSheets);
        rootNode.add(sprites);
        rootNode.add(animations);
        rootNode.add(objects);

        myTree = new JTree(treeModel);
        myTree.setEditable(true);
        myTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        myTree.setShowsRootHandles(true);
        myTree.addMouseListener(new doubleClick());

    }

    public void addNewLevel(Level level, int index){

        levels.add(new DefaultMutableTreeNode(new pair(index, level.getName())));
        myTree.updateUI();
    }

    public void addNewObject(ObjectIntel obj, int index) {
        objects.add(new DefaultMutableTreeNode(new pair(index, obj.getName())));
        myTree.updateUI();
    }

    public void addNewTileSet(TileSet ts, int index){

        tileSets.add(new DefaultMutableTreeNode(new pair(index, ts.getName())));
        myTree.updateUI();
    }

    public void addNewSprite(Sprite sprite, int index) {
        sprites.add(new DefaultMutableTreeNode(new pair(index, sprite.toString())));
        myTree.updateUI();
    }


    public void addNewSpritesSheet(SpriteSheet sheet, int index){

        spriteSheets.add(new DefaultMutableTreeNode(new pair(index, sheet.toString())));
        myTree.updateUI();
    }

    private class pair{

        int index;
        String name;

        public pair(int index, String name) {
            this.index = index;
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public void addNewAnimation(Animation anim, int index) {
        animations.add(new DefaultMutableTreeNode(new pair(index, anim.toString())));
        myTree.updateUI();
    }

    public void reload(){

        objects.removeAllChildren();
        tileSets.removeAllChildren();
        levels.removeAllChildren();
        spriteSheets.removeAllChildren();
        sprites.removeAllChildren();

        myTree.treeDidChange();
        myTree.updateUI();

        for (int i = 0; i < Editor.world.levelList.size(); i++) {
            levels.add(new DefaultMutableTreeNode(new pair(i,
                    Editor.world.levelList.get(i).getName())));
        }

        for (int i = 0; i < Editor.world.tileSetList.size(); i++) {
            tileSets.add(new DefaultMutableTreeNode(new pair(i,
                    Editor.world.tileSetList.get(i).getName())));
        }

        List<ObjectIntel> objectIntels = Editor.world.worldObjects.getObjs();
        for (int i = 0; i < objectIntels.size(); i++) {
            objects.add(new DefaultMutableTreeNode(new pair(i, objectIntels.get(i).toString())));
        }

        List<SpriteSheet> sheetList = Editor.world.worldObjects.getSpriteSheetList();
        for (int i = 0; i < sheetList.size(); i++) {
            spriteSheets.add(new DefaultMutableTreeNode(new pair(i, sheetList.get(i).toString())));
        }

        List<Sprite> spriteList = Editor.world.worldObjects.getSpriteList();
        for (int i = 0; i < spriteList.size(); i++) {
            sprites.add(new DefaultMutableTreeNode(new pair(i, spriteList.get(i).toString())));
        }

        List<Animation> animationList = Editor.world.worldObjects.getAnimations();
        for (int i = 0; i < animationList.size(); i++) {
            animations.add(new DefaultMutableTreeNode(new pair(i, animationList.get(i).toString())));
        }

        myTree.getModel();
        myTree.updateUI();
    }

    private class doubleClick extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {

            if (e.getClickCount() >= 2 && e.getButton() == MouseEvent.BUTTON1) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                        myTree.getLastSelectedPathComponent();
                if (node == null) return;

                if (!node.isLeaf())
                    return;


                TreeNode parent = node.getParent();
                Object tmp = node.getUserObject();

                if (parent == tileSets) {
                    if (tmp instanceof pair)
                        Editor.editFrame.tileSetFrame.display.changeTileSet(((pair) tmp).index);
                    return;
                } else if (parent == levels) {
                    if (tmp instanceof pair) {
                        Editor.mainFrame.setLevel(((pair) tmp).index);
                        Editor.editFrame.tabbedPane.setSelectedIndex(0);
                    }
                } else if (parent == spriteSheets) {
                    if (tmp instanceof pair) {
                        Editor.editFrame.editionFrame.setSheet(((pair) tmp).index);
                        Editor.editFrame.tabbedPane.setSelectedIndex(1);
                    }
                } else if (parent == objects) {
                    if (tmp instanceof pair) {
                        Editor.editFrame.tabbedPane.setSelectedIndex(0);
                        instantiateObject.click(((pair)tmp).index);
                    }
                }
            }
            else if (e.getButton() == MouseEvent.BUTTON3){
                DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                        myTree.getLastSelectedPathComponent();
                if (node == null) return;

                if (!node.isLeaf())
                    return;

                TreeNode parent = node.getParent();
                Object tmp = node.getUserObject();

                if (parent == objects) {
                    if (tmp instanceof pair) {
                        customizeObjIntel((pair) tmp);
                    }
                }
                else if (parent == sprites) {
                    if (tmp instanceof pair) {
                        customizeSprite((pair) tmp);
                    }
                } else if (parent == animations) {
                    if (tmp instanceof pair) {
                        customizeAnimation((pair) tmp);
                    }
                }
            }
        }
    }

    private void customizeSprite(pair p) {
        Object[] possibilites = {"Rename", "Delete"};
        String s = (String) JOptionPane.showInputDialog(Editor.editFrame,
                "What do you want to do with " + p.name,
                "Customize sprite",
                JOptionPane.INFORMATION_MESSAGE,
                new ImageIcon(Editor.world.worldObjects.getSpriteList().get(p.index).getImage()),
                possibilites, "Rename");
        if (s == null)
            return;
        if (s == "Delete") {
            deleteSprite(p);
            sprites.remove((MutableTreeNode) myTree.getLastSelectedPathComponent());
            myTree.updateUI();
            return;
        }
        if (s == "Rename") {
            s = (String) JOptionPane.showInputDialog(Editor.editFrame,
                    "New name:", "Rename", JOptionPane.INFORMATION_MESSAGE);
            if (s == null)
                return;
            p.name = s;
            Editor.world.worldObjects.getSpriteList().get(p.index).setName(s);
            myTree.updateUI();
            return;
        }
    }

    private void customizeAnimation(pair p) {
        Object[] possibilites = {"Rename", "Delete"};
        String s = (String) JOptionPane.showInputDialog(Editor.editFrame,
                Editor.world.worldObjects.getAnimations().get(p.index).getNames()
                        + "\nWhat do you want to do with " + p.name,
                "Customize animation",
                JOptionPane.INFORMATION_MESSAGE,
                null,
                possibilites, "Rename");
        if (s == null)
            return;
        if (s == "Delete") {
            deleteAnim(p, false);
            animations.remove((MutableTreeNode) myTree.getLastSelectedPathComponent());
            myTree.updateUI();
            return;
        }
        if (s == "Rename") {
            s = (String) JOptionPane.showInputDialog(Editor.editFrame,
                    "New name:", "Rename", JOptionPane.INFORMATION_MESSAGE);
            if (s == null)
                return;
            p.name = s;
            Editor.world.worldObjects.getAnimations().get(p.index).setName(s);
            myTree.updateUI();
            return;
        }
    }

    private void deleteSprite(pair p) {
        List<Animation> animations = Editor.world.worldObjects.getAnimations();
        int res = JOptionPane.NO_OPTION;
        boolean asked = false;

        for (int i = 0; i < animations.size(); i++) {
            if (animations.get(i).is_sprite(p.index)) {
                if (!asked) {
                    res = JOptionPane.showConfirmDialog(Editor.editFrame, "This sprite is " +
                            "used in at least one animation, do you really want to delete it ?");
                    asked = true;
                }
                else if (res == JOptionPane.YES_OPTION)
                    animations.get(i).removeSprite(p.index);
                else
                    JOptionPane.showMessageDialog(Editor.editFrame,
                            "Deleting cancelled", "Cancelled",
                            JOptionPane.INFORMATION_MESSAGE);
            }

            //check if all anim still have at least one sprite
            if (animations.get(i).getSprites().isEmpty()) {
                this.animations.remove(i);
                deleteAnim(new pair(i, animations.get(i).getName()), true);
                animations.remove(i);
            }
        }
    }

    private void deleteAnim(pair p, boolean noSprite) {
        List<ObjectIntel> objs = Editor.world.worldObjects.getObjs();

        int res = JOptionPane.NO_OPTION;
        boolean asked = false;

        for (int i = 0; i < objs.size(); i++) { //for each obj
            if (objs.get(i).is_anim(p.index)) { //if anim used
                if (noSprite)
                    objs.get(i).removeAnimation(p.index);
                else {
                    if (!asked) {
                        res = JOptionPane.showConfirmDialog(Editor.editFrame, "This animation is " +
                                "used in at least one object, do you really want to delete it ?");
                        asked = true;
                    }
                    if (res == JOptionPane.YES_OPTION)
                        objs.get(i).removeAnimation(p.index);
                    else
                        JOptionPane.showMessageDialog(Editor.editFrame,
                                "Supression cancelled", "Cancelled",
                                JOptionPane.INFORMATION_MESSAGE);
                }
            }

            if (objs.get(i).getAnimations().isEmpty()) {
                this.objects.remove(i);
                myTree.updateUI();
                Editor.world.worldObjects.removeObject(i);
            }
        }
    }

    private void customizeObjIntel(pair p) {
        Object[] possibilites = {"Rename", "Delete"};
        String s = (String) JOptionPane.showInputDialog(Editor.editFrame,
                Editor.world.worldObjects.getObjs().get(p.index).getNames()
                        + "\nWhat do you want to do with " + p.name,
                "Customize objects",
                JOptionPane.INFORMATION_MESSAGE,
                null,
                possibilites, "Rename");
        if (s == null)
            return;
        if (s == "Delete") {
            Editor.world.worldObjects.removeObject(p.index);
            objects.remove((MutableTreeNode) myTree.getLastSelectedPathComponent());
            myTree.updateUI();
            Editor.world.objTree.reload();
            return;
        }
        if (s == "Rename") {
            s = (String) JOptionPane.showInputDialog(Editor.editFrame,
                    "New name:", "Rename", JOptionPane.INFORMATION_MESSAGE);
            if (s == null)
                return;
            p.name = s;
            Editor.world.worldObjects.getAnimations().get(p.index).setName(s);
            myTree.updateUI();
            return;
        }
    }
}
