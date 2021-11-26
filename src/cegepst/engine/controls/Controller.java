package cegepst.engine.controls;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public abstract class Controller implements KeyListener{

    private static final int IDLE = 0;
    private static final int PUSHED = 1;
    private static final int HELD = 2;

    private final HashMap<Integer, Integer> keys;

    public Controller() {
        keys = new HashMap<>();
    }

    protected void bindKeys(int[] keys) {
        for (int keycode : keys) {
            this.keys.put(keycode, IDLE);
        }
    }

    protected void bindKey(int key) {
        keys.put(key, IDLE);
    }

    protected void clearKeys() {
        keys.clear();
    }

    protected void removeKey(int key) {
        keys.remove(key);
    }

    protected boolean isKeyPressed(int key) {
        boolean isPressed = keys.containsKey(key)
                && keys.get(key) == PUSHED;
        if (isPressed) {
            keys.put(key, HELD);
        }
        return isPressed;
    }

    protected boolean isKeyHeld(int key) {
        return (keys.containsKey(key)
                && keys.get(key) == HELD || keys.get(key) == PUSHED);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int keycode = keyEvent.getKeyCode();
        if (keys.containsKey(keycode) && keys.get(keycode) == PUSHED) {
            keys.put(keycode,HELD);
        } else if (keys.containsKey(keycode) && keys.get(keycode) == IDLE) {
            keys.put(keycode,PUSHED);
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        int keycode = keyEvent.getKeyCode();
        if (keys.containsKey(keycode)) {
            keys.put(keycode, IDLE);
        }
    }
}
