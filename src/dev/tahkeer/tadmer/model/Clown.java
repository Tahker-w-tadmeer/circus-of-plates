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
    private Image scaledImage;

    private final int yOfClown;

    public Clown(int x, int y) {
        this.setWidth(300);

        this.setX(x);
        yOfClown = y;

        leftHand.setY(this.getHeight());
        rightHand.setY(this.getHeight());

        this.generateImage();

        int widthDivided = getWidth() - getWidth()/3;
        try {
            scaledImage = ImageIO.read(new File("./res/colored_clown.png"))
                    .getScaledInstance(widthDivided, (int) (widthDivided * this.aspectRatio()), Image.SCALE_SMOOTH);
        } catch (IOException ignored) {}
    }

    @Override
    public void setX(int x) {
        super.setX(x);

        leftHand.setX(x);
        rightHand.setX(x + (getWidth() / 3) + 20);
    }

    @Override
    public void setY(int y) {}

    public Hand getContainsHand(GameObject shape) {
        Point shapePoint = new Point(shape.getX(), shape.getY());

        Point leftHandPoint = new Point(leftHand.getX(), leftHand.getY());
        if(Math.abs(leftHandPoint.y - shapePoint.y) < 5
                && Math.abs(leftHandPoint.x - shapePoint.x) < 50) {
            return leftHand;
        }

        Point rightHandPoint = new Point(rightHand.getX(), rightHand.getY());
        if(Math.abs(rightHandPoint.y - shapePoint.y) < 5
                && Math.abs(rightHandPoint.x - shapePoint.x) < 50) {
            return rightHand;
        }

        return null;
    }
    public int getrealHeight() {
        return getHeight() + Math.max(leftHand.heightOfShapes(), rightHand.heightOfShapes()) ;
    }

    private BufferedImage generateImage() {

        BufferedImage clownImage = new BufferedImage(
                getWidth()*2/3,
                getrealHeight(),
                BufferedImage.TYPE_INT_ARGB
        );
        Graphics2D g2d = clownImage.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.drawImage(scaledImage, 0, getrealHeight()-this.getHeight(), null);

        g2d.dispose();

        this.position.y = yOfClown - (getrealHeight() - this.getHeight()/3) - 20;

        return clownImage;
    }

    protected void setWidth(int width) {
        this.width = width;
        this.height = (int) (width * this.aspectRatio());
    }

    protected void setHeight(int height) {
        this.height = height;
        this.width = (int) (height / this.aspectRatio());
    }

    private double aspectRatio() {
        return 743 / 561D;
    }

    @Override
    public boolean isVisible() {
        return true;
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        BufferedImage image = generateImage();

        Graphics2D g2d = image.createGraphics();

        final int biggestY = Math.max(leftHand.heightOfShapes(), rightHand.heightOfShapes());

        int lastY = biggestY;
        for (BufferedImage shapeImage : leftHand.getSpriteImages()) {
            g2d.drawImage(shapeImage, 0, lastY, null);
            lastY -= shapeImage.getHeight();
        }

        lastY = biggestY;
        for (BufferedImage shapeImage : rightHand.getSpriteImages()) {
            g2d.drawImage(shapeImage, this.getWidth()-180, lastY, null);
            lastY -= shapeImage.getHeight();
        }

        g2d.dispose();

        vectors[0] = image;

        return vectors;
    }
}
