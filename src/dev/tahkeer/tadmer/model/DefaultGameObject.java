package dev.tahkeer.tadmer.model;

import dev.tahkeer.tadmer.model.interfaces.Shape;

import java.awt.*;
import java.awt.image.BufferedImage;

abstract public class DefaultGameObject implements Shape {
    private Color color;
    protected int width;
    protected int height;
    protected final Point position = new Point();

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getX() {
        return position.x;
    }

    @Override
    public void setX(int x) {
        position.x = x;
    }

    @Override
    public int getY() {
        return position.y;
    }

    @Override
    public void setY(int y) {
        position.y = y;
    }

    @Override
    public boolean isVisible() {
        return true;
    }

    protected void setWidth(int width) {
        this.width = width;
    }

    protected void setHeight(int height) {
        this.height = height;
    }

    @Override
    abstract public BufferedImage[] getSpriteImages();
}
