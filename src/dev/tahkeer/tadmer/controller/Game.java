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
    protected Level level;
    private long lastTime = System.currentTimeMillis();
    private boolean isGameOver = false;

    private Game() {
        ChangeLevelEngine.changeLevel(new EasyLevel(), constant, clowns, platforms, this);
        Score.getInstance().addListener(new ScoreEventListener() {

            @Override
            public void added(int oldScore, int score) {
                if(score >= level.score()) {
                    Level nextLevel = level.next();
                    if(nextLevel == null) {
                        isGameOver = true;
                        return;
                    }
                    ChangeLevelEngine.changeLevel(nextLevel, constant, clowns, platforms, Game.this);
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
    private boolean shouldGenerate(){
        return System.currentTimeMillis() - lastTime > level.speed() * 100L;
    }
    @Override
    public boolean refresh() {
        if(isGameOver) {return false;}
        if (shouldGenerate()) {
            GenerateEngine.generate(shapes,platforms,this);
            lastTime = System.currentTimeMillis();
        }
        MoveEngine.move(shapes, clowns, platforms);
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
