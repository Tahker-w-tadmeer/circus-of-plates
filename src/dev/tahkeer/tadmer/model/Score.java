package dev.tahkeer.tadmer.model;


import dev.tahkeer.tadmer.model.interfaces.Model;
import dev.tahkeer.tadmer.model.interfaces.ModelEventListner;

import java.util.ArrayList;

public class Score implements Model {

    private Score() {}
    public static ArrayList<ModelEventListner> listeners = new ArrayList<>();

    public static void addListener(ModelEventListner toAdd) {
        listeners.add(toAdd)
        ;}


    private int score = 0;

    public void addScore() {
        for (ModelEventListner hl : listeners)
            hl.createdModel(this);
        score += 10;
    }


    public void removeScore() {
        score -= 10;
    }

    public int getScore() {
        return score;
    }

    private static final class ScoreHolder {
        private static final Score score;

        static {
            score = new Score();
        }
    }

    public static Score getInstance() {
        return ScoreHolder.score;
    }
}
