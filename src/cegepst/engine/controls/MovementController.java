package cegepst.engine.controls;

import cegepst.engine.graphics.RenderingEngine;

import java.awt.event.KeyEvent;

public class MovementController extends Controller {

    private static final int DOWN_KEY = KeyEvent.VK_S; //TODO: Crouch
    private static final int LEFT_KEY = KeyEvent.VK_A;
    private static final int RIGHT_KEY = KeyEvent.VK_D;
    private static final int JUMP_KEY = KeyEvent.VK_SPACE;

    public MovementController() {
        int[] keys = {DOWN_KEY, LEFT_KEY, RIGHT_KEY, JUMP_KEY};
        bindKeys(keys);
        RenderingEngine.getInstance().addKeyListener(this);
    }

    public boolean isDownHeld() {
        return isKeyHeld(DOWN_KEY);
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

}
