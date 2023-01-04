package dev.tahkeer.tadmer.view;

import dev.tahkeer.tadmer.controller.Game;
import dev.tahkeer.tadmer.model.levels.EasyLevel;
import dev.tahkeer.tadmer.model.levels.HardLevel;
import dev.tahkeer.tadmer.model.levels.MediumLevel;
import dev.tahkeer.tadmer.utils.factories.ShapeFactory;
import eg.edu.alexu.csd.oop.game.GameEngine;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;

public class Application {

    public static void main(String[] args) {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory();
            }

            @Override
            public String getDescription() {
                return "Folders containing images";
            }
        });

        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showOpenDialog(null);

        if(result == JFileChooser.APPROVE_OPTION) {
            ShapeFactory.getInstance(fileChooser.getSelectedFile().getAbsolutePath());
        } else {
            ShapeFactory.getInstance("./res/shapes");
        }

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
        JMenuItem level1 = new JMenuItem("Easy");
        level1.addActionListener(e -> game.setLevel(new EasyLevel()));
        level1.setAccelerator(KeyStroke.getKeyStroke('1'));

        JMenuItem level2 = new JMenuItem("Medium");
        level2.addActionListener(e -> game.setLevel(new MediumLevel()));
        level2.setAccelerator(KeyStroke.getKeyStroke('2'));

        JMenuItem level3 = new JMenuItem("Hard");
        level3.addActionListener(e -> game.setLevel(new HardLevel()));
        level3.setAccelerator(KeyStroke.getKeyStroke('3'));

        menu.add(level1);
        menu.add(level2);
        menu.add(level3);

        menuBar.add(menu);
    }
}