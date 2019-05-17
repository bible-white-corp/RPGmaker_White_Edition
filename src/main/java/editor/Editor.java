package editor;

import editor.Forms.EditFrame;
import editor.Tools.Selection;

public class Editor {

    private static Selection selection = new Selection();

    public static Selection getSelection() {
        return selection;
    }

    public static void main(String[] args) {

        EditFrame frame = new EditFrame();
        frame.setVisible(true);
    }
}
