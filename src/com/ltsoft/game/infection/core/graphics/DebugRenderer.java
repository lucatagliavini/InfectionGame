package com.ltsoft.game.infection.core.graphics;

import com.ltsoft.game.infection.core.collisions.CollisionBox;
import com.ltsoft.game.infection.core.game.GameObject;
import com.ltsoft.game.infection.core.states.GameState;
import com.ltsoft.game.infection.core.utils.math.Vector2f;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.List;

public class DebugRenderer {

    private AffineTransform transform;

    public DebugRenderer() {
        this.transform = new AffineTransform();
    }

    public void onRender(GameState gameState, Graphics2D g2d) {
        AffineTransform original = g2d.getTransform();
        Camera camera = gameState.getCamera();
        Vector2f cameraPosition = camera.position.getPosition();
        // Da qua in poi siamo in trasformazione:
        this.transform.setToIdentity();
        this.transform.translate( -cameraPosition.x, -cameraPosition.y );
        g2d.transform( this.transform );

        // Rendering degli oggetti:
        List<GameObject> gameObjectList = gameState.getGameObjects();
        gameObjectList.stream()
            // Filtriamo quelli visibili:
            .filter(camera::isInView)
            // Disegnamo gli oggetti:
            .map(GameObject::getBox)
            .forEach(collisionBox -> this.onRenderCollisionBox( collisionBox, g2d ));

        // Restore:
        g2d.setTransform( original );
    }


    private void onRenderCollisionBox(CollisionBox collisionBox, Graphics2D g2d) {
        g2d.setColor(Color.ORANGE);
        Rectangle rect = collisionBox.getBounds();
        g2d.drawRect( rect.x, rect.y, rect.width, rect.height );
    }
}
