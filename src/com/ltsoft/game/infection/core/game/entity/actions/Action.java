package com.ltsoft.game.infection.core.game.entity.actions;

import com.ltsoft.game.infection.core.game.MovingObject;
import com.ltsoft.game.infection.core.states.GameState;

public abstract class Action {

    protected String animationName;
    protected boolean isFinished;

    public abstract void onUpdate(GameState gameState, MovingObject movingObject, double deltaTime);

    public boolean isFinished() {
        return this.isFinished;
    }

    public String getAnimationName() {
        return this.animationName;
    }
}
