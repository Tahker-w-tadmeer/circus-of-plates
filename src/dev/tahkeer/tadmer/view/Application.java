package dev.tahkeer.tadmer.view;

import dev.tahkeer.tadmer.controller.Game;
import dev.tahkeer.tadmer.model.levels.EasyLevel;
import dev.tahkeer.tadmer.model.levels.HardLevel;
import dev.tahkeer.tadmer.model.levels.MediumLevel;
import dev.tahkeer.tadmer.utils.EasyLevelState;
import dev.tahkeer.tadmer.utils.HardLevelState;
import dev.tahkeer.tadmer.utils.LevelState;
import dev.tahkeer.tadmer.utils.MediumLevelState;
import eg.edu.alexu.csd.oop.game.GameEngine;

import javax.swing.*;

public class Application {

    public static void main(String[] args) {

        JMenuBar menuBar = new JMenuBar();
        Game game = Game.getInstance();

        GameEngine.GameController controller = GameEngine.start("Circus Of Plates", game, menuBar);

        addControlsMenu(controller, menuBar);
        addLevelsMenu(game, menuBar);
    }

    private static void addControlsMenu(GameEngine.GameController controller, JMenuBar menuBar) {
        JMenu menu = new JMenu("Game");
        JMenuItem pause = new JMenuItem("Pause");
        pause.addActionListener(e -> controller.pause());
        pause.setAccelerator(KeyStroke.getKeyStroke('p'));

        JMenuItem resume = new JMenuItem("Resume");
        resume.addActionListener(e -> controller.resume());
        resume.setAccelerator(KeyStroke.getKeyStroke('r'));
        menu.add(pause);
        menu.add(resume);

        menuBar.add(menu);
    }

    // Add levels menu
    private static void addLevelsMenu(Game game, JMenuBar menuBar) {
        JMenu menu = new JMenu("Levels");
        LevelState levelState=new LevelState();
        EasyLevelState easyLevel=new EasyLevelState();
        MediumLevelState mediumLevel=new MediumLevelState();
        HardLevelState hardLevel=new HardLevelState();
        easyLevel.doAction(levelState,game);
        menu.add(levelState.getState().getLevel());
        menuBar.add(menu);
        mediumLevel.doAction(levelState,game);
        menu.add(levelState.getState().getLevel());
        menuBar.add(menu);
        hardLevel.doAction(levelState,game);
        menu.add(levelState.getState().getLevel());
        menuBar.add(menu);
    }
}