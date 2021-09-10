package com.ltsoft.game.infection.core.game.components;

import com.ltsoft.game.infection.core.utils.math.Vector2f;

public class SizeComponent extends EntityComponent {

    private Vector2f mSize;

    public SizeComponent(float sx, float sy) {
        this.mSize = new Vector2f( sx, sy );
    }

    public Vector2f getSize() {
        return this.mSize;
    }

    public void setSize(Vector2f mSize) {
        this.mSize = mSize;
    }
}
