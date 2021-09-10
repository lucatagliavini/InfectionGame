package com.ltsoft.game.infection.core.graphics.images;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    // Lo spritesheet caricato:
    private BufferedImage spriteSheet;
    private int spriteWidth;
    private int spriteHeight;

    /** Carichiamo l'immagine spritesheet. */
    public SpriteSheet(BufferedImage spriteSheet, int spriteWidth, int spriteHeight) {
        this.spriteSheet = spriteSheet;

        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
    }


    /** Prelevo la sprite seguendo l'indice X=horizontal, Y=vertical */
    public BufferedImage getSprite(int xIndex, int yIndex) {
        int px = xIndex * this.spriteWidth;
        int py = yIndex * this.spriteHeight;
        // Restituisco la sprite selezionata:
        return this.getSprite(px, py, this.spriteWidth, this.spriteHeight);
    }

    /** Prelevo la sprite utilizzando un rettangolo specifico. */
    public BufferedImage getSprite(int px, int py, int width, int height) {
        return this.spriteSheet.getSubimage(px, py, width, height);
    }
}
