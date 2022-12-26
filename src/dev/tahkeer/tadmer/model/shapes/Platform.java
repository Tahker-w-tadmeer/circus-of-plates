package dev.tahkeer.tadmer.model.shapes;

import dev.tahkeer.tadmer.model.DefaultGameObject;
import eg.edu.alexu.csd.oop.game.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Platform extends DefaultShape {

    private final BufferedImage[] vectors = new BufferedImage[1];
    int x2,y2;
    public Platform(int length,int height,int width,int height1,int flag) {
        super(0, height, Color.black);
        if (flag == 0)
        {    this.setX(0);
            this.x2 = length;
            this.setY(height);
            this.y2 = height;
            System.out.println("zabat 1");
    }else
        {   this.setX(width);
            this.x2 = width-length;
            this.setY(height);
            this.y2 = height;
            System.out.println("zabat 2");

        }
        this.width = width;
        this.height = height1;
        this.generateImage();

    }

    private void generateImage() {
        BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(this.getColor());
        g2d.setStroke(new BasicStroke(4));
        g2d.drawLine(this.getX(), this.getY(), this.x2, this.y2);

        System.out.println("rasamt");

        g2d.dispose();
        vectors[0] = image;

    }
    @Override
    public BufferedImage[] getSpriteImages() {
    return vectors;
    }
    @Override
    public boolean isVisible() {
        return true;
    }
}
