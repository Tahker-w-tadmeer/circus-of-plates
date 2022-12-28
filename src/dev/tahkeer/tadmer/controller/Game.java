package dev.tahkeer.tadmer.controller;

import dev.tahkeer.tadmer.controller.factories.ShapeFactory;
import dev.tahkeer.tadmer.model.Clown;
import dev.tahkeer.tadmer.model.Hand;
import dev.tahkeer.tadmer.model.Score;
import dev.tahkeer.tadmer.model.interfaces.Level;
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
    private final CopyOnWriteArrayList<GameObject> movable = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<GameObject> controllable = new CopyOnWriteArrayList<>();
    private final ArrayList<GameObject> constant = new ArrayList<>();
    private Level level;
    private final ArrayList<Platform> platforms = new ArrayList<>();

    private Game() {
        changeLevel(new EasyLevel());
    }

    @Override
    public List<GameObject> getConstantObjects() {
        return constant;
    }

    @Override
    public List<GameObject> getMovableObjects() {
        return movable;
    }

    @Override
    public List<GameObject> getControlableObjects() {
        return controllable;
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
                movable.add(shape);
            }

            if (shouldGenerateShape(40)) {
                shape = ShapeFactory.generate(platform.getX() + this.getWidth() - 100, platform.getY());
                shape.setY(shape.getY() - shape.getHeight());
                movable.add(shape);
            }
        }
    }

    long lastTime = System.currentTimeMillis();

    @Override
    public boolean refresh() {
        if (System.currentTimeMillis() - lastTime > level.speed() * 100L) {
            generate();
            lastTime = System.currentTimeMillis();
        }

        for (GameObject obstacle : movable) {
            boolean shapeMoved = false;

            for (Platform platform : platforms) {
                if (platform.isShapeLeft(obstacle)) {
                    obstacle.setX(obstacle.getX() + 1);
                    shapeMoved = true;
                    break;
                }

                if (platform.isShapeRight(obstacle)) {
                    obstacle.setX(obstacle.getX() - 1);
                    shapeMoved = true;
                    break;
                }
            }

            if (!shapeMoved) {
                obstacle.setY(obstacle.getY() + 1);
            }
        }


        for (GameObject clownObject : controllable) {
            Clown clown = (Clown) clownObject;

            for (GameObject obstacle : movable) {
                Hand hand = clown.getContainsHand(obstacle);
                if(hand == null) {
                    continue;
                }

                movable.remove(obstacle);

                if(hand.shapeLand(obstacle)) {
                    Score.getInstance().addScore();
                }
            }
        }
        return true;
    }

    @Override
    public String getStatus() {
        return level.name() + " | Score: " + Score.getInstance().getScore();
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
        this.level = level;

        movable.clear();
        controllable.clear();
        constant.clear();

        for (int i = 0; i < level.numberOfClowns(); i++) {
            controllable.add(new Clown(i * 400, this.getHeight()));
        }

        for (int i = 0; i < level.numberOfQueues(); i++) {
            Platform platform = new Platform(this.getWidth(), 60 * (i + 1), 400 - (100 * i), Color.black);

            platforms.add(platform);
            constant.add(platform);
        }
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
