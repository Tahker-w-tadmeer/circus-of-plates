package dev.tahkeer.tadmer.utils;

import dev.tahkeer.tadmer.controller.Game;
import dev.tahkeer.tadmer.utils.interfaces.State;

import javax.swing.*;

public abstract class AbtractState implements State {
    @Override
    abstract public void doAction(LevelState levelState, Game game);
    public  abstract JMenuItem getLevel();
}
