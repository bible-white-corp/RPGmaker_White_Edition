package editor.Forms;

import editor.Editor;
import editor.Tools.Brushes.*;

import javax.swing.*;
import java.awt.*;

public class ToolsFrame extends JToolBar {

    public ToolsFrame()
    {

        JButton pencil = new JButton(getIcon("src/main/resources/images/toolbar/pencil.png"));
        add(pencil);
        pencil.addActionListener(actionEvent -> Editor.setBrush(new Pencil()));

        JButton eraser = new JButton(getIcon("src/main/resources/images/toolbar/eraser.png"));
        add(eraser);
        eraser.addActionListener(actionEvent -> Editor.setBrush(new Eraser()));

        JButton select = new JButton(getIcon("src/main/resources/images/toolbar/select.png"));
        add(select);
        select.addActionListener(actionEvent -> Editor.setBrush(new SelectBrush()));

        JButton paintbrush = new JButton(getIcon("src/main/resources/images/toolbar/paint_brush.png"));
        add(paintbrush);
        paintbrush.addActionListener(actionEvent -> Editor.setBrush(new PaintBrush()));

        JButton bucketfill = new JButton(getIcon("src/main/resources/images/toolbar/bucket.png"));
        add(bucketfill);
        bucketfill.addActionListener(actionEvent -> Editor.setBrush(new BucketFill()));

        JButton linebrush = new JButton(getIcon("src/main/resources/images/toolbar/line_brush.png"));
        add(linebrush);
        linebrush.addActionListener(actionEvent -> Editor.setBrush(new LineBrush()));

        add(new JLabel("Brush Size : "));

        JSpinner brushsize = new JSpinner(new SpinnerNumberModel(Editor.getBrush_size(),1,6,1));
        brushsize.addChangeListener(changeEvent -> Editor.setBrush_size((Integer) brushsize.getValue()));
        brushsize.setMaximumSize(new Dimension(50,25));
        add(brushsize);

        add(new JLabel("Current Layer : "));

        JSpinner layer_select = new JSpinner(new SpinnerNumberModel(Editor.getLayer_index(),0,9,1));
        layer_select.addChangeListener(changeEvent -> Editor.setLayer_index((Integer) layer_select.getValue()));
        layer_select.setMaximumSize(new Dimension(50,25));
        add(layer_select);

        add(new JLabel("Show Grid "));
        JCheckBox checkBox = new JCheckBox();
        checkBox.setSelected(true);
        add(checkBox);
        checkBox.addChangeListener(changeEvent -> Editor.setShow_grid(checkBox.isSelected()));

    }

    private static ImageIcon getIcon(String str)
    {
        return new ImageIcon(new ImageIcon(str).getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT));
    }
}
