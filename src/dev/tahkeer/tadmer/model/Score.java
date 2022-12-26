package dev.tahkeer.tadmer.model;

import dev.tahkeer.tadmer.controller.Game;

public class Score {
    private Score() {}
    private int score=0;

    public void addScore() {
        score+=10;
    }
    public void removeScore(){
        score-=10;
    }

    public  int getScore() {
        return score;
    }

    private static final class ScoreHolder {
        private static final Score score;
        static {
            score=new Score();
        }
    }

    public static Score getInstance() {
        return ScoreHolder.score;
    }
}
