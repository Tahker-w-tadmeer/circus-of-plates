package dev.tahkeer.tadmer.model.shapes;

import dev.tahkeer.tadmer.model.DefaultGameObject;
import eg.edu.alexu.csd.oop.game.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Platform extends DefaultShape {

    private final BufferedImage[] vectors = new BufferedImage[1];
    int x2,y2;
    public Platform(int length,int Y,int width,int height1,Color color,int flag) {
        super(0,Y,color);

        if (flag == 0)
        {
            this.setX(0);
            this.x2 = length;
            this.setY(Y);
            this.y2 = Y;
            System.out.println("zabat 1");

    }else if(flag == 1)
        {   this.setX(700); // 700 //X1
            this.x2 = Math.abs(500-length);                //X2
            this.setY(Y);                 //Y1
            this.y2 = Y;                  //Y2

            System.out.println(this.x2);
            System.out.println(this.getX());

        }
        this.width = width;
        this.height = height1;

        this.generateImage();
    }

    private void generateImage() {
        BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setStroke(new BasicStroke(10));

        g2d.setColor(this.getColor());
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
