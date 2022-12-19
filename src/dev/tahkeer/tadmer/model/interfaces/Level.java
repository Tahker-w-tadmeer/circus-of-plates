package dev.tahkeer.tadmer.model.interfaces;

public interface Level {
    String name();

    int numberOfClowns();

    int numberOfQueues();

    int speed();

    Level next();

    Level previous();
}
