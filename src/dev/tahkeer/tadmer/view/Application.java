package dev.tahkeer.tadmer.view;

import dev.tahkeer.tadmer.model.Game;
import eg.edu.alexu.csd.oop.game.GameEngine;

public class Application {
    public static void main(String[] args) {
        GameEngine.start("Circus Of Plates", Game.getInstance());
    }
}