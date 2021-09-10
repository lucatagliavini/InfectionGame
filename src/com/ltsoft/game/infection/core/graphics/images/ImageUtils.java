package com.ltsoft.game.infection.core.graphics.images;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ImageUtils {

    /** Estraggo la graphics configuration. */
    private static final GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment()
            .getDefaultScreenDevice().getDefaultConfiguration();

    /** Creiamo una immagine performante da renderizzare. */
    public static BufferedImage createCompatibleImage(int width, int height, int transparency) {
        return gc.createCompatibleImage(width, height, transparency);
    }

    /** Caricamento immagine da disco, compatibile con la configurazione. */
    public static BufferedImage loadImage(String filePath) {
        try {
            // Carico l'immagine:
            BufferedImage loaded = ImageIO.read(Objects.requireNonNull(ImageUtils.class.getResource(filePath)));

            // Converto immagine in formato noto:
            BufferedImage converted = ImageUtils.createCompatibleImage(
                    loaded.getWidth(), loaded.getHeight(), loaded.getTransparency());
            Graphics2D g2d = converted.createGraphics();
            g2d.drawImage(loaded, 0, 0, null);
            g2d.dispose();
            loaded.flush();

            return converted;
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }
}
