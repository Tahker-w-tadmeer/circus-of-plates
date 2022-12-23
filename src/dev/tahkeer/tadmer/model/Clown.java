package dev.tahkeer.tadmer.model;

import eg.edu.alexu.csd.oop.game.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Clown extends DefaultGameObject implements GameObject {
    private final Hand leftHand = new Hand();
    private final Hand rightHand = new Hand();
    private final BufferedImage[] vectors = new BufferedImage[1];

    public Clown(int x, int y) {
        this.setWidth(350);

        this.setX(x);
        this.position.y = y - (this.getHeight() - this.getHeight()/3);

        this.generateImage();
    }

    @Override
    public void setX(int x) {
        super.setX(x);

        leftHand.setX(x);
        rightHand.setX(x + this.getWidth());
    }

    @Override
    public void setY(int y) {}

    public boolean addToLeftHand(GameObject shape) {
        return leftHand.shapeLand(shape);
    }

    public boolean addToRightHand(GameObject shape) {
        return rightHand.shapeLand(shape);
    }

    private void generateImage() {
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        try {
            int widthDivided = getWidth() - getWidth()/3;
            Image icon = ImageIO.read(new File("./res/colored_clown.png"))
                    .getScaledInstance(widthDivided, widthDivided * this.aspectRatio(), Image.SCALE_FAST);

            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.drawImage(icon, 0, 0, null);

            for (BufferedImage shapeImage : leftHand.getSpriteImages()) {
                g2d.drawImage(shapeImage,0, 0, null);
            }

            for (BufferedImage shapeImage : rightHand.getSpriteImages()) {
                g2d.drawImage(shapeImage, 0, 0, null);
            }

            g2d.dispose();
        } catch (IOException ignored) {}

        vectors[0] = image;
    }

    protected void setWidth(int width) {
        this.width = width;
        this.height = width * this.aspectRatio();
    }

    protected void setHeight(int height) {
        this.height = height;
        this.width = height * (1 / this.aspectRatio());
    }

    private int aspectRatio() {
        return 743 / 561;
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
