package dev.tahkeer.tadmer.controller;

import dev.tahkeer.tadmer.model.Clown;
import dev.tahkeer.tadmer.model.Score;
import dev.tahkeer.tadmer.utils.AsyncWaiter;
import dev.tahkeer.tadmer.utils.Background;
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
    final List<Shape> shapes = new CopyOnWriteArrayList<>();
    final List<Clown> clowns = new CopyOnWriteArrayList<>();
    final List<GameObject> constant = new CopyOnWriteArrayList<>();
    final List<Platform> platforms = new ArrayList<>();
    final Score score = Score.getInstance();
    HashMap<String, Object> level;
    LevelStrategy levelStrategy;
    AsyncWaiter shapeGenerator;
    AsyncWaiter timerWaiter;
    private boolean isGameOver = false;

    Duration timer;

    private int width;
    private int height;

    private Game(int width, int height) {
        this.width = width;
        this.height = height;

        this.setLevel(new EasyLevel());
        score.addListener(new ScoreChangedController(this));

        timerWaiter = new AsyncWaiter(
                () -> timer = timer.minusSeconds(1),
                Duration.ofSeconds(1)
        );
    }

    @Override
    public boolean refresh() {
        if (this.isFinished()) return false;

        timerWaiter.execute();

        if(timer.isZero() || timer.isNegative()) {
            this.finish();
            return false;
        }

        shapeGenerator.execute();

        MoveController.move(this);

        return true;
    }

    public void resetScore() {
        score.reset();
    }

    public void setLevel(LevelStrategy levelStrategy) {
        resetScore();

        timer = Duration.ofSeconds(59);

        this.level = levelStrategy.getProperties();
        this.levelStrategy = levelStrategy;

        this.isGameOver = false;

        constant.clear();
        clowns.clear();

        constant.add(new Background());

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
                    () -> GenerateController.generate(Game.this),
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
    public int getWidth() { return width; }

    @Override
    public int getHeight() { return height; }

    @Override
    public String getStatus() {
        long minutes = 0;
        long seconds = timer.toSeconds();

        while(seconds >= 59) {
            seconds -= 60;
            minutes++;
        }

        return level.get("name") + " | Score: " + score.getScore() + " | Time: " + minutes + ":" + seconds;
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
        private static Game game;

        private static Game getGame(){
            return game;
        }

        private static Game getGame(int width, int height) {
            game = new Game(width, height);

            return game;
        }
    }

    public static Game getInstance(int width, int height) {
        return GameHolder.getGame(width, height);
    }

    public static Game getInstance() {
        return GameHolder.getGame();
    }
}
