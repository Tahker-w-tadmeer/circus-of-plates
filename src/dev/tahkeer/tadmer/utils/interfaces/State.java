package dev.tahkeer.tadmer.utils.interfaces;

import dev.tahkeer.tadmer.controller.Game;
import dev.tahkeer.tadmer.utils.LevelState;
public interface State {
    void doAction(LevelState levelState,Game game);
}

