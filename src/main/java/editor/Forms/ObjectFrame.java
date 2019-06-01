package editor.Forms;

import editor.Editor;

import javax.swing.*;
import java.awt.*;

import static editor.Forms.EditFrame.get_c;

public class ObjectFrame extends JPanel {

    public ObjectFrame() {
        setBackground(Color.BLACK);
        setLayout(new GridBagLayout());
        add(new JScrollPane(Editor.world.objTree.myTree),get_c(0,1,1,0.5));
    }
}
