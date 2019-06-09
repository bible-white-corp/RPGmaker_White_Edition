package engine.Forms.Pause;

import engine.Engine;

public class PauseResumeAction {


    public static void perform() {

        Engine.getEngineFrame().getDisplay().start();
        Engine.getEngineFrame().getNpcCalculus().start();
    }
}
