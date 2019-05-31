package editor.ProjectTree;

import editor.Editor;
import editor.Object.*;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ObjTreeLevel {
    public ObjTreeLevel() {
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("World objects");
        DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);

        item = new DefaultMutableTreeNode("Items", true);
        npc = new DefaultMutableTreeNode("NPCs", true);
        tp = new DefaultMutableTreeNode("Teleporters", true);
        player = new DefaultMutableTreeNode("Player", true);

        rootNode.add(item);
        rootNode.add(npc);
        rootNode.add(tp);
        rootNode.add(player);

        myTree = new JTree(treeModel);
        myTree.setEditable(true);
        myTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        myTree.setShowsRootHandles(true);
        myTree.addMouseListener(new doubleClick());

        myTree.setPreferredSize(new Dimension(0, 400));
    }

    public void addObj(ObjectInstantiation instantiation, int index, objType type) {
        DefaultMutableTreeNode node;
        if (type == objType.ITEM)
            node = item;
        else if (type == objType.NPC)
            node = npc;
        else if (type == objType.TELEPORTER)
            node = tp;
        else
            node = player;

        node.add(new DefaultMutableTreeNode(new pair(index, instantiation.toString())));
        myTree.updateUI();
    }


    public void reload() {
        item.removeAllChildren();
        npc.removeAllChildren();
        player.removeAllChildren();
        tp.removeAllChildren();

        List<ObjectInstantiation> objectInstantiations = Editor.world.worldObjects.getInWorldObj();
        ObjectInstantiation tmp;
        DefaultMutableTreeNode node;

        for (int i = 0; i < objectInstantiations.size(); i++) {
            tmp = objectInstantiations.get(i);
            if (tmp.getType() == objType.ITEM){
                node = item;
            } else if (tmp.getType() == objType.NPC){
                node = npc;
            } else if (tmp.getType() == objType.TELEPORTER){
                node = tp;
            } else if (tmp.getType() == objType.PLAYER){
                node = player;
            } else
                throw new RuntimeException();
            node.add(new DefaultMutableTreeNode(new pair(i, tmp.toString())));
        }

        myTree.treeDidChange();
        myTree.updateUI();
    }

    private class doubleClick extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
        }
    }

    private DefaultMutableTreeNode item;
    private DefaultMutableTreeNode npc;
    private DefaultMutableTreeNode tp;
    private DefaultMutableTreeNode player;

    public JTree myTree;
}