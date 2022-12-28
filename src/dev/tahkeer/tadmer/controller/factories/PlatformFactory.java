package dev.tahkeer.tadmer.controller.factories;

import dev.tahkeer.tadmer.model.shapes.Platform;

import java.awt.*;

public class PlatformFactory {

    public static Platform[] generate(int number, int fullWidth, int y, int width) {
        Platform[] platforms = new Platform[number];

        for (int i = 0; i < number; i++) {
            platforms[i] =  new Platform(fullWidth, y * (i + 1), width - (100 * i), Color.black);
        }

        return platforms;
    }

}
