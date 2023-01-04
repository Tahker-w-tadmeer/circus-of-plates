package dev.tahkeer.tadmer.view;

import dev.tahkeer.tadmer.GameController;
import dev.tahkeer.tadmer.controller.Game;
import dev.tahkeer.tadmer.utils.factories.ShapeFactory;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;

public class Application {

    public static void main(String[] args) {
        Game game = Game.getInstance(1280, 720);

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
            ShapeFactory.getInstance("./res/shapes/shapes1");
        }

        GameController controller = new GameController(() -> game);
        controller.start();
    }
}