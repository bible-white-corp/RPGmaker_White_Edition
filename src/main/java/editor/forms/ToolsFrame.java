package editor.forms;

import javax.swing.*;

public class ToolsFrame extends JToolBar {

    public ToolsFrame()
    {
        ImageIcon icon = new ImageIcon("src/main/resources/images/icon_sav.jpg");

        JButton exitButton = new JButton(icon);
        add(exitButton);
    }
}
