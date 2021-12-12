package cegepst.engine.controls;

import cegepst.engine.graphics.RenderingEngine;

import java.awt.event.KeyEvent;

public class MovementController extends Controller {

    private static final int UP_KEY = KeyEvent.VK_W;
    private static final int LEFT_KEY = KeyEvent.VK_A;
    private static final int RIGHT_KEY = KeyEvent.VK_D;
    private static final int JUMP_KEY = KeyEvent.VK_SPACE;

    public MovementController() {
        int[] keys = {UP_KEY, LEFT_KEY, RIGHT_KEY, JUMP_KEY};
        bindKeys(keys);
        RenderingEngine.getInstance().addKeyListener(this);
    }


    public boolean isLeftHeld() {
        return isKeyHeld(LEFT_KEY);
    }

    public boolean isRightHeld() {
        return isKeyHeld(RIGHT_KEY);
    }

    public boolean isJumpPressed() {
        return isKeyPressed(JUMP_KEY);
    }

    public boolean isJumpHeld() {
        return isKeyHeld(JUMP_KEY);
    }

    public boolean isUpHeld() {
        return isKeyHeld(UP_KEY);
    }

    public boolean isUpPressed() {
        return isKeyPressed(UP_KEY);
    }

}
