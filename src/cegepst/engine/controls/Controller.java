package cegepst.engine.controls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public abstract class Controller implements KeyListener{

    private final HashMap<Integer, Boolean> pressedKeys;
    private final HashMap<Integer, Boolean> pushedKeys;

    public Controller() {
        pressedKeys = new HashMap<>();
        pushedKeys = new HashMap<>();
    }

    protected void bindPressedKeys(int[] keys) {
        for (int keycode : keys) {
            pressedKeys.put(keycode, false);
        }
    }

    protected void bindPressedKey(int key) {
        pressedKeys.put(key, false);
    }

    protected void bindPushedKey(int key) {
        pushedKeys.put(key, false);
    }

    protected void clearKeys() {
        pressedKeys.clear();
    }

    protected void removeKey(int key) {
        pressedKeys.remove(key);
    }

    protected boolean isKeyPressed(int key) {
        return pressedKeys.containsKey(key)
                && pressedKeys.get(key);
    }

    protected boolean isKeyPushed(int key) {
        if (pushedKeys.containsKey(key)
                && pushedKeys.get(key)) {
            pushedKeys.put(key,false);
            return true;
        }
        return false;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int keycode = keyEvent.getKeyCode();
        if (pushedKeys.containsKey(keycode)) {
            pushedKeys.put(keycode,true);
        }
        if (pressedKeys.containsKey(keycode)) {
            pressedKeys.put(keycode, true);
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        int keycode = keyEvent.getKeyCode();
        if (pressedKeys.containsKey(keycode)) {
            pressedKeys.put(keycode, false);
        }
    }
}
