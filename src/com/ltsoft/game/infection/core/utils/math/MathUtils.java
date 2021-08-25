package com.ltsoft.game.infection.core.utils.math;

public class MathUtils {

    public static float clamp(float value, float min_value, float max_value) {
        return Math.max(Math.min(value, max_value), min_value);
    }
}
