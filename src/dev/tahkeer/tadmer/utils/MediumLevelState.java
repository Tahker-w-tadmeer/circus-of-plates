package dev.tahkeer.tadmer.utils;

import dev.tahkeer.tadmer.controller.Game;

import javax.swing.*;

public class MediumLevelState extends AbtractState {
    JMenuItem level2 = new JMenuItem("Medium");
    @Override
    public void doAction(LevelState levelState, Game game) {
        level2.addActionListener(e -> game.setLevel(new dev.tahkeer.tadmer.model.levels.MediumLevel()));
        level2.setAccelerator(KeyStroke.getKeyStroke('2'));
        levelState.setLevel(this);
    }
    @Override
    public JMenuItem getLevel() {
        return level2;
    }
}
