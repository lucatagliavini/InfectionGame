package com.ltsoft.game.infection.core;

import com.ltsoft.game.infection.core.game.GameObject;
import com.ltsoft.game.infection.core.game.entity.PlayerObject;
import com.ltsoft.game.infection.core.game.states.PlayState;
import com.ltsoft.game.infection.core.graphics.Display;
import com.ltsoft.game.infection.core.graphics.images.SpriteLibrary;
import com.ltsoft.game.infection.core.inputs.Input;
import com.ltsoft.game.infection.core.inputs.controller.PlayerController;
import com.ltsoft.game.infection.core.states.GameState;

import java.util.ArrayList;
import java.util.List;

public class GameContainer {

    // Creazione oggetti per il GameContainer:
    private Display display;
    private Input input;

    private GameSettings gameSettings;

    // Oggetti:
    private GameState gameState;

    public GameContainer(int width, int height) {
        this.display = new Display(width, height);
        this.input = this.display.getInput();
        this.gameSettings = new GameSettings(true);
    }

    public void onInit() {
        this.gameState = new PlayState(this.display.getDisplaySize(), this.input);
    }

    public void onUpdate(double deltaTime) {
        this.gameState.onUpdate(deltaTime);
    }


    public void onRender() {
        // Passiamo lo stato:
        this.display.onRender(this.gameState, this.gameSettings);
    }
}
