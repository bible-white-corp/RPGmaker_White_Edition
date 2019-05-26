package editor.ProjectTree;

import editor.Editor;
import editor.Maps.Level;
import editor.Object.*;
import editor.Tiles.TileSet;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class PTree {
    public PTree(String pName) {
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(pName);
        DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);

        objects = new DefaultMutableTreeNode("Objects Pool", true);
        spriteSheets = new DefaultMutableTreeNode("SpriteSheets", true);
        tileSets = new DefaultMutableTreeNode("TileSets", true);
        levels = new DefaultMutableTreeNode("Levels", true);
        sprites = new DefaultMutableTreeNode("Sprites", true);
        animations = new DefaultMutableTreeNode("Animations", true);

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

        myTree.setPreferredSize(new Dimension(0, 250));
    }

    public void addNewLevel(Level level, int index) {
        levels.add(new DefaultMutableTreeNode(new pair(index, level.getName())));
        myTree.updateUI();
    }

    public void addNewObject(ObjectIntel obj, int index) {
        objects.add(new DefaultMutableTreeNode(new pair(index, obj.getName())));
        myTree.updateUI();
    }

    public void addNewTileSet(TileSet ts, int index) {
        tileSets.add(new DefaultMutableTreeNode(new pair(index, ts.getName())));
        myTree.updateUI();
    }

    public void addNewSpritesSheet(SpriteSheet sheet, int index) {
        spriteSheets.add(new DefaultMutableTreeNode(new pair(index, sheet.toString())));
        myTree.updateUI();
    }

    public void addNewSprite(Sprite sprite, int index) {
        sprites.add(new DefaultMutableTreeNode(new pair(index, sprite.toString())));
        myTree.updateUI();
    }

    public void addNewAnimation(Animation anim, int index) {
        animations.add(new DefaultMutableTreeNode(new pair(index, anim.toString())));
        myTree.updateUI();
    }

    public void reload() {
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
            if (e.getClickCount() == 2) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                        myTree.getLastSelectedPathComponent();
                if (node == null) return;
                if (!node.isLeaf())
                    return;
                TreeNode parent = node.getParent();
                Object tmp = node.getUserObject();

                if (parent == objects)
                    return;
                else if (parent == tileSets) {
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
                        Editor.editFrame.tabbedPane.setSelectedIndex(2);
                    }
                } else if (parent == sprites) {
                    if (tmp instanceof pair) {
                        customizeSprite((pair) tmp);
                    }
                } else if (parent == animations)
                    if (tmp instanceof pair) {
                        customizeAnimation((pair) tmp);
                    }
            }
        }
    }

    private void customizeSprite(pair p) {
        Object[] possibilites = {"Rename", "Delete"};
        String s = (String) JOptionPane.showInputDialog(Editor.mainFrame,
                "What do you want to do with " + p.name,
                "Customize sprite",
                JOptionPane.INFORMATION_MESSAGE,
                new ImageIcon(Editor.world.worldObjects.getSpriteList().get(p.index).getImage()),
                possibilites, "Rename");
        if (s == null)
            return;
        if (s == "Delete") {
            sprites.remove((MutableTreeNode) myTree.getLastSelectedPathComponent());
            myTree.updateUI();
            return;
        }
        if (s == "Rename") {
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

    private void customizeAnimation(pair p) {
        Object[] possibilites = {"Rename", "Delete"};
        String s = (String) JOptionPane.showInputDialog(Editor.mainFrame,
                Editor.world.worldObjects.getAnimations().get(p.index).getNames().toString()
                        + "\nWhat do you want to do with " + p.name,
                "Customize sprite",
                JOptionPane.INFORMATION_MESSAGE,
                null,
                possibilites, "Rename");
        if (s == null)
            return;
        if (s == "Delete") {
            animations.remove((MutableTreeNode) myTree.getLastSelectedPathComponent());
            myTree.updateUI();
            return;
        }
        if (s == "Rename") {
            s = (String) JOptionPane.showInputDialog(Editor.mainFrame,
                    "New name:", "Rename", JOptionPane.INFORMATION_MESSAGE);
            if (s == null)
                return;
            p.name = s;
            Editor.world.worldObjects.getAnimations().get(p.index).setName(s);
            myTree.updateUI();
            return;
        }
    }

    private DefaultMutableTreeNode objects;
    private DefaultMutableTreeNode tileSets;
    private DefaultMutableTreeNode levels;
    private DefaultMutableTreeNode spriteSheets;
    private DefaultMutableTreeNode sprites;
    private DefaultMutableTreeNode animations;

    public JTree myTree;
}