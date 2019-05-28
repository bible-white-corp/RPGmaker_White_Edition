package editor.Forms;

import editor.Object.ObjectInstantiation;

import javax.swing.*;
import java.awt.*;

public class StoryFrame extends JPanel {

    private JList<ObjectInstantiation> list = new JList<>();
    private JTextField name = new JTextField();
    private JTextField dialog = new JTextField();

    private JComboBox path = new JComboBox(Path.getPath());

    public StoryFrame() {

        setLayout(new BorderLayout());

        add(list, BorderLayout.WEST);
        list.setPreferredSize(new Dimension(250,0));

        JPanel con = new JPanel();
        con.setLayout(new GridBagLayout());
        add(con, BorderLayout.CENTER);

        con.add(new Label("Name"), EditFrame.get_c(0,0,0,0));
        con.add(name, EditFrame.get_c(1,0,0,0));
        con.add(new Label("Dialog"), EditFrame.get_c(0,1,0,0));
        con.add(dialog, EditFrame.get_c(1,1,0,0));
        con.add(new Label("Path"), EditFrame.get_c(0,2,0,0));
        con.add(path, EditFrame.get_c(1,2,0,0));
        con.add(new JPanel(), EditFrame.get_c(2,3,1,1));
    }

}
