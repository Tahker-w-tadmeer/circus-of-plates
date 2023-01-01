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

    public void execute() {
        if(System.currentTimeMillis() - lastTime > duration.toMillis()) {
            task.run();

            lastTime = System.currentTimeMillis();
        }
    }

    public interface AsyncTask {
        void run();
    }
}
