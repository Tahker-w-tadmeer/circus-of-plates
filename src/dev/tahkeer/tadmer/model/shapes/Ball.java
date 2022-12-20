package dev.tahkeer.tadmer.model.shapes;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Ball extends DefaultShape {
    private final BufferedImage[] vectors = new BufferedImage[1];

    public Ball(int x, int y, Color color) {
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

        int x = this.getWidth() / 2;
        int y = this.getWidth() / 2;
        int x1 = (int) ((this.getWidth() / 2.0) - x);
        int y1 = (int) ((this.getWidth() / 2.0) - y);
        int x2 = (int) ((this.getWidth() / 2.0) + x);
        int y2 = (int) ((this.getWidth() / 2.0) + y);

        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(this.getColor());
        g2d.fillOval(x1+3, y1+3, x2-6, y2-6);
        g2d.setColor(Color.WHITE);
        g2d.drawArc(x1+12, y1+8, x2-22, y2-22, 15, 40);

        g2d.dispose();

        vectors[0] = image;
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return vectors;
    }
}
