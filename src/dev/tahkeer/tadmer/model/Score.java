package dev.tahkeer.tadmer.model;

import dev.tahkeer.tadmer.utils.interfaces.ScoreEventListener;

import java.util.ArrayList;

public class Score {

    private Score() {}

    private final ArrayList<ScoreEventListener> listeners = new ArrayList<>();
    private int score = 0;

    public void addListener(ScoreEventListener listener) {
        listeners.add(listener);
    }

    public void addScore() {
        this.setScore(this.getScore() + 10);
    }

    public void removeScore() {
        this.setScore(this.getScore() - 10);
    }

    public void setScore(int score) {
        if(score < 0) {
            this.score = 0;
            return;
        }

        if(score > this.score) {
            listeners.forEach((listener) -> listener.added(this.score, score));
        }

        if(score < this.score) {
            listeners.forEach((listener) -> listener.removed(this.score, score));
        }

        this.score = score;
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
