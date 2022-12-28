package dev.tahkeer.tadmer.controller.factories;

import dev.tahkeer.tadmer.model.Clown;

public class ClownFactory {

    public static Clown generate(int x, int y) {
        return new Clown(x, y);
    }

}
