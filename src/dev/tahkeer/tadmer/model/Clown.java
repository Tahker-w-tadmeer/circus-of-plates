package dev.tahkeer.tadmer.model;

import eg.edu.alexu.csd.oop.game.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Clown extends DefaultGameObject implements GameObject {
    private final Hand leftHand;
    private final Hand rightHand;

    public Clown() {
        this.width = 60;
        this.height = 120;

        leftHand = new Hand();
        rightHand = new Hand();
    }

    @Override
    public boolean isVisible() {
        return true;
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        try {
            Image icon = ImageIO.read(new File(new File(".").getCanonicalFile() + "/res/clown.png"));

            g2d.drawImage(icon, 0, 0, null);
        } catch (IOException ignored) {}

        return new BufferedImage[]{
                image
        };
    }
}
