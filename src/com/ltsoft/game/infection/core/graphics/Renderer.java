package com.ltsoft.game.infection.core.graphics;

import com.ltsoft.game.infection.core.game.GameObject;
import com.ltsoft.game.infection.core.states.GameState;
import com.ltsoft.game.infection.core.utils.math.Vector2f;
import com.ltsoft.game.infection.core.world.Tile;
import com.ltsoft.game.infection.core.world.TiledMap;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.List;

public class Renderer {

    protected AffineTransform transform;

    public Renderer() {
        this.transform = new AffineTransform();
    }


    private void onRenderMap(TiledMap tiledMap, Camera camera, Graphics2D g2d) {
        if( tiledMap == null ) return;
        Tile[] tiles = tiledMap.getTiles();

        // Stabiliamo se la tile e' visibile:
        Rectangle tileRect = new Rectangle();
        tileRect.setSize( Tile.TILE_SIZE, Tile.TILE_SIZE );
        for(int tx=0; tx<tiledMap.getNumTilesWidth(); tx++) {
            for(int ty=0; ty<tiledMap.getNumTilesHeight(); ty++) {
                int index = tx + (ty * tiledMap.getNumTilesWidth());
                BufferedImage tileSprite = tiles[index].getSprite();

                // Disegno solo se visibile:
                int posX = tx * Tile.TILE_SIZE;
                int posY = ty * Tile.TILE_SIZE;
                tileRect.setLocation( posX, posY );
                if( camera.isInView( tileRect ) ) {
                    g2d.drawImage(tileSprite, posX, posY, null);
                }
            }
        }
    }

    public void onRender(GameState gameState, Graphics2D g2d) {
        AffineTransform original = g2d.getTransform();
        Camera camera = gameState.getCamera();
        Vector2f cameraPosition = camera.position.getPosition();
        // Da qua in poi siamo in trasformazione:
        this.transform.setToIdentity();
        this.transform.translate( -cameraPosition.x, -cameraPosition.y );
        g2d.transform( this.transform );

        // Disegno la mappa:
        this.onRenderMap(gameState.getTiledMap(), camera, g2d);

        // Rendering degli oggetti:
        List<GameObject> gameObjectList = gameState.getGameObjects();
        gameObjectList.stream()
                // Filtriamo quelli visibili:
                .filter( gameObject -> camera.isInView(gameObject))
                // Disegnamo gli oggetti:
                .forEach(
    gameObject -> g2d.drawImage( gameObject.getSprite(), gameObject.getPosition().getX(), gameObject.getPosition().getY(), null )
        );

        // Restore:
        g2d.setTransform( original );
    }
}
