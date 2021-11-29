package cegepst.mainGame.miscellaneous.other;

import cegepst.engine.controls.MovementController;

import java.awt.event.KeyEvent;

public class GamePad extends MovementController {

    private static final int QUIT_KEY = KeyEvent.VK_ESCAPE;
    private static final int FIRE_KEY = KeyEvent.VK_SHIFT;
    private static final int FULL_SCREEN_KEY = KeyEvent.VK_F11;
    private static final int DEBUG_KEY = KeyEvent.VK_F3;

    public GamePad() {
        bindKey(QUIT_KEY);
        bindKey(FULL_SCREEN_KEY);
        bindKey(DEBUG_KEY);
        bindKey(FIRE_KEY);
    }

    public boolean isQuitPressed() {
        return isKeyPressed(QUIT_KEY);
    }

    public boolean isFirePressed() {
        return isKeyPressed(FIRE_KEY);
    }

    public boolean isFullScreenPressed() {
        return isKeyPressed(FULL_SCREEN_KEY);
    }

    public boolean isDebugPressed() {
        return isKeyPressed(DEBUG_KEY);
    }


}
