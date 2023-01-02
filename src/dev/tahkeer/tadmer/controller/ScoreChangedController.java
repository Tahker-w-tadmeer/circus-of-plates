package dev.tahkeer.tadmer.controller;

import dev.tahkeer.tadmer.utils.interfaces.Level;
import dev.tahkeer.tadmer.utils.interfaces.ScoreEventListener;

public class ScoreChangedController implements ScoreEventListener {
    private final Game game;

    public ScoreChangedController(Game game) {
        this.game = game;
    }

    @Override
    public void added(int oldScore, int score) {
        if (score >= (int) game.level.get("score")) {
            Level nextLevel = (Level) game.level.get("next");
            if (nextLevel == null) {
                game.finish();
                return;
            }

            game.setLevel(nextLevel);
        }
    }

    @Override
    public void removed(int oldScore, int score) {

    }

}
