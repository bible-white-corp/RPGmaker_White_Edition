package editor.ProjectTree;

import editor.Editor;
import editor.Maps.Level;
import editor.Object.GameObjects;
import editor.Object.ObjectIntel;
import editor.Tiles.TileSet;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.util.Map;

public class PTree {
    public PTree(String pName) {
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(pName);
        DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);

        objects = new DefaultMutableTreeNode("Objects", true);
        tileSets = new DefaultMutableTreeNode("TileSets", true);
        levels = new DefaultMutableTreeNode("Levels", true);

        rootNode.add(objects);
        rootNode.add(tileSets);
        rootNode.add(levels);

        myTree = new JTree(treeModel);
        myTree.setEditable(true);
        myTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        myTree.setShowsRootHandles(true);

        myTree.setPreferredSize(new Dimension(0,250));
    }

    public void addNewLevel(Level level){
        levels.add(new DefaultMutableTreeNode(level));
        myTree.updateUI();
    }
    public void addNewObject(ObjectIntel obj){
        objects.add(new DefaultMutableTreeNode(obj));
        myTree.updateUI();
    }
    public void addNewTileSet(TileSet ts){
        tileSets.add(new DefaultMutableTreeNode(ts));
        myTree.updateUI();
    }


    private DefaultMutableTreeNode objects;
    private DefaultMutableTreeNode tileSets;
    private DefaultMutableTreeNode levels;
    public JTree myTree;
}
