package dev.tahkeer.tadmer.utils.factories;

import dev.tahkeer.tadmer.model.shapes.Platform;
import dev.tahkeer.tadmer.utils.PlatformIterator;

import java.awt.*;

public class PlatformFactory {

    public static PlatformIterator generate(int number, int fullWidth, int y, int width) {
        Platform[] platforms = new Platform[number];

        for (int i = 0; i < number; i++) {
            platforms[i] =  new Platform(fullWidth, y * (i + 1), width - (100 * i), Color.black);
        }

        return new PlatformIterator(platforms);
    }
}
