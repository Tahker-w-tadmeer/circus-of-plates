package dev.tahkeer.tadmer.model.interfaces;

import eg.edu.alexu.csd.oop.game.GameObject;

import java.awt.*;

public interface Shape extends GameObject {
    Color getColor();
    void setColor(Color color);
}
