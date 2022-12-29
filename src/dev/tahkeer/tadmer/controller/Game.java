package dev.tahkeer.tadmer.controller;

import dev.tahkeer.tadmer.model.Clown;
import dev.tahkeer.tadmer.model.Score;
import dev.tahkeer.tadmer.model.interfaces.Level;
import dev.tahkeer.tadmer.model.interfaces.Shape;
import dev.tahkeer.tadmer.model.levels.EasyLevel;
import dev.tahkeer.tadmer.model.shapes.Platform;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;

import java.util.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Game implements World {
    final CopyOnWriteArrayList<Shape> shapes = new CopyOnWriteArrayList<>();
    final CopyOnWriteArrayList<Clown> clowns = new CopyOnWriteArrayList<>();
    final CopyOnWriteArrayList<GameObject> constant = new CopyOnWriteArrayList<>();
    final ArrayList<Platform> platforms = new ArrayList<>();
    final Score score = Score.getInstance();
    Level level;
    AsyncWaiter shapeGenerator;
    boolean isGameOver = false;

    private Game() {
        ChangeLevelController.changeLevel(this, new EasyLevel());

        score.addListener(new ScoreChangedController(this));
    }

    @Override
    public boolean refresh() {
        if (isGameOver) return false;

        shapeGenerator.execute();

        MoveController.move(this);

        return true;
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
