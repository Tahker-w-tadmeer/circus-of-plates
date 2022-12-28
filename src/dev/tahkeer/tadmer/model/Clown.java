package dev.tahkeer.tadmer.model;

import dev.tahkeer.tadmer.model.interfaces.Shape;
import dev.tahkeer.tadmer.model.interfaces.ShapesEventListener;
import eg.edu.alexu.csd.oop.game.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Clown extends DefaultGameObject implements GameObject {
    private final Hand leftHand = new Hand();
    private final Hand rightHand = new Hand();
    private final BufferedImage[] vectors = new BufferedImage[1];
    private Image scaledImage;
    private final int yOfClown;
    private final ArrayList<ShapesEventListener> listeners = new ArrayList<>();

    public Clown(int x, int y) {
        this.setWidth(300);

        this.setX(x);
        yOfClown = y;

        leftHand.setY(this.getHeight());
        rightHand.setY(this.getHeight());

        this.generateImage();

        int widthDivided = getWidth() - getWidth()/19;
        try {
            scaledImage = ImageIO.read(new File("./res/colored_clown.png"))
                    .getScaledInstance(widthDivided, (int) (widthDivided / this.aspectRatio())+50, Image.SCALE_SMOOTH);
        } catch (IOException ignored) {}
    }

    @Override
    public void setX(int x) {
        super.setX(x);

        leftHand.setX(x);
        rightHand.setX(x + 2*(getWidth()*2/ 19)+150 );

    }

    @Override
    public void setY(int y) {}

    public void addShapesListener(ShapesEventListener listener) {
        listeners.add(listener);
    }

    public void removeShapesListener(ShapesEventListener listener) {
        listeners.remove(listener);
    }

    public boolean holds(Shape shape) {
        Point shapePoint = new Point(shape.getX(), shape.getY());

        Hand[] hands = new Hand[]{
                leftHand, rightHand
        };

        for (Hand hand : hands) {
            Point handPoint = new Point(hand.getX(), hand.getY());
            if(Math.abs(handPoint.y - shapePoint.y) < 2
                    && Math.abs(handPoint.x - shapePoint.x) < 40) {

                if(hand.shapeLand(shape)) {
                    listeners.forEach(ShapesEventListener::collected);
                }

                return true;
            }
        }

        return false;
    }
    public int getRealHeight() {
        return getHeight() + Math.max(leftHand.heightOfShapes(), rightHand.heightOfShapes()) ;
    }

    private BufferedImage generateImage() {

        BufferedImage clownImage = new BufferedImage(
                getWidth(),
                getRealHeight(),
                BufferedImage.TYPE_INT_ARGB
        );
        Graphics2D g2d = clownImage.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.drawImage(scaledImage, 0, getRealHeight()-this.getHeight(), null);

        g2d.dispose();

        this.position.y = yOfClown - (getRealHeight() - this.getHeight()/3) - 20;

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
            g2d.drawImage(shapeImage, this.getWidth()-100, lastY, null);
            lastY -= shapeImage.getHeight();
        }

        g2d.dispose();

        vectors[0] = image;

        return vectors;
    }
}
