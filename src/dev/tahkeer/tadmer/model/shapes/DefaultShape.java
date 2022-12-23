package dev.tahkeer.tadmer.model.shapes;

import dev.tahkeer.tadmer.model.DefaultGameObject;
import dev.tahkeer.tadmer.model.interfaces.Shape;

import java.awt.*;

abstract public class DefaultShape extends DefaultGameObject implements Shape {
    private Color color;

    public DefaultShape(int x, int y, Color color) {
        this.setColor(color);

        this.setX(x);
        this.setY(y);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean isVisible() {
        return true;
    }
}
