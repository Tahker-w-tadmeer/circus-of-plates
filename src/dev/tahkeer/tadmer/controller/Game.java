package dev.tahkeer.tadmer.controller;

import dev.tahkeer.tadmer.controller.factories.ShapeFactory;
import dev.tahkeer.tadmer.model.Clown;
import dev.tahkeer.tadmer.model.interfaces.Level;
import dev.tahkeer.tadmer.model.interfaces.Shape;
import dev.tahkeer.tadmer.model.levels.EasyLevel;
import dev.tahkeer.tadmer.model.shapes.Platform;
import dev.tahkeer.tadmer.model.shapes.DefaultShape;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Game implements World {
    Point rightcheck = new Point();
    ArrayList<Platform> arrayPlatform;
    Point leftcheck = new Point();
    CopyOnWriteArrayList<GameObject> rightqueue=new CopyOnWriteArrayList<>();
    CopyOnWriteArrayList<GameObject> leftqueue=new CopyOnWriteArrayList<>();

    private final CopyOnWriteArrayList<GameObject> movable = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<GameObject> controllable = new CopyOnWriteArrayList<>();
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

    private boolean shouldGenerateShape(int percentage) {
        return new Random().nextInt(100 / percentage) == 0;
    }

    private void generate() {
        Shape upperLeft = ShapeFactory.generate(10,0);
        Shape bottomLeft = ShapeFactory.generate(10,300);
        Shape upperRight = ShapeFactory.generate(1200,0);
        Shape bottomRight = ShapeFactory.generate(1200,300);

        if(shouldGenerateShape(50))
            movable.add(bottomLeft);

        if(shouldGenerateShape(25))
            movable.add(upperRight);

        if(shouldGenerateShape(75))
            movable.add(upperLeft);

        if(shouldGenerateShape(60))
            movable.add(bottomRight);
    }

    long lastTime = System.currentTimeMillis();

    @Override
    public boolean refresh() {
        if(System.currentTimeMillis() - lastTime > level.speed() * 100L) {
            generate();
            lastTime = System.currentTimeMillis();
        }

        for (GameObject obstacle : movable) {
            if(obstacle.getX()<=1600 && obstacle.getX()>600) {
                obstacle.setX(obstacle.getX() - 2);
            }
            if(obstacle.getX()>=400&&obstacle.getX()<450||obstacle.getX()>=500&&obstacle.getX()<600){
                obstacle.setY(obstacle.getY() + 1);
            }
            else {
                obstacle.setX(obstacle.getX() + 1);
            }
        }


        for (GameObject clownObject : controllable) {
            if(! (clownObject instanceof Clown clown))
                continue;

            for (GameObject obstacle : movable) {
                if(leftqueue.size()==0){
                if (obstacle.getY() >= clown.getY() - 10
                        && obstacle.getY() <= clown.getY() + 20
                        && obstacle.getX() >= clown.getX() - 10
                        && obstacle.getX() <= clown.getX() + clown.getWidth()
                        && obstacle.getX() + obstacle.getWidth() <= clown.getX() + 80
                ) { // left hand
                    movable.remove(obstacle);
                    clown.addToLeftHand(obstacle);
                    leftqueue.add(obstacle);
                    controllable.add(obstacle);
                    ((DefaultShape) obstacle).setShouldMoveHorizontally(false);
                    break;
                } }
                if(rightqueue.size()==0){
                     if (obstacle.getY() >= clown.getY() - 10
                        && obstacle.getY() <= clown.getY() + 5
                        && obstacle.getX() >= clown.getX() - 10 + 160
                        && obstacle.getX() <= clown.getX() + clown.getWidth()
                        && obstacle.getX() + obstacle.getWidth() <= clown.getX() + 80 + 160
                ) { // right hand
                    movable.remove(obstacle);
                    clown.addToRightHand(obstacle);
                    rightqueue.add(obstacle);
                    controllable.add(obstacle);
                    ((DefaultShape) obstacle).setShouldMoveHorizontally(false);
                    break;
                } else if (obstacle.getY() >= clown.getY() + clown.getHeight()) {
                    movable.remove(obstacle);
                    break;
                }}
                if(rightqueue.size()>0) {
                    rightcheck = new Point(rightqueue.get(rightqueue.size() - 1).getX(), rightqueue.get(rightqueue.size() - 1).getY());
                    if (rightcheck.distance(obstacle.getX(), obstacle.getY()) >= 5&&rightcheck.distance(obstacle.getX(), obstacle.getY()) <20&&obstacle.getX()<600&& rightqueue.size()<22) {
                        movable.remove(obstacle);
                       // System.out.println(obstacle.getY()+"da el y .. dah el x: "+obstacle.getX());
                        obstacle.setY(obstacle.getY() - 5);
                        controllable.add(obstacle);
                        rightqueue.add(obstacle);
                        ((DefaultShape) obstacle).setShouldMoveHorizontally(false);
                    }
                }
                if(leftqueue.size()>0) {
                    leftcheck = new Point(leftqueue.get(leftqueue.size() - 1).getX(), leftqueue.get(leftqueue.size() - 1).getY());
                    if (leftcheck.distance(obstacle.getX(), obstacle.getY()) >= 5&&leftcheck.distance(obstacle.getX(), obstacle.getY()) <20&&obstacle.getY()>13&& leftqueue.size()<22) {
                        movable.remove(obstacle);
                        obstacle.setY(obstacle.getY() - 5);
                        controllable.add(obstacle);
                        leftqueue.add(obstacle);
                        ((DefaultShape) obstacle).setShouldMoveHorizontally(false);
                    }
                }
            }
        }
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
            Platform platform = new Platform(400-(100*i),30+60*i,this.getWidth(),this.getHeight(),Color.black,0);
            Platform platform2 = new Platform(400-(100*i),30+60*i,this.getWidth(),this.getHeight(),Color.black,1);
            arrayPlatform.add(platform);
            arrayPlatform.add(platform2);
            constant.add(platform);
            constant.add(platform2);




            movable.add(ShapeFactory.generate(500,0));
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
