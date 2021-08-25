package com.ltsoft.game.infection.core.utils;

import com.ltsoft.game.infection.core.utils.math.Vector2f;

public class Position {

    public static final double PROXYMITY_RANGE = 10.0;
    private Vector2f pos;

    public Position(Position position) {
        this( position.getPosition().x, position.getPosition().y);
    }

    public Position(float x, float y) {
        this.pos = new Vector2f(x, y);
    }

    public Position copy() {
        return new Position( this );
    }

    public void setPosition(float x, float y) {
        this.pos.set(x, y);
    }

    public Vector2f getPosition() {
        return this.pos;
    }

    public void addOffset(Vector2f offset) {
        this.pos = this.pos.add( offset );
    }

    public void apply(Vector2f movement) {
        this.pos = this.pos.add( movement );
    }

    public boolean isInRangeOf(Position target, double range) {
        double dx = this.pos.x - target.getPosition().x;
        double dy = this.pos.y - target.getPosition().y;
        return Math.sqrt( dx * dx + dy * dy ) < range;
    }

    public int getX() {
        return (int)this.pos.x;
    }

    public int getY() {
        return (int)this.pos.y;
    }
}
