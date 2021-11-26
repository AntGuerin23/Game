package cegepst.mainGame.gameComponents;

import cegepst.engine.controls.MovementController;

import java.awt.event.KeyEvent;

public class GamePad extends MovementController {

    private int quitKey = KeyEvent.VK_ESCAPE;
    private int fireKey = KeyEvent.VK_X;
    private int fullScreenKey = KeyEvent.VK_F11;
    private int jumpKey = KeyEvent.VK_SPACE;

    public GamePad() {
        bindKey(quitKey);
        bindKey(fullScreenKey);
        bindKey(jumpKey);
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


}
