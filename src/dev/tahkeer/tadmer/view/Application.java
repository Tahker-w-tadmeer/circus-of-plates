package dev.tahkeer.tadmer.view;

import dev.tahkeer.tadmer.controller.Game;
import eg.edu.alexu.csd.oop.game.GameEngine;

public class Application {
    public static void main(String[] args) {
        GameEngine.GameController controller = GameEngine.start("Circus Of Plates", Game.getInstance());

//        Score.getInstance().addListener(new ScoreEventListener() {
//            @Override
//            public void added(int oldScore, int score) {
//                controller.pause();
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }finally {
//                    controller.resume();
//                }
//            }
//
//            @Override
//            public void removed(int oldScore, int score) {
//
//            }
//        });
    }
}