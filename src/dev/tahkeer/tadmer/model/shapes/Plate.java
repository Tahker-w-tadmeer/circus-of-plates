package dev.tahkeer.tadmer.model.shapes;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Plate extends DefaultShape {

    private final BufferedImage[] vectors = new BufferedImage[1];

    public Plate(int x, int y, Color color) {
        super(x, y, color);

        this.setWidth(80);
        this.setHeight(15);

        this.generateImage();
    }

    private void generateImage() {
        BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(this.getColor());
        g2d.fillPolygon(
                new int[]{ 0, this.getWidth(), this.getWidth()-10, 10 },
                new int[]{ 0, 0, this.getHeight(), this.getHeight() },
                4
        );

        g2d.dispose();
        vectors[0] = image;
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return vectors;
    }
}
