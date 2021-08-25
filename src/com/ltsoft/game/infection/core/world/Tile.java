package com.ltsoft.game.infection.core.world;

import com.ltsoft.game.infection.core.graphics.images.SpriteLibrary;

import java.awt.image.BufferedImage;

public class Tile {

    public static final int TILE_SIZE = 64;
    private String tileName;

    public Tile() {
        this.tileName = "woodfloor";
    }

    public BufferedImage getSprite() {
        return SpriteLibrary.getTileSprite(this.tileName);
    }
}
