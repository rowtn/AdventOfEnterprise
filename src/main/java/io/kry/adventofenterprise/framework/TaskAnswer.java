package io.kry.adventofenterprise.framework;

public class TaskAnswer {

    Object partOne, partTwo;

    public TaskAnswer(Object partOne, Object partTwo) {
        this.partOne = partOne;
        this.partTwo = partTwo;
    }

    public Object getPartOneAnswer() {
        return partOne;
    }

    public Object getPartTwoAnswer() {
        return partTwo;
    }
}
