package editor.Forms;

import editor.Editor;
import editor.Tools.Brushes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;

public class ToolsFrame extends JToolBar {

    public ToolsFrame()
    {

        ToolButton pencil = new ToolButton(getIcon("src/main/resources/images/toolbar/pencil.png"));
        add(pencil);
        pencil.addActionListener(actionEvent -> Editor.setBrush(new Pencil()));

        ToolButton eraser = new ToolButton(getIcon("src/main/resources/images/toolbar/eraser.png"));
        add(eraser);
        eraser.addActionListener(actionEvent -> Editor.setBrush(new Eraser()));

        ToolButton select = new ToolButton(getIcon("src/main/resources/images/toolbar/select.png"));
        add(select);
        select.addActionListener(actionEvent -> Editor.setBrush(new SelectBrush()));

        ToolButton paintbrush = new ToolButton(getIcon("src/main/resources/images/toolbar/paint_brush.png"));
        add(paintbrush);
        paintbrush.addActionListener(actionEvent -> Editor.setBrush(new PaintBrush()));

        ToolButton bucketfill = new ToolButton(getIcon("src/main/resources/images/toolbar/bucket.png"));
        add(bucketfill);
        bucketfill.addActionListener(actionEvent -> Editor.setBrush(new BucketFill()));

        ToolButton linebrush = new ToolButton(getIcon("src/main/resources/images/toolbar/line_brush.png"));
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
        return new ImageIcon(new ImageIcon(str).getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT));
    }

    public class ToolButton extends JButton {

        private static final long serialVersionUID = 1L;

        public ToolButton(ImageIcon icon) {
            setForeground(Color.WHITE);

            setOpaque(false);
            setContentAreaFilled(false); // On met à false pour empêcher le composant de peindre l'intérieur du ToolButton.
            setBorderPainted(false); // De même, on ne veut pas afficher les bordures.

            setHorizontalAlignment(SwingConstants.CENTER);
            setHorizontalTextPosition(SwingConstants.CENTER);

            setIcon(icon);

            Icon hover_icon = getHoverIcon(icon.getImage());

            addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {

                    setIcon(hover_icon);
                }

                @Override
                public void focusLost(FocusEvent e) {
                    setIcon(icon);
                }
            });
        }

        private Icon getHoverIcon(Image icon) {
            BufferedImage bImg = new BufferedImage(25, 25, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = bImg.createGraphics();

            graphics.setPaint(new Color(0, 0, 1,0.4f));
            graphics.drawImage(icon,0,0,null);
            graphics.fillRect(0, 0, bImg.getWidth(), bImg.getHeight());

            return new ImageIcon(bImg);
        }

    }
}
