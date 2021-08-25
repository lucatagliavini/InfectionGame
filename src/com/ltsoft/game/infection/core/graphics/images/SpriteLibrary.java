package com.ltsoft.game.infection.core.graphics.images;

import com.ltsoft.game.infection.core.world.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SpriteLibrary {

    private static final String PATH_TO_UNIT = "/sprites/units";
    private static final String PATH_TO_TILES = "/sprites/tiles";

    private static SpriteLibrary instance = new SpriteLibrary();

    private Map<String, SpriteSet> units;
    private Map<String, BufferedImage> tiles;

    public static void loadSprites() {
        if (SpriteLibrary.instance == null ) {
            SpriteLibrary.instance = new SpriteLibrary();
        }
        // Carica i personaggi:
        SpriteLibrary.instance.loadUnitsFromDisk();
        SpriteLibrary.instance.loadTilesFromDisk();
    }

    private SpriteLibrary() {
        this.units = new HashMap<>();
        this.tiles = new HashMap<>();
    }

    public static SpriteSet getUnit(String unitName) {
        return SpriteLibrary.instance.units.get( unitName );
    }

    public static BufferedImage getTileSprite(String tileName) {
        return SpriteLibrary.instance.tiles.get( tileName );
    }

    private void loadTilesFromDisk() {
        String pathToFolder = PATH_TO_TILES;
        String[] imagesInFolder = this.getImagesInFolder( pathToFolder );

        for( String imageName : imagesInFolder ) {
            this.tiles.put(
                    imageName.substring(0, imageName.lastIndexOf(".")),
                    ImageUtils.loadImage( pathToFolder + "/" + imageName )
            );
        }
    }


    private void loadUnitsFromDisk() {
        // Cerchiamo tutti gli units:
        String[] folderNames = this.getFolderNames( PATH_TO_UNIT );
        for( String folderName : folderNames ) {
            SpriteSet spriteSet = new SpriteSet();
            String pathToFolder = PATH_TO_UNIT + "/" + folderName;
            String[] imagesInFolder = this.getImagesInFolder( pathToFolder );

            for( String imageName : imagesInFolder ) {
                spriteSet.addSheet(
                        imageName.substring(0, imageName.lastIndexOf(".")),
                        ImageUtils.loadImage( pathToFolder + "/" + imageName )
                );
            }

            // Aggiungo alla units:
            this.units.put( folderName, spriteSet );
        }
    }

    private String[] getFolderNames(String basePath) {
        URL resoruce = SpriteLibrary.class.getResource( basePath );
        File file = new File( resoruce.getFile() );
        // Filtriamo per directory:
        return file.list((current, name) -> new File(current, name).isDirectory());
    }

    private String[] getImagesInFolder(String basePath) {
        URL resoruce = SpriteLibrary.class.getResource( basePath );
        File file = new File( resoruce.getFile() );
        // Filtriamo per directory:
        return file.list((current, name) -> new File(current, name).isFile());
    }
}
