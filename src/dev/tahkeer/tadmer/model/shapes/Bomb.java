package dev.tahkeer.tadmer.model.shapes;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bomb extends DefaultShape {
    private final BufferedImage[] vectors = new BufferedImage[1];

    public Bomb(int x, int y, BufferedImage image) {
        super(x, y, Color.black);

        vectors[0] = image;
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
