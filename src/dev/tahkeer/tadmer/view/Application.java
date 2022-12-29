package dev.tahkeer.tadmer.view;

import dev.tahkeer.tadmer.controller.Game;
import eg.edu.alexu.csd.oop.game.GameEngine;

import javax.swing.*;

public class Application {

    public static void main(String[] args) {

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Game");

        GameEngine.GameController controller = GameEngine.start("Circus Of Plates", Game.getInstance(), menuBar);

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
}