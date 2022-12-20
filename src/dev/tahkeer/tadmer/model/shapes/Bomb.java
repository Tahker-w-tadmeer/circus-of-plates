package dev.tahkeer.tadmer.model.shapes;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bomb extends DefaultShape {
    private final BufferedImage[] vectors = new BufferedImage[1];

    public Bomb(int x, int y, Color color) {
        this.setColor(color);

        this.width = 60;
        this.height = 60;

        this.setX(x);
        this.setY(y);

        this.generateImage();
    }

    private void generateImage() {
        BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(this.getColor());
        g2d.fillOval(0, 0, this.getWidth(), this.getHeight());
        g2d.dispose();

        vectors[0] = image;
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return vectors;
    }
}
