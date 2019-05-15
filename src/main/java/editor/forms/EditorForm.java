package editor.forms;

import javax.swing.*;

public class EditorForm {
    private JPanel EditorWindow;
    private JToolBar FilesBar;
    private JToolBar ToolsBar;
    private JPanel ToolsView;
    private JScrollPane GameView;
    private JScrollPane ProjectView;
    private JScrollPane TileSetView;
    private JPanel LeftPanel;


    public static JFrame createFrame(){
        JFrame frame = new JFrame("Rpg Maker");
        frame.setContentPane(new EditorForm().EditorWindow);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        return frame;
    }
}
