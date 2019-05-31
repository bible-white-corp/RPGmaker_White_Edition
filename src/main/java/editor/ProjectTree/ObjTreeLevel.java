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

    public void removeAllPlayers() {
        player.removeAllChildren();

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

                if (parent == tp) {
                    if (tmp instanceof pair)
                        customizeTP((pair) tmp);
                    return;
                }
        }
    }

    private void customizeTP(pair p){
        Object[] possibilites = {"Link", "Rename", "Delete"};
        ObjectInstantiation cur = Editor.world.worldObjects.getInWorldObj().get(p.index);
        ObjectInstantiation linked = cur.getSibling();
        String s = (String) JOptionPane.showInputDialog(Editor.editFrame,

                (linked == null ? "This teleporter is not linked yet!" :
                        "This teleporter is linked to " + linked.toString()) + "\nWhat do you want to do with " + p.name + "?",
                "Customize teleporter",
                JOptionPane.INFORMATION_MESSAGE,
                null,
                possibilites, "Rename");
        if (s == null)
            return;
        if (s == "Link"){
            Object[] choice = Editor.world.worldObjects.getInWorldObj().stream()
                    .filter(w -> w != null)
                    .filter(w -> w.getType() == objType.TELEPORTER)
                    .toArray();
            linked = (ObjectInstantiation) JOptionPane.showInputDialog(Editor.editFrame,
                    "To which teleporter would you link to link " + p.name + "?", "Link teleporter",
                    JOptionPane.INFORMATION_MESSAGE, null, choice, choice[0]);
            if (linked == null)
                return;
            cur.setSibling(linked.getIndex(), linked.getLevelIndex());
            JOptionPane.showMessageDialog(Editor.editFrame, "Teleporter linked!",
                    "SUCCESS", JOptionPane.INFORMATION_MESSAGE);

        } else if (s == "Delete") {
            tp.remove((MutableTreeNode) myTree.getLastSelectedPathComponent());
            myTree.updateUI();
            return;
        } else if (s == "Rename") {
            s = (String) JOptionPane.showInputDialog(Editor.mainFrame,
                    "New name:", "Rename", JOptionPane.INFORMATION_MESSAGE);
            if (s == null)
                return;
            p.name = s;
            Editor.world.worldObjects.getSpriteList().get(p.index).setName(s);
            myTree.updateUI();
            return;
        }
        }
    }

    private DefaultMutableTreeNode item;
    private DefaultMutableTreeNode npc;
    private DefaultMutableTreeNode tp;
    private DefaultMutableTreeNode player;

    public JTree myTree;
}