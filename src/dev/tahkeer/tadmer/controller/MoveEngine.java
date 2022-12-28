package dev.tahkeer.tadmer.controller;

import dev.tahkeer.tadmer.model.Clown;
import dev.tahkeer.tadmer.model.interfaces.Shape;
import dev.tahkeer.tadmer.model.shapes.Platform;

import java.util.List;

public class MoveEngine {
    protected static void move(List<Shape>shapes, List<Clown>clowns, List<Platform>platforms) {
        for (Shape shape : shapes) {
            boolean shapeMoved = false;

            for (Platform platform : platforms) {
                if (platform.isShapeLeft(shape)) {
                    shape.setX(shape.getX() + 3);
                    shapeMoved = true;
                    break;
                }

                if (platform.isShapeRight(shape)) {
                    shape.setX(shape.getX() - 3);
                    shapeMoved = true;
                    break;
                }
            }

            if (!shapeMoved) {
                shape.fall();
            }
        }
        for (Clown clown : clowns) {
            shapes.removeIf(clown::holds);
        }
    }

}
