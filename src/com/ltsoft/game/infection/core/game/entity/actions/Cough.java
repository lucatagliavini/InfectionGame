package com.ltsoft.game.infection.core.game.entity.actions;

import com.ltsoft.game.infection.core.game.MovingObject;
import com.ltsoft.game.infection.core.states.GameState;

public class Cough extends Action {

    private double fDuration;
    private double fCurrentDuration;

    public Cough() {
        this.fDuration = 2.0;
        this.fCurrentDuration = this.fDuration;
        this.isFinished = false;
        this.animationName = "cough";
    }

    @Override
    public void onUpdate(GameState gameState, MovingObject movingObject, double deltaTime) {
        this.fCurrentDuration -= deltaTime;
        if( this.fCurrentDuration <= 0.0 ) this.isFinished = true;


    }
}
