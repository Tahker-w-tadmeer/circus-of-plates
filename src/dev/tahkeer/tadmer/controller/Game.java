package dev.tahkeer.tadmer.controller;

import dev.tahkeer.tadmer.controller.factories.ShapeFactory;
import dev.tahkeer.tadmer.model.Clown;
import dev.tahkeer.tadmer.model.interfaces.Level;
import dev.tahkeer.tadmer.model.interfaces.Shape;
import dev.tahkeer.tadmer.model.levels.EasyLevel;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;

import java.awt.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Game implements World {


    private final CopyOnWriteArrayList<GameObject> movable = new CopyOnWriteArrayList<>();

    private final ArrayList<GameObject> controllable = new ArrayList<>();
    private final ArrayList<GameObject> constant = new ArrayList<>();
    private Level level;

    private Game() {
        changeLevel(new EasyLevel());
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                genrate();
            }
        }, 0, 1500);

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
    private void genrate() {
        var sh=ShapeFactory.generate(10,0);
        movable.add(sh);
        var shh=ShapeFactory.generate(10,300);
        movable.add(shh);
        var shhh=ShapeFactory.generate(1200,0);
        movable.add(shhh);
        var shhhh=ShapeFactory.generate(1200,300);
        movable.add(shhhh);
    }
    @Override
    public boolean refresh() {
        // if returns false game stops
        ArrayList<Integer> toBeRemoved = new ArrayList<>();
        for (GameObject obstacle : movable) {
            if(obstacle.getX()<=1600&&obstacle.getX()>600){
                obstacle.setX(obstacle.getX() - 2);
            }
            if(obstacle.getX()>=400&&obstacle.getX()<450||obstacle.getX()>=500&&obstacle.getX()<600){
                obstacle.setY(obstacle.getY() + 1);
            }
            else {
                obstacle.setX(obstacle.getX() + 1);
            }
            if(obstacle.getY() == this.getHeight()) {
                toBeRemoved.add(movable.indexOf(obstacle));
            }
        }

        // TODO ZEYAD
        // TODO USING ShapeFactory
        // TODO generate obstacles (Plates, bombs, ...) on platforms
        // TODO Make generated obstacles slide until it falls down
        Clown clown = (Clown) controllable.get(0);
//        Shape shape=(Shape) controllable.get(1);
//        System.out.println(shape.getX() + " " + shape.getY());

        for (GameObject obstacle : movable) {

             obstacle.setY(obstacle.getY() + 1);

            if(obstacle.getY() >= clown.getY()-10
                    && obstacle.getY()<=clown.getY()+20
                    && obstacle.getX() >= clown.getX()-10
                    && obstacle.getX() <= clown.getX() + clown.getWidth()
                    && obstacle.getX()+obstacle.getWidth()<=clown.getX()+80
            ){
                movable.remove(obstacle);
                constant.add(obstacle);
                break;
            }
            else if (obstacle.getY() >= clown.getY()+clown.getHeight()) {
                movable.remove(obstacle);
                break;
            }

        }

        // TODO PETER
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

            movable.add(ShapeFactory.generate(100,0));

            // TODO YAMEN
            // TODO Draw queues/platform
            // TODO Class Platform implementing GameObject extends DefaultGameObject
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
