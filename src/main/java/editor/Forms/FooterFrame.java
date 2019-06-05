package editor.Forms;

import editor.Editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FooterFrame extends JPanel {

    JLabel label = new JLabel("( 0 , 0 )          ");

    public FooterFrame()
    {
        setLayout(new BorderLayout());

        add(label, BorderLayout.EAST);
    }

    public void setFooterMouseListener(GameFrame gameFrame)
    {
        gameFrame.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);

                int w = Editor.mainFrame.level.getTileWidth();
                int h = Editor.mainFrame.level.getTileWidth();

                label.setText("( " + e.getX() / w + " , " + e.getY() / h + " )          ");
            }
        });
    }
}
