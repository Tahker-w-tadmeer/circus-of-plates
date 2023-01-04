package dev.tahkeer.tadmer.controller;

import dev.tahkeer.tadmer.utils.factories.ShapeFactory;
import dev.tahkeer.tadmer.utils.interfaces.Shape;
import dev.tahkeer.tadmer.model.shapes.Platform;

import java.util.Random;

public class GenerateController {

    private static boolean shouldGenerateShape(int percentage) {
        return new Random().nextInt(100 / percentage) == 0;
    }

    protected static void generate(Game game) {
        ShapeFactory shapeFactory = ShapeFactory.getInstance();

        for (Platform platform : game.platforms) {
            Shape shape;
            if (shouldGenerateShape(30)) {
                shape = shapeFactory.generate(platform.getX(), platform.getY());
                shape.setY(shape.getY() - shape.getHeight());
                game.shapes.add(shape);
            }

            if (shouldGenerateShape(40)) {
                shape = shapeFactory.generate(platform.getX() + game.getWidth() - 100, platform.getY());
                shape.setY(shape.getY() - shape.getHeight());
                game.shapes.add(shape);
            }
        }
    }
}
