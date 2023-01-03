package dev.tahkeer.tadmer.utils.interfaces;

public interface Level extends LevelStrategy {
    String name();

    int numberOfClowns();

    int numberOfQueues();

    int speed();

    int controlSpeed();

    int score();

    Level next();

    Level previous();
}
