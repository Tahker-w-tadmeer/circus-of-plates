package dev.tahkeer.tadmer.model;
import dev.tahkeer.tadmer.model.interfaces.Shape;
import eg.edu.alexu.csd.oop.game.GameObject;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class Hand {
    private final ArrayList<GameObject> shapes = new ArrayList<>();
    private int x = 0;
    private int y = 0;
    public void setX(int x)
    {
        this.x = x;
    }
    public int getY()
    {
        return y;
    }

    public boolean shapeLand(GameObject shapeObject) {
        if(shapes.size() < 2) {
            shapes.add(shapeObject);
            return false;
        }
        Shape shape = (Shape) shapeObject;
        int size = shapes.size();
        Shape lastShape = (Shape) shapes.get(size - 1);
        Shape beforeLastShape = (Shape) shapes.get(size - 2);

        if(shape.getColor().equals(lastShape.getColor()) && shape.getColor().equals(beforeLastShape.getColor())) {
            shapes.remove(size - 1);
            shapes.remove(size - 2);

            return true;
        }
        shapes.add(shapeObject);
        return false;
    }
    public int heightOfShapes() {
        return shapes.stream().mapToInt(GameObject::getHeight).sum();
    }
    public ArrayList<BufferedImage> getSpriteImages() {
        ArrayList<BufferedImage> vectors = new ArrayList<>();
        AtomicReference<Integer> lastHeight = new AtomicReference<>(0);
        shapes.forEach((shape) -> {
            shape.setX(x);
            shape.setY(y + lastHeight.get() + this.getY());
            lastHeight.set(lastHeight.get() + shape.getHeight());

            vectors.add(shape.getSpriteImages()[0]);
        });
        return vectors;
    }
}
