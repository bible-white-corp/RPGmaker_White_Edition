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
        else if (resp.equals(possibilities[1])) {
            obj = new ObjectInstantiation(objIntelIndex, name, objType.NPC);
            int rdm = JOptionPane.showConfirmDialog(Editor.editFrame, "Does the NPC moves?",
                    "Instantiation", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (rdm == JOptionPane.CANCEL_OPTION){
                return;
            } else if (rdm == JOptionPane.YES_OPTION)
                obj.setRandomMove(true);
        }
        else if (resp.equals(possibilities[2]))
            obj = new ObjectInstantiation(objIntelIndex, name, objType.TELEPORTER);
        else if (resp.equals(possibilities[3]))
            obj = new ObjectInstantiation(objIntelIndex, name, objType.PLAYER);
        else {
            JOptionPane.showMessageDialog(Editor.editFrame, "Unexpected object type",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Editor.setBrush(new ObjectEdit(Editor.getBrush(), obj));
    }
}
