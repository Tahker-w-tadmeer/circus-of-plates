package dev.tahkeer.tadmer.model.shapes;

import eg.edu.alexu.csd.oop.game.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Platform extends DefaultShape {

    private final BufferedImage[] vectors = new BufferedImage[1];

    int fullWidth;

    public Platform(int fullWidth, int y, int width, Color color) {
        super(0, y, color);

        this.fullWidth = fullWidth;

        this.setWidth(width);

       this.generateImage();
    }

    public boolean isShapeOnTop(GameObject shape) {
        return Math.abs(shape.getY() - this.getY()) < 30;
    }


    public boolean isShapeLeft(GameObject shape) {
        if(!isShapeOnTop(shape)) return false;

        int startX = 0;
        int endX = this.getWidth();

        return shape.getX() >= startX && shape.getX() <= endX;
    }

    public boolean isShapeRight(GameObject shape) {
        if(!isShapeOnTop(shape)) return false;

        int startX = fullWidth;
        int endX = fullWidth - this.getWidth() - 80;

        return shape.getX() <= startX && shape.getX() >= endX;
    }

    private void generateImage() {
        BufferedImage image = new BufferedImage(this.fullWidth, 10, BufferedImage.TYPE_INT_ARGB);


        Graphics2D g2d = image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setStroke(new BasicStroke(10));
        g2d.setColor(this.getColor());

        g2d.drawLine(0, 0, this.getWidth(), 0);

        g2d.drawLine(fullWidth, 0, fullWidth-this.getWidth(), 0);

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
