package dev.tahkeer.tadmer.utils.factories;

import dev.tahkeer.tadmer.utils.interfaces.Shape;
import dev.tahkeer.tadmer.model.shapes.Bomb;
import dev.tahkeer.tadmer.model.shapes.Plate;
import dev.tahkeer.tadmer.model.shapes.Bullet;

import java.awt.*;
import java.util.HashMap;
import java.util.Random;

public class ShapeFactory  {
    private static final Color[] colors = new Color[]{
            Color.blue,
            Color.orange,
            Color.green,
            Color.red,
    };

    private static final HashMap<String, Integer> shapes = new HashMap<>() {{
        put("bullet", 50);
        put("plate", 50);
        put("bomb", 10);
    }};

    public static Shape generate(int x, int y) {
        for (String key : shapes.keySet()) {
            if (new Random().nextInt(100 / shapes.get(key)) == 0) {
                return generate(key, x, y);
            }
        }

        return generate(x, y);
    }

    public static Shape generate(String type, int x, int y) {
        if("plate".equalsIgnoreCase(type))
            return new Plate(x, y, colors[new Random().nextInt(colors.length)]);

        if("bullet".equalsIgnoreCase(type))
            return new Bullet(x, y, colors[new Random().nextInt(colors.length)]);

        if("bomb".equalsIgnoreCase(type))
            return new Bomb(x, y);

        return new Plate(x, y, colors[new Random().nextInt(colors.length)]);
    }

}
