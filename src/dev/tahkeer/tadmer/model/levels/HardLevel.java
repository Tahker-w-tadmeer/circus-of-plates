package dev.tahkeer.tadmer.model.levels;

import dev.tahkeer.tadmer.utils.interfaces.Level;

import java.util.HashMap;

public class HardLevel implements Level {
    @Override
    public String name() {
        return "Hard";
    }

    @Override
    public int numberOfClowns() {
        return 1;
    }

    @Override
    public int numberOfQueues() {
        return 3;
    }

    @Override
    public int speed() {
        return 5;
    }

    @Override
    public int controlSpeed() {
        return 10;
    }

    @Override
    public int score() {
        return 150;
    }

    @Override
    public Level next() {
        return null;
    }

    @Override
    public Level previous() {
        return new MediumLevel();
    }

    @Override
    public HashMap<String, Object> getProperties() {
        return new HashMap<>(){
            {
                put("name", name());
                put("numberOfClowns", numberOfClowns());
                put("numberOfQueues", numberOfQueues());
                put("speed", speed());
                put("controlSpeed", controlSpeed());
                put("score", score());
                put("next", next());
                put("previous", previous());
            }
        };
    }
}
