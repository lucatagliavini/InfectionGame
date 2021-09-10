package com.ltsoft.game.infection.core.graphics.images.spritesheets;

import com.ltsoft.game.infection.core.graphics.images.ImageUtils;
import com.ltsoft.game.infection.core.graphics.images.Sprite;
import com.ltsoft.game.infection.core.utils.math.Vector2f;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/*************************************************************************
 * Specifiche:
 * -----------
 *
 * La classe deve caricare lo spritesheet, avendo la possibilità di dividelo
 * in zone. Le zone sono calcolabili alla sua costruzione, scrivendone il numero.
 *
 */
public class SpriteSheet {

    private String spriteSheetPath;
    private BufferedImage spriteSheet;

    private int numSpriteZonesWidth;
    private int numSpriteZonesHeight;

    private int spriteWidth;
    private int spriteHeight;

    private Map<Integer, Sprite> mSpriteCache;


    /** Costruttore senza alcun resize degli spritesheet. */
    public SpriteSheet(String spriteSheetPath, int numSpriteZonesWidth, int numSpriteZonesHeight) {
        this.spriteSheetPath = spriteSheetPath;
        this.numSpriteZonesWidth = numSpriteZonesWidth;
        this.numSpriteZonesHeight = numSpriteZonesHeight;
        this.mSpriteCache = new HashMap<>();
        this.loadSpriteSheet();
    }


    /** Carico uno spritesheet. */
    private void loadSpriteSheet() {
        this.spriteSheet = ImageUtils.loadImage( this.spriteSheetPath );
        // Dividiamo per ottenere il numero di sprites:
        this.spriteWidth = this.spriteSheet.getWidth() / this.numSpriteZonesWidth;
        this.spriteHeight = this.spriteSheet.getHeight() / this.numSpriteZonesHeight;
    }


    /** Metodo dinamico che recupera una porzione di immagine. */
    public Sprite getSpriteAt(int x, int y) {
        if( x < 0 || x > this.numSpriteZonesWidth || y < 0 || y > this.numSpriteZonesHeight ) {
            System.err.println("SpriteSheet " + this.spriteSheetPath + "> ERROR: Index out of range: " + x + ", " + y);
            return null;
        }

        // Estraiamo la sprite dalla cache o dal foglio:
        int sx = this.spriteWidth * x;
        int sy = this.spriteHeight * y;
        int index = sx + (sy * this.numSpriteZonesWidth);
        Sprite sprite = this.mSpriteCache.get( index );
        if( sprite == null ) {
            BufferedImage spriteImage = this.spriteSheet.getSubimage(sx, sy, this.spriteWidth, this.spriteHeight);
            sprite = new Sprite(spriteImage);
            this.mSpriteCache.put( index, sprite );
        }
        // Restituisco la sprite:
        return sprite;
    }


    /** Recupera la sprite con indice. */
    public Sprite getSpriteAt(int index) {
        int zx = index % this.numSpriteZonesWidth;
        int zy = index / this.numSpriteZonesWidth;
        return this.getSpriteAt( zx, zy );
    }


    // Main di test:
    public static void main(String[] args) {
        try {
            String spriteSheetPath = "/sprites/spritesheets/spritesheet-test.png";
            SpriteSheet spriteSheet = new SpriteSheet(spriteSheetPath, 4, 2);

            JFrame form = new JFrame("SpriteSheet - TESTER");
            form.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
            Container container = form.getContentPane();
            container.setLayout(new FlowLayout());

            SpritePanel spritePanel = new SpritePanel(
                    spriteSheet.getSpriteAt(3),
                    new Vector2f(2.0f, 2.0f)
            );
            container.add( spritePanel );

            form.pack();
            form.setLocationRelativeTo(null);
            form.setVisible(true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class SpritePanel extends JPanel {

        private final Color DEFAULT_COLOR = Color.MAGENTA;
        private final Sprite sprite;
        private Vector2f scale;

        public SpritePanel(Sprite sprite, Vector2f scale) {
            this.sprite = sprite;
            this.scale = scale;

            this.sprite.setScale(this.scale);
            int sx = (int)(this.sprite.getWidth() * this.scale.x);
            int sy = (int)(this.sprite.getHeight() * this.scale.y);
            Dimension dimension = new Dimension(sx, sy);
            this.setPreferredSize( dimension );


        }

        @Override
        public void paintComponent(Graphics g) {
            g.setColor(DEFAULT_COLOR);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            this.sprite.clearTransform();
            this.sprite.setScale(this.scale);
            this.sprite.render( (Graphics2D)g );
        }
    }
}
