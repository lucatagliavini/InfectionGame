package com.ltsoft.game.infection.core.graphics;

import com.ltsoft.game.infection.core.game.GameObject;
import com.ltsoft.game.infection.core.game.components.PositionComponent;
import com.ltsoft.game.infection.core.states.GameState;
import com.ltsoft.game.infection.core.utils.Position;
import com.ltsoft.game.infection.core.utils.Size;
import com.ltsoft.game.infection.core.utils.math.MathUtils;
import com.ltsoft.game.infection.core.utils.math.Vector2f;
import com.ltsoft.game.infection.core.world.TiledMap;

import java.awt.*;
import java.util.Optional;

public class Camera {

    protected Position position;
    protected Size windowSize;

    protected Rectangle viewBounds;

    private Optional<GameObject> objectWithFocus;


    public Camera(Size windowSize) {
        this.position = new Position(0.0f, 0.0f);
        this.windowSize = windowSize;
        this.viewBounds = new Rectangle(
            this.position.getX(), this.position.getY(), this.windowSize.getWidth(), this.windowSize.getHeight()
        );
    }

    public void setFocusOnObject(GameObject gameObject) {
        this.objectWithFocus = Optional.of( gameObject );
    }

    public void onUpdate(GameState state, double deltaTime) {
        if( this.objectWithFocus.isPresent() ) {
            Vector2f objectPosition = this.objectWithFocus.get().getPosition();
            Vector2f objectSize = this.objectWithFocus.get().getSize();

            // CENTER:
            float cameraX = objectPosition.x - (this.windowSize.getWidth() - objectSize.x)/2.0f;
            float cameraY = objectPosition.y - (this.windowSize.getHeight() - objectSize.y)/2.0f;

            this.position.setPosition(cameraX, cameraY);
        }

        // Clamp camera nei bounds:
        this.clampWithinBounds( state );
        this.viewBounds.setLocation( this.position.getX(), this.position.getY() );
    }

    private void clampWithinBounds(GameState state) {
        TiledMap tiledMap = state.getTiledMap();
        Vector2f position = this.position.getPosition();

        position.x = MathUtils.clamp( position.x, 0.0f, (float) (tiledMap.getMapWitdh() - this.windowSize.getWidth()));
        position.y = MathUtils.clamp( position.y, 0.0f, (float) (tiledMap.getMapHeight() - this.windowSize.getHeight()));
    }


    /** Metodo generico per stabilire se un rect è in view. */
    public boolean isInView( Rectangle rectangle ) {
        return this.viewBounds.intersects( rectangle );
    }

    /** Check se l'oggetto è in view della camera. */
    public boolean isInView(GameObject gameObject) {
        Rectangle objectRectangle = new Rectangle();
        objectRectangle.setLocation( (int)gameObject.getPosition().x, (int)gameObject.getPosition().y );
        objectRectangle.setSize( (int)gameObject.getSize().x, (int)gameObject.getSize().y );

        return this.isInView( objectRectangle );
    }
}