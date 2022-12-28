package dev.tahkeer.tadmer.model.shapes;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet extends DefaultShape {
    private final BufferedImage[] vectors = new BufferedImage[1];

    public Bullet(int x, int y, Color color) {
        super(x, y, color);

        this.setWidth(60);
        this.setHeight(10);

        this.generateImage();
    }

    public void fall() {
        this.setY(this.getY() + 2);
    }

    private void generateImage() {
        BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(this.getColor());
        g2d.fillOval(0, 0, this.getWidth(), this.getHeight());
        g2d.fillRoundRect(this.getWidth()/2, 0, this.getWidth()/2, this.getHeight(), 5, 10);

        g2d.dispose();

        vectors[0] = image;
    }
    @Override
    public BufferedImage[] getSpriteImages() {
        return vectors;
    }
}
