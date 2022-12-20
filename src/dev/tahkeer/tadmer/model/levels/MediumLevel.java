package dev.tahkeer.tadmer.model.levels;

import dev.tahkeer.tadmer.model.interfaces.Level;

public class MediumLevel implements Level {
    @Override
    public String name() {
        return "Medium";
    }

    @Override
    public int numberOfClowns() {
        return 2;
    }

    @Override
    public int numberOfQueues() {
        return 1;
    }

    @Override
    public int speed() {
        return 10;
    }

    @Override
    public int controlSpeed() {
        return 10;
    }

    @Override
    public Level next() {
        return new HardLevel();
    }

    @Override
    public Level previous() {
        return new EasyLevel();
    }
}
