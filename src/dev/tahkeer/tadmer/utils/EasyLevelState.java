package dev.tahkeer.tadmer.utils;

import dev.tahkeer.tadmer.controller.Game;
import dev.tahkeer.tadmer.utils.interfaces.State;

import javax.swing.*;

public class EasyLevelState extends AbtractState{
    JMenuItem level1 = new JMenuItem("Easy");
    @Override
    public void doAction(LevelState levelState, Game game) {
        level1.addActionListener(e -> game.setLevel(new dev.tahkeer.tadmer.model.levels.EasyLevel()));
        level1.setAccelerator(KeyStroke.getKeyStroke('1'));
        levelState.setLevel(this);
    }
    @Override
    public JMenuItem getLevel() {
        return level1;
    }
}
