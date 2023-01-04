package dev.tahkeer.tadmer.utils.factories;

import dev.tahkeer.tadmer.model.shapes.*;
import dev.tahkeer.tadmer.utils.Images;
import dev.tahkeer.tadmer.utils.interfaces.Shape;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class ShapeFactory {
    private final List<DynamicShape> dynamicShapes = new ArrayList<>();
    private static String SHAPE_PATH;

    private final Color[] colors = new Color[] {
            Color.blue,
            Color.orange,
            Color.green,
            Color.red,
    };

    private final HashMap<String, Integer> shapes = new HashMap<>() {{
        put("bomb", 10);
    }};

    private static ShapeFactory instance;

    public static ShapeFactory getInstance() {
        return instance;
    }

    public static ShapeFactory getInstance(String path) {
        if(instance == null) {
            instance = new ShapeFactory(path);
        }

        return instance;
    }

    private ShapeFactory(String path) {
        File folder = new File(path);

        SHAPE_PATH = folder.getAbsolutePath();

        File[] shapeFiles = folder.listFiles();
        if(shapeFiles == null) {
            return;
        }

        for (File shape : shapeFiles) {
            if (shape.isFile()) {
                shapes.put(shape.getName(), 50);
            }
        }
    }

    public Shape generate(int x, int y) {
        for (String key : shapes.keySet()) {
            if (new Random().nextInt(100 / shapes.get(key)) == 0) {
                return generate(key, x, y);
            }
        }

        return generate(x, y);
    }

    public Shape generate(String type, int x, int y) {
        Color color = colors[new Random().nextInt(colors.length)];

        if("bomb".equalsIgnoreCase(type)) {
            return new Bomb(x, y, Images.getBombImage());
        }

        for (DynamicShape dynamicShape : dynamicShapes) {
            if(dynamicShape.getType().equalsIgnoreCase(type) && color.equals(dynamicShape.getColor())) {
                return new MovableDynamicShape(x, y, dynamicShape);
            }
        }

        if(shapes.containsKey(type) && type.endsWith(".png")) {
            DynamicShape dynamicShape = new DynamicShape(color, SHAPE_PATH + "/" + type);
            dynamicShapes.add(dynamicShape);

            return new MovableDynamicShape(x, y, dynamicShape);
        }

        return generate((String) shapes.keySet().toArray()[0], x, y);
    }
}
