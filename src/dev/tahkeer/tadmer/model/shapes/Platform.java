package dev.tahkeer.tadmer.model.shapes;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Platform extends DefaultShape {

    private final BufferedImage[] vectors = new BufferedImage[1];

    public Platform(int x, int y, int width, Color color) {
        super(x, y, color);

        this.setWidth(width);

       this.generateImage();
    }

    public int getStartX() {
        int x = this.getX();

        if(x > 0) {
            x += this.getWidth();
        }

        return x;
    }

    private void generateImage() {
        BufferedImage image = new BufferedImage(this.getWidth(), 10, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setStroke(new BasicStroke(10));
        g2d.setColor(this.getColor());
        g2d.drawLine(0, 0, this.width, 0);

        g2d.dispose();
        vectors[0] = image;

    }
    @Override
    public BufferedImage[] getSpriteImages() {
    return vectors;
    }
    @Override
    public boolean isVisible() {
        return true;
    }
}
