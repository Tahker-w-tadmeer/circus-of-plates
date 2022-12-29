package dev.tahkeer.tadmer.controller;

import dev.tahkeer.tadmer.utils.Sound;
import dev.tahkeer.tadmer.utils.interfaces.ShapesEventListener;

public class ShapesChangedController implements ShapesEventListener {

    private final Game game;

    public ShapesChangedController(Game game) {
        this.game = game;
    }

    @Override
    public void collected() {
        game.score.addScore();
    }

    @Override
    public void bombCaught() {
        game.score.removeScore();

        Sound.getInstance().playExplosion();
    }

}
