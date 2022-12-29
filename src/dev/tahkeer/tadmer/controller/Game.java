package dev.tahkeer.tadmer.controller;

import dev.tahkeer.tadmer.controller.factories.ClownFactory;
import dev.tahkeer.tadmer.controller.factories.PlatformFactory;
import dev.tahkeer.tadmer.controller.factories.ShapeFactory;
import dev.tahkeer.tadmer.model.Clown;
import dev.tahkeer.tadmer.model.Score;
import dev.tahkeer.tadmer.model.interfaces.Level;
import dev.tahkeer.tadmer.model.interfaces.ScoreEventListener;
import dev.tahkeer.tadmer.model.interfaces.Shape;
import dev.tahkeer.tadmer.model.levels.EasyLevel;
import dev.tahkeer.tadmer.model.shapes.Platform;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Game implements World {
    private final CopyOnWriteArrayList<Shape> shapes = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<Clown> clowns = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<GameObject> constant = new CopyOnWriteArrayList<>();
    private final ArrayList<Platform> platforms = new ArrayList<>();
    private Level level;
    private final Score score = Score.getInstance();

    private boolean isGameOver = false;

    private Game() {
        changeLevel(new EasyLevel());

        score.addListener(new ScoreEventListener() {
            @Override
            public void added(int oldScore, int score) {
                if(score >= level.score()) {
                    Level nextLevel = level.next();

                    if(nextLevel == null) {
                        isGameOver = true;
                        return;
                    }

                    changeLevel(nextLevel);
                }
            }

            @Override
            public void removed(int oldScore, int score) {

            }
        });
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
    public int getWidth() { // frame width
        return 1280;
    }

    @Override
    public int getHeight() { // frame height
        return 720;
    }

    private boolean shouldGenerateShape(int percentage) {
        return new Random().nextInt(100 / percentage) == 0;
    }

    private void generate() {
        for (Platform platform : platforms) {
            Shape shape;

            if (shouldGenerateShape(30)) {
                shape = ShapeFactory.generate(platform.getX(), platform.getY());
                shape.setY(shape.getY() - shape.getHeight());
                shapes.add(shape);
            }

            if (shouldGenerateShape(40)) {
                shape = ShapeFactory.generate(platform.getX() + this.getWidth() - 100, platform.getY());
                shape.setY(shape.getY() - shape.getHeight());
                shapes.add(shape);
            }
        }
    }

    long lastTime = System.currentTimeMillis();

    @Override
    public boolean refresh() {
        if(isGameOver) {
            return false;
        }

        if (System.currentTimeMillis() - lastTime > level.speed() * 100L) {
            generate();
            lastTime = System.currentTimeMillis();
        }

        for (Shape shape : shapes) {
            boolean shapeMoved = false;

            for (Platform platform : platforms) {
                if (platform.isShapeLeft(shape)) {
                    shape.setX(shape.getX() + 3);
                    shapeMoved = true;
                    break;
                }

                if (platform.isShapeRight(shape)) {
                    shape.setX(shape.getX() - 3);
                    shapeMoved = true;
                    break;
                }
            }

            if (!shapeMoved) {
                shape.fall();
            }
        }


        for (Clown clown : clowns) {
            shapes.removeIf(clown::holds);
        }

        return true;
    }

    @Override
    public String getStatus() {
        return level.name() + " | Score: " + score.getScore();
    }

    @Override
    public int getSpeed() { // refresh every x millis
        return level.speed();
    }

    @Override
    public int getControlSpeed() { // in millis
        return level.controlSpeed();
    }

    private void changeLevel(Level level) {
        constant.clear();

        clowns.clear();

        for (int i = 0; i < level.numberOfClowns(); i++) {
            Clown clown = ClownFactory.generate(i * 400, this.getHeight());

            clown.addShapesListener(score::addScore);

            clowns.add(clown);
        }

        platforms.clear();

        Platform[] platformsArray = PlatformFactory.generate(level.numberOfQueues(), this.getWidth(), 60, 400);
        for (Platform platform : platformsArray) {
            platforms.add(platform);
            constant.add(platform);
        }

        this.level = level;
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
