package com.ltsoft.game.infection.core.graphics.images;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class SpriteSet {

    private Map<String, BufferedImage> animationSheet;

    public SpriteSet() {
        this.animationSheet = new HashMap<>();
    }

    public void addSheet(String spriteName, BufferedImage animationSheet) {
        this.animationSheet.put( spriteName, animationSheet );
    }

    public BufferedImage getSheet(String spriteName) {
        return this.animationSheet.get( spriteName );
    }
}
