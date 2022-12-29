package dev.tahkeer.tadmer.controller;

import dev.tahkeer.tadmer.model.interfaces.Level;
import dev.tahkeer.tadmer.model.interfaces.ScoreEventListener;

public class ScoreChangedController implements ScoreEventListener {
    private final Game game;

    public ScoreChangedController(Game game) {
        this.game = game;
    }

    @Override
    public void added(int oldScore, int score) {
        if (score >= game.level.score()) {
            Level nextLevel = game.level.next();
            if (nextLevel == null) {
                game.isGameOver = true;
                return;
            }

            ChangeLevelController.changeLevel(game, nextLevel);
        }
    }

    @Override
    public void removed(int oldScore, int score) {

    }

}
