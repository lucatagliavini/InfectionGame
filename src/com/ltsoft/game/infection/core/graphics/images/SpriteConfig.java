package com.ltsoft.game.infection.core.graphics.images;

import com.ltsoft.game.infection.core.utils.math.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SpriteConfig {

    // Questo è l'index da settare per le direzioni:
    private int D_INDEX = 0;
    private int L_INDEX = 1;
    private int R_INDEX = 2;
    private int U_INDEX = 3;

    // Numero di character nello spritesheet:
    private int NUM_CHARACTERS = 8;

    // Numero di sprites per singola animazione:
    private int NUM_SPRITES_WIDTH = 3;
    private int NUM_SPRITES_HEIGHT = 3;

    // Dimensioni:
    private Vector2f singleSpriteDimension;
    private Vector2f spriteSheetDimension;


    /** Costruttore di default per gli spritesheet. */
    public SpriteConfig(Vector2f singleSpriteDimension, Vector2f spriteSheetDimension) {
        this.singleSpriteDimension = singleSpriteDimension;
        this.spriteSheetDimension = spriteSheetDimension;
    }
}
