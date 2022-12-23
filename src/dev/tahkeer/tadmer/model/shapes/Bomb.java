package dev.tahkeer.tadmer.model.shapes;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bomb extends DefaultShape {
    private final BufferedImage[] vectors = new BufferedImage[1];

    public Bomb(int x, int y, Color color) {
        super(x, y, color);

        this.setWidth(60);
        this.setHeight(60);
        
        this.generateImage();
    }

    private void generateImage() {
        BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        try {
            Image icon = ImageIO.read(new File("./res/bomb.png"))
                    .getScaledInstance(60,60, Image.SCALE_FAST);

            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.drawImage(icon,0, 0, null);
            g2d.dispose();
        } catch (IOException ignored) {}

        vectors[0] = image;
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return vectors;
    }
}
