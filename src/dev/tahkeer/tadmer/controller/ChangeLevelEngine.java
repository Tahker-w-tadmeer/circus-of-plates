package dev.tahkeer.tadmer.controller;

import dev.tahkeer.tadmer.controller.factories.ClownFactory;
import dev.tahkeer.tadmer.controller.factories.PlatformFactory;
import dev.tahkeer.tadmer.model.Clown;
import dev.tahkeer.tadmer.model.Score;
import dev.tahkeer.tadmer.model.interfaces.Level;
import dev.tahkeer.tadmer.model.shapes.Platform;
import eg.edu.alexu.csd.oop.game.GameObject;

import java.util.List;

public class ChangeLevelEngine {
    protected static void changeLevel(Level level, List<GameObject> constant, List<Clown> clowns, List<Platform> platforms,Game game) {
        constant.clear();
        clowns.clear();
        for (int i = 0; i < level.numberOfClowns(); i++) {
            Clown clown = ClownFactory.generate(i * 400, game.getHeight());
            clown.addShapesListener(() -> Score.getInstance().addScore());
            clowns.add(clown);
        }

        platforms.clear();

        Platform[] platformsArray = PlatformFactory.generate(level.numberOfQueues(), game.getWidth(), 60, 400);
        for (Platform platform : platformsArray) {
            platforms.add(platform);
            constant.add(platform);
        }

        game.level = level;
    }

}
