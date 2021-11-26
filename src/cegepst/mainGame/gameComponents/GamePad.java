package cegepst.mainGame.gameComponents;

import cegepst.engine.controls.MovementController;

import java.awt.event.KeyEvent;

public class GamePad extends MovementController {

    private int quitKey = KeyEvent.VK_ESCAPE;
    private int fireKey = KeyEvent.VK_X;
    private int fullScreenKey = KeyEvent.VK_F11;
    private int jumpKey = KeyEvent.VK_SPACE;
    private int debugKey = KeyEvent.VK_F3;

    public GamePad() {
        bindKey(quitKey);
        bindKey(fullScreenKey);
        bindKey(jumpKey);
        bindKey(debugKey);
    }


    public boolean isQuitPressed() {
        return isKeyPressed(quitKey);
    }

    public boolean isFirePressed() {
        return isKeyPressed(fireKey);
    }

    public boolean isFullScreenHeld() {
        return isKeyPressed(fullScreenKey);
    }

    public boolean isJumpPressed() {
        return isKeyPressed(jumpKey);
    }

    public boolean isDebugPressed() {
        return isKeyPressed(debugKey);
    }


}
