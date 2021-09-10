package com.ltsoft.game.infection.core.utils.math;

public class MathUtils {

    public static float lerp(float min, float max, float t) {
        return min + t * (max - min);
    }

    public static float clamp(float value, float min_value, float max_value) {
        return Math.max(Math.min(value, max_value), min_value);
    }
}
