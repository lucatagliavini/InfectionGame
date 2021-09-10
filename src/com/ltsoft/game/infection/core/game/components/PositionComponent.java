package com.ltsoft.game.infection.core.game.components;

import com.ltsoft.game.infection.core.utils.math.Vector2f;

public class PositionComponent extends EntityComponent {

    private Vector2f mPosition;

    public PositionComponent(float px, float py) {
        this.mPosition = new Vector2f(px, py);
    }

    public void setPosition(Vector2f mPosition) {
        this.mPosition = mPosition;
    }

    public Vector2f getPosition() {
        return this.mPosition;
    }
}
