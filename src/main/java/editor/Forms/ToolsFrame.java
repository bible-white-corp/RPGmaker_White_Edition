package editor.Forms;

import editor.Editor;
import editor.Tools.Brushes.*;

import javax.swing.*;

public class ToolsFrame extends JToolBar {

    public ToolsFrame()
    {

        ImageIcon icon = new ImageIcon("src/main/resources/images/toolbar/pencil.png");
        JButton pencil = new JButton(icon);
        add(pencil);
        pencil.addActionListener(actionEvent -> Editor.setBrush(new Pencil()));

        icon = new ImageIcon("src/main/resources/images/toolbar/eraser.png");
        JButton eraser = new JButton(icon);
        add(eraser);
        eraser.addActionListener(actionEvent -> Editor.setBrush(new Eraser()));

        icon = new ImageIcon("src/main/resources/images/toolbar/select.png");
        JButton select = new JButton(icon);
        add(select);
        select.addActionListener(actionEvent -> Editor.setBrush(new SelectBrush()));

        icon = new ImageIcon("src/main/resources/images/toolbar/paint_brush.png");
        JButton paintbrush = new JButton(icon);
        add(paintbrush);
        paintbrush.addActionListener(actionEvent -> Editor.setBrush(new PaintBrush()));

        icon = new ImageIcon("src/main/resources/images/toolbar/bucket.png");
        JButton bucketfill = new JButton(icon);
        add(bucketfill);
        bucketfill.addActionListener(actionEvent -> Editor.setBrush(new BucketFill()));

        icon = new ImageIcon("src/main/resources/images/toolbar/line_brush.png");
        JButton linebrush = new JButton(icon);
        add(linebrush);
        linebrush.addActionListener(actionEvent -> Editor.setBrush(new LineBrush()));
    }
}
