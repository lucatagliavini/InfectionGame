package com.ltsoft.game.infection.core.graphics.images;

import com.ltsoft.game.infection.core.utils.math.Vector2f;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Sprite {

    // Costruzione della sprite:
    private BufferedImage spriteImage;
    private AffineTransform transform;

    /** Generazione della sprite. */
    public Sprite(BufferedImage spriteImage) {
        this.setImage( spriteImage );
        this.setTransform( new AffineTransform() );
    }

    public void setTransform(AffineTransform transform) {
        this.transform = transform;
    }

    public void setImage(BufferedImage spriteImage) {
        this.spriteImage = spriteImage;
    }

    public void clearTransform() {
        this.transform.setToIdentity();
    }

    public void setTranslation(Vector2f translation) {
        this.transform.translate( translation.x, translation.y );
    }

    public void setRotation(float angle) {
        this.transform.rotate( angle );
    }

    public void setScale(Vector2f scale) {
        this.transform.scale( scale.x, scale.y );
    }

    public AffineTransform getTransform() {
        return this.transform;
    }

    /** Accesso low level alla immagine. */
    public BufferedImage getImage() {
        return this.spriteImage;
    }

    /** Accesso alla dimensione. */
    public float getWidth() {
        return (float)(this.transform.getScaleX() * this.spriteImage.getWidth());
    }

    /** Accesso alla dimensione. */
    public float getHeight() {
        return (float)(this.transform.getScaleY() * this.spriteImage.getHeight());
    }

    public void render(Graphics2D g2d) {
        // Salvo la original:
        AffineTransform original = g2d.getTransform();

        // Applico:
        g2d.drawImage(this.spriteImage, this.transform, null);

        // Restore:
        g2d.setTransform( original );
    }
}
