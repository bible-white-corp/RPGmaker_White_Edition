package editor.Object.actions;

import editor.Editor;
import editor.Object.GameObjects;
import editor.Object.Sprite;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Vector;

public class createAnimationAction extends AbstractAction {

    public createAnimationAction() {
        super("New animation");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Editor.editFrame.tabbedPane.setSelectedIndex(2);
        List<Sprite> spriteList = Editor.world.worldObjects.getSpriteList();
        Object[] elements = spriteList.toArray();
        if (elements.length == 0){
            JOptionPane.showMessageDialog(Editor.editFrame, "No sprite available!",
                    "New animation ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String animName = (String) JOptionPane.showInputDialog(Editor.editFrame,
                "Name the new animation", "New animation",
                JOptionPane.INFORMATION_MESSAGE);

        if (animName == null){
            return;
        }

        int res = JOptionPane.NO_OPTION;
        List<Integer> sprites = new Vector<>();
        List<String> names = new Vector<>();
        do {
            Sprite s = (Sprite) JOptionPane.showInputDialog(Editor.editFrame,
                    "Add a sprite to the animation",
                    "New animation", JOptionPane.INFORMATION_MESSAGE,
                    null, elements, elements[0]);
            if (s == null)
                return;

            for (int i = 0; i < spriteList.size(); i++) {
                if (spriteList.get(i) == s) {
                    sprites.add(i);
                    names.add(s.toString());
                    break;
                }
            }

            res = JOptionPane.showConfirmDialog(Editor.editFrame,
                    names.toString() + "\nAdd another animation ?");
        } while (res == JOptionPane.YES_OPTION);

        if (res == JOptionPane.NO_OPTION){
            Editor.world.worldObjects.addAnimation(animName, sprites);
            JOptionPane.showMessageDialog(Editor.editFrame,
                    "Animation " + animName + " created", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(Editor.editFrame,
                    "Animation creation aborted", "Failure",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
}
