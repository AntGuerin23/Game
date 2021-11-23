package cegepst.mainGame.gameComponents;

import cegepst.engine.controls.MovementController;

import java.awt.event.KeyEvent;

public class GamePad extends MovementController {

    private int quitKey = KeyEvent.VK_ESCAPE;
    private int fireKey = KeyEvent.VK_X;
    private int fullScreenKey = KeyEvent.VK_F11;

    public GamePad() {
        bindPushedKey(quitKey);
        bindPushedKey(fullScreenKey);
    }


    public boolean isQuitPushed() {
        return isKeyPushed(quitKey);
    }

    public boolean isFirePressed() {
        return isKeyPressed(fireKey);
    }

    public boolean isFullScreenPushed() {
        return isKeyPushed(fullScreenKey);
    }


}
