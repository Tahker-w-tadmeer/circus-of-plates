package dev.tahkeer.tadmer.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Images {
    private static BufferedImage bombImage = null;

    static {
        try {
            bombImage = ImageIO.read(new File("./res/bomb.png"));
        } catch (IOException e) {
            System.err.println("Error while loading bomb image: " + e.getMessage());
        }
    }

    public static BufferedImage getBombImage() {
        return bombImage;
    }
}
