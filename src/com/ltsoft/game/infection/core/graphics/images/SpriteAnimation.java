package com.ltsoft.game.infection.core.graphics.images;

import java.awt.geom.AffineTransform;;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpriteAnimation {

    // Settiamo le durations:
    private float fDuration;
    private float fFrameDuration;
    private int nTotalFrames;

    private float fCurrentFrameDuration;
    private int nFrameIndex;

    // Boolean per controllo animazione:
    private boolean bMustLoop;
    private boolean bIsFinished;

    private List<Sprite> spriteFrames;
    private AffineTransform transform;


    /** Inizializzo una sprite animation. */
    public SpriteAnimation() {
        this.spriteFrames = new ArrayList<>();
        this.transform = new AffineTransform();

        this.bIsFinished = false;
        this.nFrameIndex = 0;
        this.setLoop( true );
    }

    /** L'update con la gestione del cambio frame. */
    public void onUpdate(float fDeltaTime) {
        this.fCurrentFrameDuration += fDeltaTime;

        // Cambio frame:
        if( this.fCurrentFrameDuration >= this.fFrameDuration ) {
            this.fCurrentFrameDuration = 0.0f;
            this.nFrameIndex++;
        }

        // Check se andiamo oltre:
        if( this.nFrameIndex >= this.nTotalFrames ) {
            this.bIsFinished = true;

            // Se looppiamo reset, se no ultima:
            if( this.bMustLoop ) {
                this.nFrameIndex = (this.nFrameIndex)%this.nTotalFrames;
            }
            else {
                this.nFrameIndex = (this.nTotalFrames-1);
            }
        }
    }

    /** La resetto in tutto. */
    public void reset() {
        this.bIsFinished = false;
        this.nFrameIndex = 0;
        this.fCurrentFrameDuration = 0.0f;
    }

    /** Bisogna aggiungere le frames. */
    public void setFrames(float fDuration, Sprite ... sprites) {
        this.fDuration = fDuration;
        this.spriteFrames.clear();
        this.spriteFrames.addAll(Arrays.asList(sprites));
        this.nTotalFrames = sprites.length;;
        this.fFrameDuration = this.fDuration / (float)this.nTotalFrames;
        this.reset();
    }


    /** Restituisco la current sprite. */
    public Sprite getSprite() {
        if( this.spriteFrames.size() == 0 ) return null;
        Sprite currentSprite = this.spriteFrames.get( this.nFrameIndex );
        currentSprite.setTransform( this.transform );
        return currentSprite;
    }

    /** La transform si attiva su tutte le sprites. */
    public void setTransform(AffineTransform transform) {
        this.transform = transform;
    }

    /** Transform applicata a tutte le sprites. */
    public AffineTransform getTransform() {
        return this.transform;
    }

    /** Se attiviamo, loop della animazione (comportamento default). */
    public void setLoop(boolean loop) {
        this.bMustLoop = loop;
    }

    /** Check se è un loop. */
    public boolean isLoop() {
        return this.bMustLoop;
    }

    /** Check se è stata disegnata tutta almeno 1 volta. */
    public boolean isFinished() {
        return this.bIsFinished;
    }
}
