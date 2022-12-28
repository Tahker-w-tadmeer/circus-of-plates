package dev.tahkeer.tadmer.model.interfaces;

public interface ScoreEventListener {
    void added(int oldScore, int score);
    void removed(int oldScore, int score);
}
