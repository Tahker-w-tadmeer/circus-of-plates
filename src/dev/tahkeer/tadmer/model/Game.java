package dev.tahkeer.tadmer.model;

import dev.tahkeer.tadmer.model.interfaces.Level;
import dev.tahkeer.tadmer.model.levels.EasyLevel;
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


        return true;
    }

    @Override
    public String getStatus() { // bar at the bottom
        return level.name() + " | Score: " + 0;
    }

    @Override
    public int getSpeed() { // in millis
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
    }

    private static final class GameHolder {
        private static final Game game = new Game();
    }

    public static Game getInstance() {
        return GameHolder.game;
    }

}
