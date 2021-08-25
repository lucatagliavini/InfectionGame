package com.ltsoft.game.infection.core.game;

import com.ltsoft.game.infection.core.collisions.CollisionBox;
import com.ltsoft.game.infection.core.states.GameState;
import com.ltsoft.game.infection.core.utils.Position;
import com.ltsoft.game.infection.core.utils.Size;

import java.awt.image.BufferedImage;

public abstract class GameObject {

    protected Position position;
    protected Size size;

    protected CollisionBox collisionBox;
    protected Position collisionBoxOffset;
    protected Size collisionBoxSize;

    public GameObject() {
        this.position = new Position(50.0, 50.0);
        this.size = new Size(64.0, 64.0);

        this.collisionBoxOffset = new Position( 0.0, 0.0 );
        this.collisionBox = new CollisionBox();

        this.collisionBoxSize = new Size( 16, 24 );
        this.collisionBoxOffset.setPosition( 24.0, 32.0 );
        this.collisionBox.setSize( this.collisionBoxSize );

        this.collisionBox.setLocation( this.position );
    }

    public abstract void onUpdate(GameState gameState, double deltaTime);

    public abstract BufferedImage getSprite();

    /** TODO: Capire se questo va sempre bene o meno, sempre prima posizione
     * poi update collision box, o questa cosa non va bene ?
     */
    public void setPosition(Position position) {
        this.position = position;

        Position currentBoxPosition = new Position( this.position );
        currentBoxPosition.addOffset( this.collisionBoxOffset.getPosition() );
        this.collisionBox.setLocation( currentBoxPosition );
    }

    public Position getPosition() {
        return this.position;
    }

    public Size getSize() {
        return this.size;
    }

    public boolean collidingWith(GameObject otherObject) {
        return this.collisionBox.collides( otherObject.getCollisionBox() );
    }

    public CollisionBox getBox() {
        return this.collisionBox;
    }

    public CollisionBox getCollisionBox() {
        return this.getBox();
    }
}
