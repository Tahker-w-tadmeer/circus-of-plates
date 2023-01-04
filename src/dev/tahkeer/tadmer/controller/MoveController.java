package dev.tahkeer.tadmer.controller;

import dev.tahkeer.tadmer.model.Clown;
import dev.tahkeer.tadmer.utils.interfaces.Shape;
import dev.tahkeer.tadmer.model.shapes.Platform;

public class MoveController {
    protected static void move(Game game) {
        for (Shape shape : game.shapes) {
            boolean shapeMoved = false;

            for (Platform platform : game.platforms) {
                if (platform.isShapeLeft(shape)) {
                    shape.setX(shape.getX() + 3);
                    shapeMoved = true;
                    break;
                }

                if (platform.isShapeRight(shape)) {
                    shape.setX(shape.getX() - 3);
                    shapeMoved = true;
                    break;
                }
            }

            if (!shapeMoved) {
                shape.fall();
            }

            for (Clown clown : game.clowns) {

                if (clown.getHighestY() + 40 < game.platforms.get(0).getY()) {
                    game.finish();

                    return;
                }

                game.shapes.removeIf(clown::holds);
            }
        }
    }
}
