package dev.tahkeer.tadmer.utils;

import eg.edu.alexu.csd.oop.game.GameObject;

public interface GameObjectIterator {
    boolean hasNext();

    GameObject getNext();

    void reset();
}
