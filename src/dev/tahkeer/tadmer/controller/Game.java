package dev.tahkeer.tadmer.controller;

import dev.tahkeer.tadmer.controller.factories.ShapeFactory;
import dev.tahkeer.tadmer.model.Clown;
import dev.tahkeer.tadmer.model.interfaces.Level;
import dev.tahkeer.tadmer.model.levels.EasyLevel;
import dev.tahkeer.tadmer.model.shapes.Platform;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;

import java.util.ArrayList;
import java.util.List;

public class Game implements World {

    private final ArrayList<GameObject> movable = new ArrayList<>();
    private final ArrayList<GameObject> controllable = new ArrayList<>();
    private final ArrayList<GameObject> constant = new ArrayList<>();
    private Level level;

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

    @Override
    public boolean refresh() { // if returns false game stops

        // TODO ZEYAD
        // TODO USING ShapeFactory
        // TODO generate obstacles (Plates, bombs, ...) on platforms
        // TODO Make generated obstacles slide until it falls down

        ArrayList<Integer> toBeRemoved = new ArrayList<>();
        for (GameObject obstacle : movable) {
            obstacle.setY(obstacle.getY() + 1);

            if(obstacle.getY() > this.getHeight()) {
                toBeRemoved.add(movable.indexOf(obstacle));
            }
        }
        toBeRemoved.forEach(movable::remove);

        // TODO PETER
        // TODO Detect if shape is on a clown hand, if so, remove it from the list
        // TODO Then add it to the clown's hand

        return true;
    }

    @Override
    public String getStatus() { // bar at the bottom
        return level.name() + " | Score: " + 0;
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

        for (int i=0; i<level.numberOfClowns(); i++) {
            controllable.add(new Clown(i*400, this.getHeight()-20));
        }

        for (int i=0; i<level.numberOfQueues(); i++) {
            Platform platform = new Platform(500,100,this.getWidth(),this.getHeight(),0);
            controllable.add(platform);
            System.out.println("ana da5alt hena");
        }
    }
    private static final class GameHolder {
        private static final Game game = new Game();
    }

    public static Game getInstance() {
        return GameHolder.game;
    }

}
