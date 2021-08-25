package com.ltsoft.game.infection.core.inputs.controller;

import com.ltsoft.game.infection.core.inputs.Input;

import java.awt.event.KeyEvent;

public class PlayerController implements Controller {

    private final Input input;

    public PlayerController(Input input) {
        this.input = input;
    }

    @Override
    public boolean isRequestingUp() {
        return this.input.isKeyPressed(KeyEvent.VK_W);
    }

    @Override
    public boolean isRequestingDown() {
        return this.input.isKeyPressed(KeyEvent.VK_S);
    }

    @Override
    public boolean isRequestingLeft() {
        return this.input.isKeyPressed(KeyEvent.VK_A);
    }

    @Override
    public boolean isRequestingRight() {
        return this.input.isKeyPressed(KeyEvent.VK_D);
    }
}
