package dev.tahkeer.tadmer.controller;

import dev.tahkeer.tadmer.controller.factories.ShapeFactory;
import dev.tahkeer.tadmer.model.interfaces.Shape;
import dev.tahkeer.tadmer.model.shapes.Platform;

import java.util.List;
import java.util.Random;

public class GenerateEngine {
    private static boolean shouldGenerateShape(int percentage) {
        return new Random().nextInt(100 / percentage) == 0;
    }
    protected static void generate(List<Shape>shapes, List<Platform>platforms,Game game) {
        for (Platform platform : platforms) {
            Shape shape;

            if (shouldGenerateShape(30)) {
                shape = ShapeFactory.generate(platform.getX(), platform.getY());
                shape.setY(shape.getY() - shape.getHeight());
                shapes.add(shape);
            }

            if (shouldGenerateShape(40)) {
                shape = ShapeFactory.generate(platform.getX() + game.getWidth() - 100, platform.getY());
                shape.setY(shape.getY() - shape.getHeight());
                shapes.add(shape);
            }
        }
    }
}
