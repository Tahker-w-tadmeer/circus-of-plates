package dev.tahkeer.tadmer.utils;

import dev.tahkeer.tadmer.controller.Game;
import dev.tahkeer.tadmer.utils.interfaces.State;

import javax.swing.*;

public class HardLevelState extends AbtractState {
    JMenuItem level3 = new JMenuItem("Hard");
    @Override
    public void doAction(LevelState levelState, Game game) {
        level3.addActionListener(e -> game.setLevel(new dev.tahkeer.tadmer.model.levels.HardLevel()));
        level3.setAccelerator(KeyStroke.getKeyStroke('3'));
        levelState.setLevel(this);
    }
    @Override
    public JMenuItem getLevel() {
        return level3;
    }
}
