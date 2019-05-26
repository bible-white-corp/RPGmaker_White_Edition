package editor.Object.actions;

import editor.Editor;
import editor.Object.Animation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Vector;

public class createObjectAction extends AbstractAction {
    public createObjectAction() {
        super("Create object");
    }

    private List<Animation> animations;
    private Object[] possibilities;
    private List<Integer> animIndex;
    List<Animation> animationList;

    private boolean ask(String request){
        Animation anim = (Animation) JOptionPane.showInputDialog(Editor.editFrame,
                "Select the " + request + " animation", "New object",
                JOptionPane.INFORMATION_MESSAGE, null, possibilities, possibilities[0]);
        if (anim == null)
            return true;
        for (int i = 0; i < animations.size(); i++) {
            if (anim == animations.get(i)) {
                animIndex.add(i);
                animationList.add(anim);
                break;
            }
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Editor.editFrame.tabbedPane.setSelectedIndex(2);

        if (Editor.world.worldObjects.getAnimations().size() == 0){
            JOptionPane.showMessageDialog(Editor.editFrame, "No animation available",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String objName = (String) JOptionPane.showInputDialog(Editor.editFrame,
                "Name the object", "New object", JOptionPane.INFORMATION_MESSAGE);
        if (objName == null)
            return;

        boolean is_static = false;
        int res = JOptionPane.showConfirmDialog(Editor.editFrame, "Is the object an idle item? ",
                "New object", JOptionPane.YES_NO_CANCEL_OPTION);
        if (res == JOptionPane.YES_OPTION)
            is_static = true;
        else if (res == JOptionPane.NO_OPTION)
            is_static=  false;
        else
            return;

        animations = Editor.world.worldObjects.getAnimations();
        possibilities = animations.toArray();
        animIndex = new Vector<>();
        animationList = new Vector<>();

        if (ask("IDLE"))
            return;

        if (!is_static){
            if (ask("FORWARD") || ask("BACKWARD")
                    || ask("LEFT") || ask("RIGHT"))
                return;
        }

        res = JOptionPane.showConfirmDialog(Editor.editFrame,
                animationList.toString() + "\nThe object " + objName + " will be created",
                "New object", JOptionPane.YES_OPTION);
        if (res == JOptionPane.YES_OPTION){
            Editor.world.worldObjects.addObject(objName, is_static, animIndex);
            JOptionPane.showMessageDialog(Editor.editFrame,
                    "Object " + objName + " has been created", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(Editor.editFrame,
                    "Creation cancelled", "Cancel",
                    JOptionPane.WARNING_MESSAGE);
    }
}
