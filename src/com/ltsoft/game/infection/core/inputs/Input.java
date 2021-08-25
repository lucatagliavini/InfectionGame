package com.ltsoft.game.infection.core.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener {

    private static final int NUM_KEYS = 192;
    private boolean[] keyPressed;

    public Input() {
        this.keyPressed = new boolean[NUM_KEYS];
    }

    public boolean isKeyPressed(int keyCode) {
        return this.keyPressed[keyCode];
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.keyPressed[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.keyPressed[e.getKeyCode()] = false;
    }
}
