package dev.tahkeer.tadmer.model.shapes;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bomb extends DefaultShape {
    private final static BufferedImage[] vectors = new BufferedImage[1];
    private static BufferedImage image;

    public Bomb(int x, int y) {
        super(x, y, Color.black);

        this.setWidth(60);
        this.setHeight(60);

        if(image == null) {
            vectors[0] = image = this.generateImage();
        }
    }

    private BufferedImage generateImage() {
        BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        try {
            Image icon = ImageIO.read(new File("./res/bomb.png"))
                    .getScaledInstance(60,60, Image.SCALE_SMOOTH);

            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.drawImage(icon,0, 0, null);
            g2d.dispose();
        } catch (IOException ignored) {}

        return image;
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return vectors;
    }
}
