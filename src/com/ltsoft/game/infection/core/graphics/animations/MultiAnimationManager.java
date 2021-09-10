package com.ltsoft.game.infection.core.graphics.animations;

import com.ltsoft.game.infection.core.graphics.images.CharSpriteSheet;
import com.ltsoft.game.infection.core.graphics.images.Sprite;
import com.ltsoft.game.infection.core.graphics.images.SpriteAnimation;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class MultiAnimationManager {

    private Map<CharSpriteSheet.AnimationType, SpriteAnimation> mAnimations;
    private CharSpriteSheet.AnimationType mCurrentAnimationType;
    private SpriteAnimation mCurrentAnimation;


    /** Il Mulitple animation manager. */
    public MultiAnimationManager() {
        this.mAnimations = new HashMap<>();
        this.mCurrentAnimationType = null;
        this.mCurrentAnimation = null;
    }


    /** Carichiamo le animazioni del character nello spritesheet. */
    public void loadAnimations(CharSpriteSheet charSpriteSheet, int characterIndex, float duration) {
        CharSpriteSheet.AnimationType[] animationTypes = CharSpriteSheet.AnimationType.values();
        for(int i=0; i<animationTypes.length; i++ ) {
            CharSpriteSheet.AnimationType animationType = animationTypes[i];

            SpriteAnimation spriteAnimation = charSpriteSheet.getSpriteAnimation(characterIndex, duration, animationType);
            this.mAnimations.put( animationType, spriteAnimation );
        }
    }


    /** Settiamo una animazione. */
    public void setAnimation(CharSpriteSheet.AnimationType animationType) {
        if( this.mCurrentAnimationType == animationType ) return;
        this.mCurrentAnimationType = animationType;
        this.mCurrentAnimation = this.mAnimations.get( this.mCurrentAnimationType );
    }


    /** Aggiorno la animazione. */
    public void onUpdate(float fDeltaTime) {
        if(this.mCurrentAnimation == null) return;
        this.mCurrentAnimation.onUpdate( fDeltaTime );
    }


    /** Prelevo la sprite attuale. */
    public Sprite getSprite() {
        if( this.mCurrentAnimation == null ) return null;
        return this.mCurrentAnimation.getSprite();
    }
}

