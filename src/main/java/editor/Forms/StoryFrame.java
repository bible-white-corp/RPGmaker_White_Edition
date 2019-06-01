package editor.Forms;

import editor.Object.GameObjects;
import editor.Object.ObjectInstantiation;
import editor.Tools.Selection;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class StoryFrame extends JPanel {

    private JList<ObjectInstantiation> list = new JList<ObjectInstantiation>((Vector<? extends ObjectInstantiation>) GameObjects.instantiations);
    private JTextField name = new JTextField();
    private JTextField dialog = new JTextField();
    private JComboBox path = new JComboBox(Path.getPath());

    private ObjectInstantiation selectObj;

    public void printList() {
        System.out.println("list: ");
        for (int i = 0; i < list.getModel().getSize(); i++) {
            System.out.println(list.getModel().getElementAt(i).getName());
        }
    }
    
    public StoryFrame() {
        
        printList();
        setLayout(new BorderLayout());

        add(list, BorderLayout.WEST);

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setPreferredSize(new Dimension(250, 0));

        list.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent event) {

                if (event.getValueIsAdjusting())
                    return;

                setSelectObj(list.getSelectedValue());
                setName(selectObj.getName());
                setDialog(selectObj.getDialog());

                JPanel con = new JPanel();
                con.setLayout(new GridBagLayout());
                add(con, BorderLayout.CENTER);

                con.add(new Label("Name"), EditFrame.get_c(0, 0, 0, 0));
                con.add(name, EditFrame.get_c(1, 0, 0, 0));
                name.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        selectObj.setName(name.getText());
                    }
                });

                con.add(new Label("Dialog"), EditFrame.get_c(0, 1, 0, 0));
                con.add(dialog, EditFrame.get_c(1, 1, 0, 0));
                dialog.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        selectObj.setDialog(dialog.getText());
                    }
                });

                con.add(new Label("Path"), EditFrame.get_c(0, 2, 0, 0));
                con.add(path, EditFrame.get_c(1, 2, 0, 0));
                path.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        selectObj.setPath((Path) path.getSelectedItem());
                    }
                });

                con.add(new JPanel(), EditFrame.get_c(2, 3, 1, 1));
                add(con, BorderLayout.CENTER);

            }
        });
    }

    public void setName(String name) {
        if (name == null) {
            System.out.println("null name");
            return;
        }
        this.name = new JTextField(name);
    }

    public void setDialog(String dialog) {
        if (dialog == null) {
            System.out.println("null dialog");
            return;
        }
        this.dialog = new JTextField(dialog);
    }

    public void setSelectObj(Object selectElt) {
        if (selectElt == null) {
            System.out.println("null selected obj");
            return;
        }
        this.selectObj = (ObjectInstantiation) selectElt;
    }
}
