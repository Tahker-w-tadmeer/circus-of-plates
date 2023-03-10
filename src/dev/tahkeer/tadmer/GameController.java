package dev.tahkeer.tadmer;

import dev.tahkeer.tadmer.controller.Game;
import dev.tahkeer.tadmer.model.levels.EasyLevel;
import dev.tahkeer.tadmer.model.levels.HardLevel;
import dev.tahkeer.tadmer.model.levels.MediumLevel;
import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.World;
import java.util.function.Supplier;
import javax.swing.*;

public final class GameController {
    
    private Supplier<World> gameSupplier;
    private JFrame gameFrame;
    private GameEngine.GameController gameController;
    
    public GameController(Supplier<World> gameSupplier) {
        this.gameSupplier = gameSupplier;
    }
    
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New");
        newMenuItem.setAccelerator(KeyStroke.getKeyStroke('n'));
        JMenuItem pauseMenuItem = new JMenuItem("Pause");
        pauseMenuItem.setAccelerator(KeyStroke.getKeyStroke('p'));
        JMenuItem resumeMenuItem = new JMenuItem("Resume");
        resumeMenuItem.setAccelerator(KeyStroke.getKeyStroke('r'));

        menu.add(newMenuItem);
        menu.addSeparator();
        menu.add(pauseMenuItem);
        menu.add(resumeMenuItem);
        menuBar.add(menu);
        
        newMenuItem.addActionListener(e -> {
            gameFrame.dispose();

            start();

//            addLevelsMenu(menuBar);
        });
        pauseMenuItem.addActionListener(e -> gameController.pause());

        resumeMenuItem.addActionListener(e -> gameController.resume());

//        addLevelsMenu(menuBar);
        
        return menuBar;
    }

//    private void addLevelsMenu(JMenuBar menuBar) {
//        JMenu menu = new JMenu("Levels");
//        JMenuItem level1 = new JMenuItem("Easy");
//        level1.addActionListener(e -> ((Game) this.gameSupplier.get()).setLevel(new EasyLevel()));
//        level1.setAccelerator(KeyStroke.getKeyStroke('1'));
//
//        JMenuItem level2 = new JMenuItem("Medium");
//        level2.addActionListener(e -> ((Game) this.gameSupplier.get()).setLevel(new MediumLevel()));
//        level2.setAccelerator(KeyStroke.getKeyStroke('2'));
//
//        JMenuItem level3 = new JMenuItem("Hard");
//        level3.addActionListener(e -> ((Game) this.gameSupplier.get()).setLevel(new HardLevel()));
//        level3.setAccelerator(KeyStroke.getKeyStroke('3'));
//
//        menu.add(level1);
//        menu.add(level2);
//        menu.add(level3);
//
//        menuBar.add(menu);
//    }

    public void start() {
        JMenuBar menuBar = createMenuBar();

        this.gameSupplier = () -> Game.getInstance(1280, 720);

        this.gameController = GameEngine.start("Circus Of Plates", gameSupplier.get(), menuBar);
        this.gameFrame = (JFrame) menuBar.getParent().getParent().getParent();

//        this.gameFrame.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent windowEvent) {
//                if (JOptionPane.showConfirmDialog(gameFrame, "Are you sure you want to close this game?",
//                        "End Game?", JOptionPane.YES_NO_OPTION,
//                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
//
//                    gameFrame.dispose();
//
//                    //some code to return to game main window.
//                } else {
//                    gameFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//                }
//            }
//        });
    }

    public JFrame getGameFrame() {
        return gameFrame;
    }

    public GameEngine.GameController getGameController() {
        return gameController;
    }

}
