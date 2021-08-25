package com.ltsoft.game.infection.core.utils;

import com.ltsoft.game.infection.core.inputs.controller.Controller;
import com.ltsoft.game.infection.core.utils.math.Vector2f;

public class Motion {

    private Vector2f direction;
    private double speed;
    private double multiplier;


    public Motion(double speed) {
        this.speed = speed;
        this.direction = new Vector2f();
        this.multiplier = 1.0;
    }

    public void onUpdate(Controller controller, double deltaTime) {
        float deltaX = 0.0f;
        float deltaY = 0.0f;
        if( controller.isRequestingUp() ) {
            deltaY += -1.0;
        }
        if( controller.isRequestingDown() ) {
            deltaY += 1.0;
        }
        if( controller.isRequestingLeft() ) {
            deltaX += -1.0;
        }
        if( controller.isRequestingRight() ) {
            deltaX += 1.0;
        }

        this.direction = this.direction.set(deltaX, deltaY);
        this.direction = this.direction.normalize();
    }

    public void multiply(double multiplier) {
        this.multiplier = multiplier;
    }

    public Vector2f getVector() {
        return this.direction.multiply( this.speed * this.multiplier );
    }

    public Vector2f getDirection() {
        return this.direction;
    }

    public double getSpeed() {
        return this.speed;
    }

    public boolean isMoving() {
        Vector2f vector = this.getVector();
        return ( vector.lengthSq() > 0 );
    }

    public void stop() {
        this.direction.set( 0.0f, 0.0f );
    }
}
