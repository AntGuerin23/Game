package cegepst.engine.controls;

import cegepst.engine.graphics.RenderingEngine;

import java.awt.event.KeyEvent;

public class MovementController extends Controller {

    private int downKey = KeyEvent.VK_S; //TODO: Crouch
    private int leftKey = KeyEvent.VK_A;
    private int rightKey = KeyEvent.VK_D;
    //TODO: Jump Key

    public MovementController() {
        int[] keys = {downKey, leftKey, rightKey};
        bindKeys(keys);
        RenderingEngine.getInstance().addKeyListener(this);
    }

    public boolean isDownHeld() {
        return isKeyHeld(downKey);
    }

    public boolean isLeftHeld() {
        return isKeyHeld(leftKey);
    }

    public boolean isRightHeld() {
        return isKeyHeld(rightKey);
    }

    public boolean isMoving() {
        return isLeftHeld() || isRightHeld() || isDownHeld(); //TOOD: Add jump
    }

    public void setDownKey(int downKey) {
        removeKey(this.downKey);
        bindKey(downKey);
        this.downKey = downKey;
    }

    public void setLeftKey(int leftKey) {
        removeKey(this.leftKey);
        bindKey(leftKey);
        this.leftKey = leftKey;
    }

    public void setRightKey(int rightKey) {
        removeKey(this.rightKey);
        this.rightKey = rightKey;
        bindKey(rightKey);
    }

}
