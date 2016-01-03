package io.kry.adventofenterprise.days.fourteen.builders;

import io.kry.adventofenterprise.days.fourteen.object.Reindeer;

public class ReindeerBuilder {
    private int speed, moveTime, waitTime;

    public ReindeerBuilder movetime(String moveTime) {
        this.moveTime = Integer.parseInt(moveTime);
        return this;
    }

    public ReindeerBuilder waitTime(String waitTime) {
        this.waitTime = Integer.parseInt(waitTime);
        return this;
    }

    public ReindeerBuilder speed(String speed) {
        this.speed = Integer.parseInt(speed);
        return this;
    }

    public Reindeer build() {
        return new Reindeer(speed, moveTime, waitTime);
    }
}
