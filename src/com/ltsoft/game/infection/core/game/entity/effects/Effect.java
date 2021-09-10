package com.ltsoft.game.infection.core.game.entity.effects;

import com.ltsoft.game.infection.core.game.MovingObject;
import com.ltsoft.game.infection.core.states.GameState;

public abstract class Effect {

    protected double fDuration;
    protected double fCurrentDuration;
    private boolean isFinished;

    public Effect(double fDuration) {
        this.fDuration = fDuration;
        this.fCurrentDuration = this.fDuration;
        this.isFinished = false;
    }

    public void onUpdate(GameState gameState, MovingObject movingObject, float deltaTime) {
        this.fCurrentDuration -= deltaTime;
        if( this.fCurrentDuration <= 0.0 ) {
            this.isFinished = true;
        }
    }

    public boolean isFinished() {
        return (this.isFinished);
    }
}
