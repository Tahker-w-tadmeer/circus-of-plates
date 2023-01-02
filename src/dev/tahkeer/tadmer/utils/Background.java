package dev.tahkeer.tadmer.utils;

import dev.tahkeer.tadmer.model.DefaultGameObject;
import eg.edu.alexu.csd.oop.game.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Background extends DefaultGameObject {
    private final static BufferedImage[] vectors = new BufferedImage[1];
    public Background(){
        vectors[0]=this.generateImage();
    }
    private BufferedImage generateImage() {
        BufferedImage image = new BufferedImage(1280,720, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        try {
            Image icon = ImageIO.read(new File("./res/Backgroung2.jpg"))
                    .getScaledInstance(1280,720, Image.SCALE_DEFAULT);

            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.drawImage(icon,0, 0, null);
            g2d.dispose();
        } catch (IOException ignored) {}

        return image;
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return vectors;
    }
}
