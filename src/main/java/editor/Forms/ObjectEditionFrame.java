package editor.Forms;

import editor.Editor;
import editor.Object.Sprite;
import editor.Object.SpriteSheet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ObjectEditionFrame extends JPanel {

    SpriteSheet sheet;
    int ssIndex;
    Point first;
    Point last;
    MouseAdapter mouse;

    public ObjectEditionFrame() {

        setBackground(Color.WHITE);
        ssIndex = 0;
        setSheet(0);
        mouse = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                first = new Point(e.getX(), e.getY());
                last = new Point(e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //launch action
                String name = (String) JOptionPane.showInputDialog(Editor.mainFrame,
                        "Give a name for the new sprite", "New sprite",
                        JOptionPane.INFORMATION_MESSAGE);
                desactivate();
                if (name == null)
                    return;
                int x1 = Math.min(first.x, last.x);
                int x2 = Math.max(first.x, last.x);
                int y1 = Math.min(first.y, last.y);
                int y2 = Math.max(first.y, last.y);

                Editor.world.worldObjects.addSprite(new Sprite(x1, y1, x2 - x1, y2 - y1, name, ssIndex));
                first = null;
                last = null;
                refresh();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                last.setLocation(e.getX(), e.getY());
                refresh();
            }
        };
    }


    public boolean is_set(){
        return sheet != null;
    }

    private void refresh(){
        this.repaint();
    }

    public void active(){
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }

    public void desactivate(){
        removeMouseListener(mouse);
        removeMouseMotionListener(mouse);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (sheet == null)
            return;

        g.drawImage(sheet.getImg(), 0, 0, null);

        if (first == null)
            return;

        g.setColor(new Color(0.5f,0.5f,0.5f,0.7f));

        int x1 = Math.min(first.x, last.x);
        int x2 = Math.max(first.x, last.x);
        int y1 = Math.min(first.y, last.y);
        int y2 = Math.max(first.y, last.y);

        g.fillRect(x1, y1, x2 - x1, y2 - y1);
    }

    public void setSheet(int index){
        List<SpriteSheet> list = Editor.world.worldObjects.getSpriteSheetList();

        if (index >= list.size()){
            sheet = null;
            ssIndex = -1;
            this.repaint();
            return;
        }
        sheet = list.get(index);
        ssIndex = index;
        this.repaint();
    }

}
