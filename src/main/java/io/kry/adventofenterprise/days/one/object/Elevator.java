package io.kry.adventofenterprise.days.one.object;

import io.kry.adventofenterprise.exceptions.LiftException;

public class Elevator {

    private int currentFloor = 0;

    public int processInstruction(char c) throws LiftException {
        if (c == '(')
            currentFloor++;
        else if (c == ')')
            currentFloor--;
        else
            throw new LiftException("Illegal lift instruction.");
        return currentFloor;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }
}
