package com.ltsoft.game.infection.core.game.components;

import com.ltsoft.game.infection.core.utils.math.Vector2f;

public class VelocityComponent extends EntityComponent {

    private Vector2f velocity;
    private Vector2f scaledVelocity;

    /** Riscaling della velocità sulla base del tempo. */
    public void onUpdate(float fDeltaTime) {
        this.scaledVelocity = this.velocity.multiply( fDeltaTime );
    }

    public void setVelocity(Vector2f velocity) {
        this.velocity = velocity;
    }

    public float getSpeed() {
        return this.velocity.length();
    }

    public float getScaledSpeed() {
        return this.scaledVelocity.length();
    }

    public Vector2f getVelocity() {
        return this.velocity;
    }

    public Vector2f getScaledVelocity() {
        return this.scaledVelocity;
    }
}
