package com.ltsoft.game.infection.core.utils.math;

public class Vector2f {

    public float x;
    public float y;

    public Vector2f() {
        this(0.0f, 0.0f);
    }

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2f(Vector2f v) {
        this(v.x, v.y);
    }

    public Vector2f set(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vector2f add(Vector2f v) {
        Vector2f result = new Vector2f();
        result.x = this.x + v.x;
        result.y = this.y + v.y;
        return result;
    }

    public Vector2f multiply(float value) {
        Vector2f result = new Vector2f(this);
        result.x *= value;
        result.y *= value;
        return result;
    }

    public Vector2f normalize() {
        double lengthSq = this.lengthSq();
        if( lengthSq != 0.0f ) {
            float recLength = 1.0f / (float)Math.sqrt( lengthSq );
            return this.multiply( recLength );
        }
        return new Vector2f(this);
    }

    public String toString() {
        return "Vector2D: (" + this.x + ", " + this.y + ")";
    }

    public float lengthSq() {
        return (this.x * this.x) + (this.y * this.y);
    }

    public float length() {
        return Math.sqrt( this.lengthSq() );
    }

    public float angle() {
        return (float)Math.atan2(this.y, this.x);
    }
}
