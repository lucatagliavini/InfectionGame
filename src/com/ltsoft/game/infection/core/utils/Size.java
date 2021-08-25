package com.ltsoft.game.infection.core.utils;

public class Size {

    private double width;
    private double height;

    public Size(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return (int)this.width;
    }

    public int getHeight() {
        return (int)this.height;
    }
}
