package dev.tahkeer.tadmer.model.shapes;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class Bomb extends DefaultShape {
    private final BufferedImage[] vectors = new BufferedImage[1];

    public Bomb(int x, int y, Color color) {
        this.setColor(color);

        this.width = 60;
        this.height = 60;

        this.setX(x);
        this.setY(y);

        this.generateImage();
    }

    private void generateImage() {
        BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        // g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        try {
            // int widthDivided = getWidth() ;//- getWidth()/3;
            Image icon = ImageIO.read(new File("./res/bomb.png"))
                    .getScaledInstance(60,60, Image.SCALE_FAST);

            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.drawImage(icon,getX(), getY(), null);
            g2d.dispose();
        } catch (IOException ignored) {
        }
        vectors[0] = image;
    }
    private int aspectRatio() {
        return 320 / 201;
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return vectors;
    }
}
