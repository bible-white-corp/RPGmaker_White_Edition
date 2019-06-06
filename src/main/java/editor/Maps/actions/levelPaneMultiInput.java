package editor.Maps.actions;

import editor.Editor;

import javax.swing.*;
import java.awt.*;

public class levelPaneMultiInput {

    private JSpinner widthField;
    private JSpinner heightField;
    private JSpinner tileHeightField;
    private JSpinner tileWidthField;
    private JTextField levelNameField;

    private JPanel askPanel;
    private int result;

    public levelPaneMultiInput() {
        widthField = new JSpinner();
        widthField.setPreferredSize(new Dimension(70,20));
        widthField.setEditor(new JSpinner.NumberEditor(widthField));

        heightField = new JSpinner();
        heightField.setPreferredSize(new Dimension(70,20));
        heightField.setEditor(new JSpinner.NumberEditor(heightField));

        tileHeightField = new JSpinner();
        tileHeightField.setPreferredSize(new Dimension(70,20));
        tileHeightField.setEditor(new JSpinner.NumberEditor(tileHeightField));

        tileWidthField = new JSpinner();
        tileWidthField.setPreferredSize(new Dimension(70,20));
        tileWidthField.setEditor(new JSpinner.NumberEditor(tileWidthField));

        levelNameField = new JTextField(10);
        levelNameField.setMinimumSize(new Dimension(100,4));

        askPanel = new JPanel();

        askPanel.add(new JLabel("Width:"));
        askPanel.add(widthField);

        askPanel.add(new JLabel("Height:"));
        askPanel.add(heightField);

        askPanel.add(new JLabel("Tile Height:"));
        askPanel.add(tileHeightField);

        askPanel.add(new JLabel("Tile Width:"));
        askPanel.add(tileWidthField);

        askPanel.add(new JLabel("Level Name:"));
        askPanel.add(levelNameField);

        result = JOptionPane.showConfirmDialog(Editor.editFrame, askPanel,
                "Enter values for the new map", JOptionPane.OK_CANCEL_OPTION);
    }

    public int getWidthField() {
        return (int) widthField.getValue();
    }

    public int getHeightField() {
        return (int) heightField.getValue();
    }

    public int getTileHeightField() {
        return (int) tileHeightField.getValue();
    }

    public int getTileWidthField() {
        return (int) tileWidthField.getValue();
    }

    public String getLevelNameField() {
        return levelNameField.getText();
    }

    public int getResult() {
        return result;
    }
}
