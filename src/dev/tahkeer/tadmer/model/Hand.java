package dev.tahkeer.tadmer.model;

import dev.tahkeer.tadmer.model.interfaces.Shape;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Shape> shapes;

    private int x = 0;
    private int y = 0;

    public int getY()
    {
        return y;
    }

    public boolean shapeLand(Shape shape) {
        y += shape.getHeight();

        if(false) { // TODO if 2 previous shapes are NOT the same color
            return false;
        }

        // TODO if 2 previous shapes ARE the same color

        return true;
    }
}
