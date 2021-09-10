package com.ltsoft.game.infection.core.graphics.images;

import java.awt.image.BufferedImage;

public class CharSpriteSheet {

    // Genero le animation Type:
    public enum AnimationType {
        WALK_DOWN,
        WALK_LEFT,
        WALK_RIGHT,
        WALK_UP
    };

    // Generali:
    private String filePath;
    private int numCharsX;
    private int numCharsY;
    private int spriteWidth;
    private int spriteHeight;

    // Qua vi sono delle spritesheet che corrispondono a tutte le animazioni di 1 char:
    private SpriteSheet spriteSheetChars;

    // Fisso per tutti:
    private final int FRAMES_PER_ANIMATION = 3;


    /** Carichiamo uno spritesheet con dentro tutte le animazioni di diversi chars. */
    public CharSpriteSheet(String filePath, int numCharsX, int numCharsY, int spriteWidth, int spriteHeight) {
        this.filePath = filePath;
        this.numCharsX = numCharsX;
        this.numCharsY = numCharsY;
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;

        BufferedImage spriteSheetImage = ImageUtils.loadImage(this.filePath);
        if( spriteSheetImage == null ) return;
        int spriteWidthChars = spriteSheetImage.getWidth() / this.numCharsX;
        int spriteHeightChars = spriteSheetImage.getHeight() / this.numCharsY;
        this.spriteSheetChars = new SpriteSheet(spriteSheetImage, spriteWidthChars, spriteHeightChars);
    }


    /** Preleviamo la spritesheet animation, dando il char index, e anche il nome della animazione. */
    public SpriteAnimation getSpriteAnimation(int charIndex, float duration,  AnimationType charAnimationType) {
        int charIndexH = charIndex % this.numCharsX;
        int charIndexV = charIndex / this.numCharsX;

        // Salvo le sprites:
        int startCharPixelH = charIndexH * (FRAMES_PER_ANIMATION * this.spriteWidth);
        int startCharPixelV = charIndexV * (AnimationType.values().length * this.spriteHeight);
        Sprite[] sprites = new Sprite[FRAMES_PER_ANIMATION];
        for( int s=0; s<sprites.length; s++ ) {
            int px = startCharPixelH + (s * this.spriteWidth);
            int py = startCharPixelV + ( charAnimationType.ordinal() * this.spriteHeight );

            // Carichiamo la sprite:
            this.spriteSheetChars.getSprite(px, py, this.spriteWidth, this.spriteHeight);
        }

        // Creo l'animazione:
        SpriteAnimation animation = new SpriteAnimation();
        animation.setLoop( true );
        animation.setFrames(duration, sprites);
        return animation;
    }

}
