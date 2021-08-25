package com.ltsoft.game.infection.core.world;

import com.ltsoft.game.infection.core.utils.Size;

public class TiledMap {

    private int numTilesWidth;
    private int numTilesHeight;
    private Tile[] tiles;


    public TiledMap(Size mapSize) {
        this.numTilesWidth = mapSize.getWidth();
        this.numTilesHeight = mapSize.getHeight();

        this.tiles = new Tile[this.numTilesWidth * this.numTilesHeight];
        this.initializeTiles();
    }

    private void initializeTiles() {
        for(int i=0; i<this.tiles.length; i++ ) {
            this.tiles[i] = new Tile();
        }
    }

    public int getNumTilesWidth() {
        return this.numTilesWidth;
    }

    public int getNumTilesHeight() {
        return this.numTilesHeight;
    }

    public double getMapWitdh() {
        return this.numTilesWidth * Tile.TILE_SIZE;
    }

    public double getMapHeight() {
        return this.numTilesHeight * Tile.TILE_SIZE;
    }

    public Tile[] getTiles() {
        return this.tiles;
    }
}
