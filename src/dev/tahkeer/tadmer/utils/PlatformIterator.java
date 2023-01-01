package dev.tahkeer.tadmer.utils;

import dev.tahkeer.tadmer.model.shapes.Platform;

public class PlatformIterator implements GameObjectIterator {
    private final Platform[] platforms;
    private int index = 0;

    public PlatformIterator(Platform[] platforms) {
        this.platforms = platforms;
    }

    @Override
    public boolean hasNext() {
        return index < platforms.length;
    }

    @Override
    public Platform getNext() {
        return platforms[index++];
    }

    @Override
    public void reset() {
        index = 0;
    }
}
