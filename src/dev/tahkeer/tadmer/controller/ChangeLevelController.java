package dev.tahkeer.tadmer.controller;

import dev.tahkeer.tadmer.utils.AsyncWaiter;
import dev.tahkeer.tadmer.utils.PlatformIterator;
import dev.tahkeer.tadmer.utils.factories.ClownFactory;
import dev.tahkeer.tadmer.utils.factories.PlatformFactory;
import dev.tahkeer.tadmer.model.Clown;
import dev.tahkeer.tadmer.utils.interfaces.Level;
import dev.tahkeer.tadmer.model.shapes.Platform;

import java.time.Duration;

public class ChangeLevelController {
    protected static void changeLevel(Game game, Level level) {
        game.level = level;

        game.constant.clear();
        game.clowns.clear();

        for (int i = 0; i < game.level.numberOfClowns(); i++) {
            Clown clown = ClownFactory.generate(i * 400, game.getHeight());

            clown.addShapesListener(new ShapesChangedController(game));

            game.clowns.add(clown);
        }

        game.platforms.clear();

        PlatformIterator iterator = PlatformFactory.generate(level.numberOfQueues(), game.getWidth(), 60, 400);
        while (iterator.hasNext()) {
            Platform platform = iterator.getNext();
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
