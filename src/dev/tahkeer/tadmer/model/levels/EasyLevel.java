package dev.tahkeer.tadmer.model.levels;

import dev.tahkeer.tadmer.utils.interfaces.Level;

import java.util.HashMap;

public class EasyLevel implements Level {
    public String name() {
        return "Easy";
    }

    public int numberOfClowns() {
        return 1;
    }

    public int numberOfQueues() {
        return 2;
    }

    public int speed() {
        return 10;
    }

    public int controlSpeed() {
        return 20;
    }

    public int score() {
        return 50;
    }

    public Level next() {
        return new MediumLevel();
    }

    public Level previous() {
        return null;
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
