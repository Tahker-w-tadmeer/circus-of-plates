package dev.tahkeer.tadmer.view;

import dev.tahkeer.tadmer.GameController;
import dev.tahkeer.tadmer.controller.Game;
import dev.tahkeer.tadmer.utils.factories.ShapeFactory;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.io.File;
import java.util.concurrent.atomic.AtomicReference;

public class MainMenu extends JFrame {
    private JPanel panel;
    private JButton startButton;
    private JButton chooseDifferentShapesButton;
    private JPanel shapesPanel;

    public static void main(String[] args) {
        new MainMenu();
    }

    public MainMenu() {
        super("Circus of Plates - Thakeer w tadmeer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setVisible(true);
        this.setContentPane(panel);

        this.setBackground(Color.red);

        AtomicReference<File> shapesFolder = new AtomicReference<>();
        shapesFolder.set(new File("./res/shapes/shapes1"));

        renderShapesPanel(shapesFolder.get());

        startButton.addActionListener(e -> {
            this.setVisible(false);

            ShapeFactory.getInstance(shapesFolder.get().getAbsolutePath());
            GameController controller = new GameController(() -> Game.getInstance(1280, 720));
            controller.start();
        });

        startButton.setUI(new BasicButtonUI());

        chooseDifferentShapesButton.setUI(new BasicButtonUI());

        chooseDifferentShapesButton.addActionListener(e -> {
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
                shapesFolder.set(fileChooser.getSelectedFile());

                renderShapesPanel(shapesFolder.get());
            }
        });
    }

    private void renderShapesPanel(File path)
    {
        shapesPanel.removeAll();


        File[] files = path.listFiles();
        if(files == null) {
            return;
        }

        int rows = (int) Math.ceil(files.length / 4.0);
        GridLayout gridLayout = new GridLayout(rows, 4);
        shapesPanel.setLayout(gridLayout);
        for (File file : files) {
            if (file.isFile()) {
                JLabel label = new JLabel();
                label.setIcon(new ImageIcon(file.getAbsolutePath()));
                shapesPanel.add(label);
            }
        }

        shapesPanel.revalidate();
        shapesPanel.repaint();
    }
}
