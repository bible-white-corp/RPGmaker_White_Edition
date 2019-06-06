package engine.Forms.Pause;

import engine.Engine;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PauseResumeAction {


    public static void perform() {

        Engine.getEngineFrame().getDisplay().start();
    }
}
