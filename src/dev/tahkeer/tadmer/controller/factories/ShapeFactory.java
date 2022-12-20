package dev.tahkeer.tadmer.controller.factories;

import dev.tahkeer.tadmer.model.interfaces.Shape;
import dev.tahkeer.tadmer.model.shapes.Bomb;
import dev.tahkeer.tadmer.model.shapes.Plate;

import java.awt.*;
import java.util.Random;

public class ShapeFactory {

    private static final Color[] colors = new Color[]{
            Color.blue,
            Color.cyan,
            Color.yellow,
            Color.orange,
            Color.green,
            Color.red,
            Color.gray,
            Color.magenta,
            Color.pink,
            Color.green,
    };

    private static final String[] shapes = new String[] {
            "plate", "bomb"
    };

    public static Shape generate(int x, int y) {
        return generate(shapes[new Random().nextInt(shapes.length)], x, y);
    }

    public static Shape generate(String type, int x, int y) {
        if("plate".equalsIgnoreCase(type))
            return new Plate(x, y, colors[new Random().nextInt(colors.length)]);

        if("bomb".equalsIgnoreCase(type))
            return new Bomb(x, y, colors[new Random().nextInt(colors.length)]);

        return new Plate(x, y, colors[new Random().nextInt(colors.length)]);
    }

}
