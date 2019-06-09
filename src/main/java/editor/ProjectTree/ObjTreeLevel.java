package editor.ProjectTree;

import editor.Editor;
import editor.Object.ObjectInstantiation;
import editor.Object.PathFinding.MoveType;
import editor.Tools.Brushes.ObjectMove;
import editor.Tools.Brushes.PathEdit;

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

            if (tmp == null)
                continue;

            if (tmp.getType() == objType.ITEM) {
                node = item;
            } else if (tmp.getType() == objType.NPC) {
                node = npc;
            } else if (tmp.getType() == objType.TELEPORTER) {
                node = tp;
            } else if (tmp.getType() == objType.PLAYER) {
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
                } else if (parent == npc) {
                    if (tmp instanceof pair)
                        customizeNPC((pair) tmp);
                    return;
                } else if (parent == item) {
                    if (tmp instanceof pair)
                        customizeItem((pair) tmp);
                    return;
                } else if (parent == player) {
                    if (tmp instanceof pair)
                        customizePlayer((pair) tmp);
                    return;
                }
            }
        }

    }

    private void customizeTP(pair p) {
        Object[] possibilites = {"Link", "Rename", "Delete", "Re-Locate"};
        ObjectInstantiation cur = Editor.world.worldObjects.getInWorldObj().get(p.index);
        ObjectInstantiation linked = cur.getSibling();

        String s = askChoice(possibilites, (linked == null ? "This teleporter is not linked yet!" :
                "This teleporter is linked to " + linked.toString())
                + "\nWhat do you want to do with " + p.name + "?", "Customize teleporter");

        if (s == null)
            return;
        if (s == "Link") {
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
            Object[] list = Editor.world.worldObjects.getInWorldObj().stream()
                    .filter(w -> w != null)
                    .filter(w -> w.getType() == objType.TELEPORTER)
                    .filter(w -> w.getSibling() == cur).toArray();
            for (Object o : list)
                ((ObjectInstantiation) o).setSibling(-1, -1);
            deleteFromMemory(p, tp);
            return;
        } else if (s == "Rename") {
            renameInMemory(p);
        } else if (s == "Re-Locate")
            relocate(cur);
    }

    private void customizeNPC(pair p) {
        ObjectInstantiation cur = Editor.world.worldObjects.getInWorldObj().get(p.index);
        ObjectInstantiation linked = cur.getSibling();
        Object[] possibilities = {"Moves", "Rename", "Customize dialog", "Delete","Re-Locate"};
        Object[] movePossibilities = MoveType.values();

        String s = askChoice(possibilities, "What do you want to do with the NPC " + p.name + "?",
        "Customize NPC");

        if (s == null)
            return;
        if (s == "Moves") {
            MoveType type = (MoveType) JOptionPane.showInputDialog(Editor.editFrame,
                    "Which is the type of wished movements?", "Movements",
                    JOptionPane.INFORMATION_MESSAGE, null, movePossibilities, movePossibilities[0]);
            if (type == null)
                return;
            else
                cur.getPath().setType(type);
            if (type.needMov()){
                JOptionPane.showMessageDialog(Editor.editFrame,
                        "Please, click on the destination", "Movements",
                        JOptionPane.INFORMATION_MESSAGE);
                Editor.setBrush(new PathEdit(Editor.getBrush(), cur));
            }
        } else if (s == "Delete") {
            deleteFromMemory(p, npc);
            return;
        } else if (s == "Customize dialog") {
            customizeDialog(p);
        }
        else if (s == "Rename") {
            renameInMemory(p);
        } else if (s == "Re-Locate")
            relocate(cur);
    }

    private void customizeDialog(pair p) {
        String s = (String) JOptionPane.showInputDialog(Editor.editFrame,
                "Current dialog: " + Editor.world.worldObjects.getInWorldObj().get(p.index).getDialog()+
                        "\nNew dialog: ", "Change dialog", JOptionPane.INFORMATION_MESSAGE);
        if (s == null)
            return;
        Editor.world.worldObjects.getInWorldObj().get(p.index).setDialog(s);
        return;
    }

    private void customizeItem(pair p) {
        Object[] possibilities = {"Rename", "Delete", "Re-Locate"};

        String s = askChoice(possibilities, "What do you want to do with the item " + p.name + "?",
                "Customize item");

        if (s == null)
            return;
        if (s == "Delete") {
            deleteFromMemory(p, item);
            return;
        } else if (s == "Rename") {
            renameInMemory(p);
        } else if (s == "Re-Locate")
            relocate(Editor.world.worldObjects.getInWorldObj().get(p.index));
    }

    private void customizePlayer(pair p) {
        Object[] possibilities = {"Rename", "Delete", "Re-Locate"};

        String s = askChoice(possibilities, "What do you want to do with the player " + p.name + "?",
                "Customize player");

        if (s == null)
            return;
        if (s == "Delete") {
            Editor.world.worldObjects.setPlayer(null);
            deleteFromMemory(p, player);
            return;
        } else if (s == "Rename") {
            renameInMemory(p);
        } else if (s == "Re-Locate")
            relocate(Editor.world.worldObjects.getInWorldObj().get(p.index));
    }

    private void relocate(ObjectInstantiation obj){
        JOptionPane.showMessageDialog(Editor.editFrame, "Please click a place to relocate you object",
                "Re-Locate", JOptionPane.INFORMATION_MESSAGE);
        Editor.setBrush(new ObjectMove(Editor.getBrush(), obj));
    }

    private String askChoice(Object[] possibilities, String message, String title){
        return (String) JOptionPane.showInputDialog(Editor.editFrame,
                message,
                title,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                possibilities, possibilities[0]);
    }


    private void deleteFromMemory(pair p, DefaultMutableTreeNode node){
        Editor.world.worldObjects.getInWorldObj().set(p.index, null);
        node.remove((MutableTreeNode) myTree.getLastSelectedPathComponent());
        Editor.mainFrame.repaint();
        myTree.updateUI();
    }

    private void renameInMemory(pair p){
        String s = (String) JOptionPane.showInputDialog(Editor.editFrame,
                "New name:", "Rename", JOptionPane.INFORMATION_MESSAGE);
        if (s == null)
            return;
        p.name = s;
        Editor.world.worldObjects.getInWorldObj().get(p.index).setName(s);
        myTree.updateUI();
        return;
    }


    private DefaultMutableTreeNode item;
    private DefaultMutableTreeNode npc;
    private DefaultMutableTreeNode tp;
    private DefaultMutableTreeNode player;

    public JTree myTree;
}