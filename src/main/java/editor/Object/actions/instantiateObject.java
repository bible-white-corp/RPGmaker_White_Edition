package editor.Object.actions;

import editor.Editor;
import editor.Object.Categories.Item;
import editor.Object.Categories.NPC;
import editor.Object.Categories.Player;
import editor.Object.Categories.Teleporter;
import editor.Object.ObjectInstantiation;
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
            obj = new Item(objIntelIndex, name);
        else if (resp.equals(possibilities[1]))
            obj = new NPC(objIntelIndex, name);
        else if (resp.equals(possibilities[2]))
            obj = new Teleporter(objIntelIndex, name);
        else if (resp.equals(possibilities[3]))
            obj = new Player(objIntelIndex, name);
        else {
            JOptionPane.showMessageDialog(Editor.editFrame, "Unexpected object type",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Editor.setBrush(new ObjectEdit(Editor.getBrush(), obj));
    }
}
