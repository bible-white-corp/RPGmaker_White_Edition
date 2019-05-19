package editor.Forms;

import editor.Editor;
import editor.Tools.Brushes.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolsFrame extends JToolBar {

    public ToolsFrame()
    {
        ImageIcon icon = new ImageIcon("src/main/resources/images/icon_sav.jpg");

        JButton pencil = new JButton(icon);
        add(pencil);
        pencil.addActionListener(actionEvent -> Editor.setBrush(new Pencil()));

        JButton eraser = new JButton(icon);
        add(eraser);
        eraser.addActionListener(actionEvent -> Editor.setBrush(new Eraser()));

        JButton select = new JButton(icon);
        add(select);
        select.addActionListener(actionEvent -> Editor.setBrush(new SelectBrush()));

        JButton paintbrush = new JButton(icon);
        add(paintbrush);
        paintbrush.addActionListener(actionEvent -> Editor.setBrush(new PaintBrush()));

        JButton bucketfill = new JButton(icon);
        add(bucketfill);
        bucketfill.addActionListener(actionEvent -> Editor.setBrush(new BucketFill()));

        JButton linebrush = new JButton(icon);
        add(linebrush);
        linebrush.addActionListener(actionEvent -> Editor.setBrush(new LineBrush()));
    }
}
