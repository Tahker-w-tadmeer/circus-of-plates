package dev.tahkeer.tadmer.model;
import dev.tahkeer.tadmer.utils.interfaces.Shape;
import eg.edu.alexu.csd.oop.game.GameObject;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class Hand {
    private final ArrayList<Shape> shapes = new ArrayList<>();
    private int x = 0;
    private int y = 0;

    private Integer originalY;

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        if(originalY == null)
        {
            this.y = originalY = y;
            return;
        }

        this.y = originalY - y;
    }

    public int getX() {
        return this.x;
    }

    public int getY()
    {
        return y;
    }

    public boolean shapeLand(Shape shape) {
        if(shapes.size() < 2) {
            shapes.add(shape);

            this.setY(this.heightOfShapes());

            return false;
        }

        int size = shapes.size();
        Shape lastShape = shapes.get(size - 1);
        Shape beforeLastShape = shapes.get(size - 2);

        if(shape.getColor().equals(lastShape.getColor()) && shape.getColor().equals(beforeLastShape.getColor())) {
            shapes.remove(size - 1);
            shapes.remove(size - 2);

            this.setY(this.heightOfShapes());
            return true;
        }

        shapes.add(shape);
        this.setY(this.heightOfShapes());

        return false;
    }

    public void removeShapes(int number) {
        for (int i = 0; i < number; i++) {
            if(shapes.size() == 0) {
                return;
            }

            shapes.remove(shapes.size() - 1);
        }
    }

    public int heightOfShapes() {
        return shapes.stream().mapToInt(GameObject::getHeight).sum();
    }

    public ArrayList<BufferedImage> getSpriteImages() {
        ArrayList<BufferedImage> vectors = new ArrayList<>();
        AtomicReference<Integer> lastHeight = new AtomicReference<>(0);
        shapes.forEach((shape) -> {
            shape.setX(x);
            shape.setY(originalY - lastHeight.get());
            lastHeight.set(lastHeight.get() + shape.getHeight());

            vectors.add(shape.getSpriteImages()[0]);
        });
        return vectors;
    }
}
