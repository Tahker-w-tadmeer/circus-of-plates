package dev.tahkeer.tadmer.utils;

import dev.tahkeer.tadmer.utils.interfaces.State;

public class LevelState {
    private State state;
    public void setLevel(State state){
        this.state = state;
    }
    public AbtractState getState() {
        return (AbtractState) state;
    }
}
