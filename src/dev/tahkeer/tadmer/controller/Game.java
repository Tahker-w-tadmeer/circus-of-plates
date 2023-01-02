package dev.tahkeer.tadmer.controller;

import dev.tahkeer.tadmer.model.Clown;
import dev.tahkeer.tadmer.model.Score;
import dev.tahkeer.tadmer.utils.AsyncWaiter;
import dev.tahkeer.tadmer.utils.PlatformIterator;
import dev.tahkeer.tadmer.utils.factories.ClownFactory;
import dev.tahkeer.tadmer.utils.factories.PlatformFactory;
import dev.tahkeer.tadmer.utils.interfaces.LevelStrategy;
import dev.tahkeer.tadmer.utils.interfaces.Shape;
import dev.tahkeer.tadmer.model.levels.EasyLevel;
import dev.tahkeer.tadmer.model.shapes.Platform;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;

import java.time.Duration;
import java.util.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Game implements World {
    final CopyOnWriteArrayList<Shape> shapes = new CopyOnWriteArrayList<>();
    final CopyOnWriteArrayList<Clown> clowns = new CopyOnWriteArrayList<>();
    final CopyOnWriteArrayList<GameObject> constant = new CopyOnWriteArrayList<>();
    final ArrayList<Platform> platforms = new ArrayList<>();
    final Score score = Score.getInstance();
    HashMap<String, Object> level;
    LevelStrategy levelStrategy;
    AsyncWaiter shapeGenerator;
    private boolean isGameOver = false;

    private Game() {
        this.setLevel(new EasyLevel());

        score.addListener(new ScoreChangedController(this));
    }

    @Override
    public boolean refresh() {
        if (this.isFinished()) return false;

        shapeGenerator.execute();

        MoveController.move(this);

        return true;
    }

    public void setLevel(LevelStrategy levelStrategy) {
        this.level = levelStrategy.getProperties();
        this.levelStrategy = levelStrategy;

        if(this.isFinished()) {
            return;
        }

        constant.clear();
        clowns.clear();

        for (int i = 0; i < (int) level.get("numberOfClowns"); i++) {
            Clown clown = ClownFactory.generate(i * 400, this.getHeight());

            clown.addShapesListener(new ShapesChangedController(this));

            clowns.add(clown);
        }

        platforms.clear();

        PlatformIterator iterator = PlatformFactory.generate((int) level.get("numberOfQueues"), this.getWidth(), 60, 400);
        while (iterator.hasNext()) {
            Platform platform = iterator.getNext();
            platforms.add(platform);
            constant.add(platform);
        }

        if(shapeGenerator == null) {
            shapeGenerator = new AsyncWaiter(
                    () -> GenerateController.generate(this),
                    Duration.ofMillis((int) level.get("speed") * 100L)
            );
        }

        shapeGenerator.setDuration(Duration.ofMillis((int) level.get("speed") * 100L));
    }

    public boolean isFinished() {
        return isGameOver;
    }

    public void finish() {
        isGameOver = true;
    }

    @Override
    public List<GameObject> getConstantObjects() {
        return constant;
    }

    @Override
    public List<GameObject> getMovableObjects() {
        return shapes.stream().map(s -> (GameObject) s).toList();
    }

    @Override
    public List<GameObject> getControlableObjects() {
        return clowns.stream().map(s -> (GameObject) s).toList();
    }

    @Override
    public int getWidth() { return 1280; }

    @Override
    public int getHeight() { return 720; }

    @Override
    public String getStatus() {
        return level.get("name") + " | Score: " + score.getScore();
    }

    @Override
    public int getSpeed() { // refresh every x millis
        return (int) level.get("speed");
    }

    @Override
    public int getControlSpeed() { // in millis
        return (int) level.get("controlSpeed");
    }

    private static final class GameHolder {
        private static final Game game;

        static {
            game = new Game();
        }
    }

    public static Game getInstance() {
        return GameHolder.game;
    }
}
