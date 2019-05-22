package editor.Forms;

import editor.Editor;
import editor.Object.SpriteSheet;
import editor.Tiles.TilePair;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ObjectEditionFrame extends JPanel {

    SpriteSheet sheet;

    public void setSheet(int index){
        List<SpriteSheet> list = Editor.world.worldObjects.getSpriteSheetList();
        if (index >= list.size()){
            sheet = null;
            this.repaint();
            return;
        }
        sheet = list.get(index);
        this.repaint();
    }

    public ObjectEditionFrame() {

        setBackground(Color.WHITE);
        setSheet(0);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (sheet == null)
            return;

        g.drawImage(sheet.getImg(), 0, 0, null);
    }

}
