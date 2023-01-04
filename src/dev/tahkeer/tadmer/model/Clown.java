package dev.tahkeer.tadmer.model;

import dev.tahkeer.tadmer.utils.interfaces.Shape;
import dev.tahkeer.tadmer.utils.interfaces.ShapesEventListener;
import dev.tahkeer.tadmer.model.shapes.Bomb;
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
    private final BufferedImage image;
    private final int yOfClown;
    private final ArrayList<ShapesEventListener> listeners = new ArrayList<>();

    public Clown(int x, int y) {
        try {
            image = ImageIO.read(new File("./res/colored_clown.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.setX(x);
        yOfClown = y;

        leftHand.setY(this.getHeight());
        rightHand.setY(this.getHeight());
    }

    public int getHighestY() {
        return Math.min(leftHand.getY(), rightHand.getY());
    }

    @Override
    public void setX(int x) {
        super.setX(x);

        leftHand.setX(x);
        rightHand.setX(x + this.getWidth() - 50);
    }

    @Override
    public void setY(int y) {}

    public void addShapesListener(ShapesEventListener listener) {
        listeners.add(listener);
    }

    public boolean holds(Shape shape) {
        Point shapePoint = new Point(shape.getX(), shape.getY());

        Hand[] hands = new Hand[]{
                leftHand, rightHand
        };

        for (Hand hand : hands) {
            if(shapePoint.y - hand.getY() <= 4 && shapePoint.y - hand.getY() >= 0
                    && Math.abs(hand.getX() - shapePoint.x) <= 40) {

                if(shape instanceof Bomb) {
                    listeners.forEach(ShapesEventListener::bombCaught);

                    hand.removeShapes(2);

                    return true;
                }

                if(hand.shapeLand(shape)) {
                    listeners.forEach(ShapesEventListener::collected);
                }

                return true;
            }
        }

        return false;
    }
    public int getRealHeight() {
        return getHeight() + Math.max(leftHand.heightOfShapes(), rightHand.heightOfShapes());
    }

    @Override
    public int getWidth() {
        return image.getWidth();
    }

    @Override
    public int getHeight() {
        return image.getHeight();
    }

    @Override
    public boolean isVisible() {
        return true;
    }

    public BufferedImage generateImage() {
        BufferedImage clownImage = new BufferedImage(
                getWidth(),
                getRealHeight(),
                BufferedImage.TYPE_INT_ARGB
        );
        Graphics2D g2d = clownImage.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.drawImage(image, 0, getRealHeight()-this.getHeight(), null);

        g2d.dispose();

        position.y = yOfClown - this.getRealHeight();

        return clownImage;
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        BufferedImage image = this.generateImage();

        Graphics2D g2d = image.createGraphics();

        final int biggestY = Math.max(leftHand.heightOfShapes(), rightHand.heightOfShapes()) - 10;

        int lastY = biggestY;
        for (BufferedImage shapeImage : leftHand.getSpriteImages()) {
            g2d.drawImage(shapeImage, 0, lastY, null);
            lastY -= shapeImage.getHeight();
        }

        lastY = biggestY;
        for (BufferedImage shapeImage : rightHand.getSpriteImages()) {
            g2d.drawImage(shapeImage, this.getWidth()-shapeImage.getWidth(), lastY, null);
            lastY -= shapeImage.getHeight();
        }

        g2d.dispose();

        vectors[0] = image;

        return vectors;
    }
}
