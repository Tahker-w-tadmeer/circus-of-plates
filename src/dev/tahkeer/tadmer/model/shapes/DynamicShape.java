package dev.tahkeer.tadmer.model.shapes;

import dev.tahkeer.tadmer.utils.ColorMapper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.LookupOp;
import java.io.File;
import java.io.IOException;

public class DynamicShape {
    private final BufferedImage image;
    private final Color color;
    private final String type;

    public DynamicShape(Color color, String path) {
        this.color = color;

        File file = new File(path);

        this.type = file.getName();

        image = loadImage(file);
    }

    public Color getColor() {
        return color;
    }

    public String getType() {
        return type;
    }

    public BufferedImage getImage() {
        return image;
    }

    private BufferedImage loadImage(File imageFile) {
        BufferedImage loadedImage;
        try {
            loadedImage = ImageIO.read(imageFile);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }

        BufferedImage shape = new BufferedImage(loadedImage.getWidth(), loadedImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = shape.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        BufferedImageOp lookup = new LookupOp(
                new ColorMapper(new Color(0, 0, 0), getColor()),
                null
        );
        BufferedImage convertedImage = lookup.filter(loadedImage, null);

        g2d.drawImage(convertedImage, 0, 0, null);

        g2d.dispose();

        return shape;
    }
}
