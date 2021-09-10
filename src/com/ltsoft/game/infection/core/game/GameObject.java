package com.ltsoft.game.infection.core.game;

import com.ltsoft.game.infection.core.collisions.CollisionBox;
import com.ltsoft.game.infection.core.game.components.PositionComponent;
import com.ltsoft.game.infection.core.game.components.SizeComponent;
import com.ltsoft.game.infection.core.states.GameState;
import com.ltsoft.game.infection.core.utils.Position;
import com.ltsoft.game.infection.core.utils.Size;
import com.ltsoft.game.infection.core.utils.math.Vector2f;

import java.awt.image.BufferedImage;

public abstract class GameObject extends Entity {

    protected CollisionBox collisionBox;
    protected Vector2f collisionBoxOffset;
    protected Vector2f collisionBoxSize;

    public GameObject(String mObjectName) {
        super(mObjectName);

        this.addComponent(new PositionComponent(50.0f, 50.0f));
        this.addComponent(new SizeComponent(64.0f, 64.0f));

        this.collisionBoxOffset = new Vector2f( 0.0f, 0.0f );
        this.collisionBox = new CollisionBox();

        this.collisionBoxSize = new Vector2f( 16f, 24f );
        this.collisionBoxOffset.set( 24.0f, 32.0f );
        this.collisionBox.setSize( this.collisionBoxSize );

        // Controllo compatibilità:
        Vector2f position = this.getComponent(PositionComponent.class).getPosition();
        this.collisionBox.setLocation( position );
    }

    public abstract void onUpdate(GameState gameState, float deltaTime);

    public abstract BufferedImage getSprite();

    /** TODO: Capire se questo va sempre bene o meno, sempre prima posizione
     * poi update collision box, o questa cosa non va bene ?
     */
    public void setPosition(Vector2f position) {
        PositionComponent positionComp = this.getComponent(PositionComponent.class);
        positionComp.setPosition( position );

        Vector2f currentBoxPosition = new Vector2f( position );
        currentBoxPosition = currentBoxPosition.add( this.collisionBoxOffset );
        this.collisionBox.setLocation( currentBoxPosition );
    }

    public Vector2f getPosition() {
        return this.getComponent(PositionComponent.class).getPosition();
    }

    public Vector2f getSize() {
        return this.getComponent(SizeComponent.class).getSize();
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
