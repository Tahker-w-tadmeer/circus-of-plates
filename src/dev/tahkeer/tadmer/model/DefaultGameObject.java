package dev.tahkeer.tadmer.model;

import eg.edu.alexu.csd.oop.game.GameObject;

import java.awt.*;

abstract public class DefaultGameObject implements GameObject {
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return position.x;
    }

    public void setX(int x) {
        position.x = x;
    }

    public int getY() {
        return position.y;
    }

    public void setY(int y) {
        position.y = y;
    }

    public boolean isVisible() {
        return true;
    }

    protected void setWidth(int width) {
        this.width = width;
    }

    protected void setHeight(int height) {
        this.height = height;
    }
}
