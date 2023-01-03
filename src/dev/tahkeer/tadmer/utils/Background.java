package dev.tahkeer.tadmer.utils;

import dev.tahkeer.tadmer.model.DefaultGameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Background extends DefaultGameObject {
    private final static BufferedImage[] vectors = new BufferedImage[1];

    static {
        try {
            BufferedImage image = new BufferedImage(1280, 720, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = image.createGraphics();

            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
            g2d.drawImage(
                    ImageIO.read(new File("./res/Background.jpg")),
                    0, 0, null
            );
            g2d.dispose();

            vectors[0] = image;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return vectors;
    }
}
