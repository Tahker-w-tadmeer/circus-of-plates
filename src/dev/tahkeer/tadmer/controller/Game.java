package dev.tahkeer.tadmer.controller;

import dev.tahkeer.tadmer.controller.factories.ShapeFactory;
import dev.tahkeer.tadmer.model.Clown;
import dev.tahkeer.tadmer.model.interfaces.Level;
import dev.tahkeer.tadmer.model.interfaces.Shape;
import dev.tahkeer.tadmer.model.levels.EasyLevel;
import dev.tahkeer.tadmer.model.shapes.DefaultShape;
import dev.tahkeer.tadmer.model.shapes.Plate;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

public class Game implements World {


    private final ArrayList<GameObject> movable = new ArrayList<>();
    private final ArrayList<GameObject> controllable = new ArrayList<>();
    private final ArrayList<GameObject> constant = new ArrayList<>();
    private Level level;

    private Game() throws InterruptedException {
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
      ArrayList<GameObject> kk= new ArrayList<>();
        var sh=ShapeFactory.generate(10,0);
       // movable.add(sh);
        kk.add(sh);
        var shh=ShapeFactory.generate(10,300);
      //  movable.add(shh);
        kk.add(shh);
        var shhh=ShapeFactory.generate(1200,0);
       // movable.add(shhh);
        kk.add(shhh);
        var shhhh=ShapeFactory.generate(1200,300);
      //  movable.add(shhhh);
        kk.add(shhhh);
        movable.addAll(kk);
//        for (int i = 0; i < 8; i++) {
//            var sh=ShapeFactory.generate(10+(i*60),0);
//            movable.add(sh);
//            var shh=ShapeFactory.generate(10+(i*60),300);
//            movable.add(shh);
//            var shhh=ShapeFactory.generate(1200-(i*60),0);
//            movable.add(shhh);
//            var shhhh=ShapeFactory.generate(1200-(i*60),300);
//            movable.add(shhhh);
//        }
//        var sh=ShapeFactory.generate(10,0);
//        movable.add(sh);
//        var shh=ShapeFactory.generate(10,300);
//        movable.add(shh);
//        var shhh=ShapeFactory.generate(1200,0);
//        movable.add(shhh);
//        var shhhh=ShapeFactory.generate(1200,300);
//        movable.add(shhhh);
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


//        for (GameObject obstacle : movable) {
//            obstacle.setY(obstacle.getY() + 1);
//
//            if(obstacle.getY() > this.getHeight()) {
//                toBeRemoved.add(movable.indexOf(obstacle));
//            }
//        }
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
            // TODO YAMEN
            // TODO Draw queues/platform
            // TODO Class Platform implementing GameObject extends DefaultGameObject
        }
    }
    private static final class GameHolder {
        private static final Game game;

        static {
            try {
                game = new Game();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static Game getInstance() {
        return GameHolder.game;
    }

}
