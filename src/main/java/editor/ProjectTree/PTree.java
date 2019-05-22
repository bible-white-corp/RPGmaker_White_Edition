package editor.ProjectTree;

import editor.Editor;
import editor.Maps.Level;
import editor.Object.GameObjects;
import editor.Object.ObjectIntel;
import editor.Object.SpriteSheet;
import editor.Tiles.TileSet;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PTree {
    public PTree(String pName) {
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(pName);
        DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);

        objects = new DefaultMutableTreeNode("Objects", true);
        spriteSheets = new DefaultMutableTreeNode("SpriteSheets", true);
        tileSets = new DefaultMutableTreeNode("TileSets", true);
        levels = new DefaultMutableTreeNode("Levels", true);

        rootNode.add(objects);
        rootNode.add(spriteSheets);
        rootNode.add(tileSets);
        rootNode.add(levels);
        rootNode.add(spriteSheets);


        myTree = new JTree(treeModel);
        myTree.setEditable(true);
        myTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        myTree.setShowsRootHandles(true);
        myTree.addMouseListener(new doubleClick());

        myTree.setPreferredSize(new Dimension(0,250));
    }

    public void addNewLevel(Level level, int index){
        levels.add(new DefaultMutableTreeNode(new pair(index, level.getName())));
        myTree.updateUI();
    }

    public void addNewObject(ObjectIntel obj){
        objects.add(new DefaultMutableTreeNode(obj));
        myTree.updateUI();
    }

    public void addNewTileSet(TileSet ts, int index){
        tileSets.add(new DefaultMutableTreeNode(new pair(index, ts.getName())));
        myTree.updateUI();
    }

    public void addNewSpritesSheet(SpriteSheet sheet, int index){
        spriteSheets.add(new DefaultMutableTreeNode(new pair(index, sheet.toString())));
        myTree.updateUI();
    }

    private class pair{
        public pair(int index, String name) {
            this.index = index;
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }

        public int index;
        public String name;
    }

    public void reload(){
        objects.removeAllChildren();
        tileSets.removeAllChildren();
        levels.removeAllChildren();
        myTree.treeDidChange();
        myTree.updateUI();

        for (int i = 0; i < Editor.world.levelList.size(); i++){
            levels.add(new DefaultMutableTreeNode(new pair(i,
                    Editor.world.levelList.get(i).getName())));
        }

        for (int i = 0; i < Editor.world.tileSetList.size(); i++) {
            tileSets.add(new DefaultMutableTreeNode(new pair(i,
                    Editor.world.tileSetList.get(i).getName())));
        }

        for (ObjectIntel obj: Editor.world.worldObjects.getObjs()){
            objects.add(new DefaultMutableTreeNode(obj));
        }

        List<SpriteSheet> sheetList = Editor.world.worldObjects.getSpriteSheetList();
        for (int i = 0; i < Editor.world.worldObjects.getSpriteSheetList().size(); i++) {
            spriteSheets.add(new DefaultMutableTreeNode(new pair(i, sheetList.get(i).toString())));
        }

        myTree.getModel();
        myTree.updateUI();
    }

    private class doubleClick extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                        myTree.getLastSelectedPathComponent();
                if (node == null) return;
                if (!node.isLeaf())
                    return;
                if (node.getParent() == objects)
                    return;
                Object tmp = node.getUserObject();
                if (node.getParent() == tileSets){
                    if (tmp instanceof pair)
                        Editor.editFrame.tileSetFrame.display.changeTileSet(((pair) tmp).index);
                    return;
                }
                if (node.getParent() == levels){
                    if (tmp instanceof pair)
                        Editor.mainFrame.setLevel(((pair) tmp).index);
                }
            }
        }
    }


    private DefaultMutableTreeNode objects;
    private DefaultMutableTreeNode tileSets;
    private DefaultMutableTreeNode levels;
    private DefaultMutableTreeNode spriteSheets;

    public JTree myTree;
}