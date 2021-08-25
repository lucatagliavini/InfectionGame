package com.ltsoft.game.infection.core.graphics.animations;

import com.ltsoft.game.infection.core.graphics.images.SpriteSet;
import com.ltsoft.game.infection.core.utils.Direction;

import java.awt.image.BufferedImage;

public class AnimationManager {

    private SpriteSet spriteSet;
    private BufferedImage currentAnimationSheet;
    private String currentAnimationName;
    private Direction direction;

    private int spriteWidth;
    private int spriteHeight;
    private int numSpritesWidth;
    private int numSpritesHeght;

    private double totalDuration;
    private double currentFrameTime;
    private double durationFrameTime;
    private int frameIndex;
    private int directionIndex;

    public AnimationManager(SpriteSet spriteSet, int spriteWidth, int spriteHeight, double totalDuration) {
        this.spriteSet = spriteSet;
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
        this.totalDuration = totalDuration;
        this.directionIndex = 0;
        this.currentAnimationName = "";
        this.reset();
    }

    public void setAnimation(String animationName) {
        if( this.currentAnimationName.equals( animationName ) ) return;
        this.currentAnimationName = animationName;
        this.currentAnimationSheet = this.spriteSet.getSheet( animationName );

        this.numSpritesWidth = this.currentAnimationSheet.getWidth() / this.spriteWidth;
        this.numSpritesHeght = this.currentAnimationSheet.getHeight() / this.spriteHeight;

        this.durationFrameTime = this.totalDuration / (double)this.numSpritesWidth;
    }

    public void onUpdate(double deltaTime, Direction direction) {
        this.directionIndex = direction.getAnimationRow();
        this.currentFrameTime += deltaTime;
        // Incremento la frame index:
        if( this.currentFrameTime >= this.durationFrameTime ) {
            this.currentFrameTime = 0;
            this.frameIndex++;
        }
        // Resetto come se fossi in loop:
        if( this.frameIndex >= this.numSpritesWidth ) {
            this.reset();
        }
    }

    private void reset() {
        this.frameIndex = 0;
        this.currentFrameTime = 0;
    }

    public BufferedImage getSprite() {
        int posX = this.frameIndex * this.spriteWidth;
        int posY = this.directionIndex * this.spriteHeight;
        return this.currentAnimationSheet.getSubimage(
            posX, posY, this.spriteWidth, this.spriteHeight
        );
    }
}
