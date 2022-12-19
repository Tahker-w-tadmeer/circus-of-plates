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
    private final BufferedImage[] vectors = new BufferedImage[1];

    public Clown(int x, int y) {
        this.setWidth(200);

        this.setX(x);
        this.position.y = y - (this.getHeight() - 100);

        leftHand = new Hand();
        rightHand = new Hand();

        this.generateImage();
    }

    @Override
    public void setY(int y) {}

    private void generateImage() {
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        try {
            Image icon = ImageIO.read(new File("./res/clown.png"))
                    .getScaledInstance(getWidth() - getWidth()/3, (getWidth() - getWidth()/3)*619/397, Image.SCALE_FAST);

            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.drawImage(icon, 0, 0, null);

            g2d.dispose();
        } catch (IOException ignored) {}

        vectors[0] = image;
    }

    protected void setWidth(int width) {
        this.width = width;
        this.height = width * 619 / 397;
    }

    protected void setHeight(int height) {
        this.height = height;
        this.width = height * 397 / 619;
    }

    @Override
    public boolean isVisible() {
        return true;
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return vectors;
    }
}
