package dev.tahkeer.tadmer.model;

import eg.edu.alexu.csd.oop.game.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Clown implements GameObject {
    private Hand leftHand;
    private Hand rightHand;

    private final Point position = new Point();

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
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public boolean isVisible() {
        return false;
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return new BufferedImage[0];
    }
}
