package com.ltsoft.game.infection.core.game.entity.effects;

import com.ltsoft.game.infection.core.game.MovingObject;
import com.ltsoft.game.infection.core.states.GameState;

public class Caffeinated extends Effect {

    private static final double CAFFEINATED_DURATION = 5.0;
    private double speedMultiplier;


    public Caffeinated() {
        super(CAFFEINATED_DURATION);
        this.speedMultiplier = -2.5;
    }

    @Override
    public void onUpdate(GameState gameState, MovingObject movingObject, double deltaTime) {
        super.onUpdate(gameState, movingObject, deltaTime);
        movingObject.setSpeedMultiplier(this.speedMultiplier);
        if( this.isFinished() ) movingObject.setSpeedMultiplier( 1.0 );
    }
}
