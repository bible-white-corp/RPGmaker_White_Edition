package editor.Object.actions;

import editor.Editor;
import editor.Object.ObjectInstantiation;
import editor.ProjectTree.objType;
import editor.Tools.Brushes.ObjectEdit;

import javax.swing.*;

public class instantiateObject {
    static String[] possibilities = {"Item", "NPC", "Teleporter", "Player"};

    public static void click(int objIntelIndex){
        String resp = (String)JOptionPane.showInputDialog(Editor.editFrame,
                "Select the type of the new object", "Instantiation",
                JOptionPane.INFORMATION_MESSAGE, null, possibilities, possibilities[0]);
        if (resp == null)
            return;

        String name = JOptionPane.showInputDialog(Editor.editFrame, "Name the new object",
                "Instantiation", JOptionPane.INFORMATION_MESSAGE);

        if (name == null)
            return;

        ObjectInstantiation obj;
        if (resp.equals(possibilities[0]))
            obj = new ObjectInstantiation(objIntelIndex, name, objType.ITEM);
        else if (resp.equals(possibilities[1]))
            obj = new ObjectInstantiation(objIntelIndex, name, objType.NPC);
        else if (resp.equals(possibilities[2]))
            obj = new ObjectInstantiation(objIntelIndex, name, objType.TELEPORTER);
        else if (resp.equals(possibilities[3])) {
            obj = new ObjectInstantiation(objIntelIndex, name, objType.PLAYER);
            if (Editor.world.worldObjects.getPlayer() != null) {
                if (JOptionPane.showConfirmDialog(Editor.editFrame,
                        "A player already exists, would you like to overwrite it?", "Instantiation",
                        JOptionPane.YES_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.CANCEL_OPTION)
                    return;
            }
        }
        else {
            JOptionPane.showMessageDialog(Editor.editFrame, "Unexpected object type",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Editor.setBrush(new ObjectEdit(Editor.getBrush(), obj));
    }
}
