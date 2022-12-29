package dev.tahkeer.tadmer.controller;

import dev.tahkeer.tadmer.controller.factories.ClownFactory;
import dev.tahkeer.tadmer.controller.factories.PlatformFactory;
import dev.tahkeer.tadmer.model.Clown;
import dev.tahkeer.tadmer.model.interfaces.Level;
import dev.tahkeer.tadmer.model.shapes.Platform;

import java.time.Duration;

public class ChangeLevelController {
    protected static void changeLevel(Game game, Level level) {
        game.level = level;

        game.constant.clear();
        game.clowns.clear();

        for (int i = 0; i < game.level.numberOfClowns(); i++) {
            Clown clown = ClownFactory.generate(i * 400, game.getHeight());

            clown.addShapesListener(game.score::addScore);

            game.clowns.add(clown);
        }

        game.platforms.clear();

        Platform[] platformsArray = PlatformFactory.generate(level.numberOfQueues(), game.getWidth(), 60, 400);
        for (Platform platform : platformsArray) {
            game.platforms.add(platform);
            game.constant.add(platform);
        }

        if(game.shapeGenerator == null) {
            game.shapeGenerator = new AsyncWaiter(
                    () -> GenerateController.generate(game),
                    Duration.ofMillis(level.speed() * 100L)
            );
        }

        game.shapeGenerator.setDuration(Duration.ofMillis(level.speed() * 100L));
    }

}
