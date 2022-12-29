package dev.tahkeer.tadmer.utils;

import java.time.Duration;

public class AsyncWaiter {
    private long lastTime = System.currentTimeMillis();
    private Duration duration;
    private final AsyncTask task;

    public AsyncWaiter(AsyncTask task, Duration duration) {
        this.duration = duration;

        this.task = task;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public boolean execute() {
        boolean shouldExecute = System.currentTimeMillis() - lastTime > duration.toMillis();

        if(shouldExecute) {
            task.run();

            lastTime = System.currentTimeMillis();
        }

        return shouldExecute;
    }

    public interface AsyncTask {
        void run();
    }
}
