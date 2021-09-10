package com.ltsoft.game.infection.core.utils;

import com.ltsoft.game.infection.core.inputs.controller.Controller;
import com.ltsoft.game.infection.core.utils.math.Vector2f;

public class Motion {

    private Vector2f direction;
    private float speed;
    private float multiplier;


    public Motion(float speed) {
        this.speed = speed;
        this.direction = new Vector2f();
        this.multiplier = 1.0f;
    }

    public void onUpdate(Controller controller, float deltaTime) {
        float deltaX = 0.0f;
        float deltaY = 0.0f;
        if( controller.isRequestingUp() ) {
            deltaY += -1.0f;
        }
        if( controller.isRequestingDown() ) {
            deltaY += 1.0f;
        }
        if( controller.isRequestingLeft() ) {
            deltaX += -1.0f;
        }
        if( controller.isRequestingRight() ) {
            deltaX += 1.0f;
        }

        this.direction = this.direction.set(deltaX, deltaY);
        this.direction = this.direction.normalize();
    }

    public void multiply(float multiplier) {
        this.multiplier = multiplier;
    }

    public Vector2f getVector() {
        return this.direction.multiply( this.speed * this.multiplier );
    }

    public Vector2f getDirection() {
        return this.direction;
    }

    public float getSpeed() {
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
