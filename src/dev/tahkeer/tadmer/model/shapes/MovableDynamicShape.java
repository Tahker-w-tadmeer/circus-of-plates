package dev.tahkeer.tadmer.model.shapes;

import dev.tahkeer.tadmer.utils.interfaces.Shape;

import java.awt.image.BufferedImage;

public class MovableDynamicShape extends DefaultShape implements Shape {
    private final BufferedImage[] vectors = new BufferedImage[1];

    public MovableDynamicShape(int x, int y, DynamicShape shape) {
        super(x, y, shape.getColor());

        vectors[0] = shape.getImage();
    }

    @Override
    public int getWidth() {
        return vectors[0].getWidth();
    }

    @Override
    public int getHeight() {
        return vectors[0].getHeight();
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return vectors;
    }
}
