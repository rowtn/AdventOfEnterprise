package io.kry.adventofenterprise.days.six.enums;

import io.kry.adventofenterprise.exceptions.ParseInstructionException;

public enum StateInstruction {

    TURN_ON("turn on", State.ON), TURN_OFF("turn off", State.OFF), TOGGLE("toggle", null);

    private final String instruction;
    private final State state;

    StateInstruction(String instruction, State state) {
        this.instruction = instruction;
        this.state = state;
    }

    public static StateInstruction fromString(String s) throws ParseInstructionException {
        for (StateInstruction instruction : values()) {
            if (s.startsWith(instruction.instruction)) return instruction;
        }
        throw new ParseInstructionException("Could not parse instruction for day six");
    }

    public State getRelatedState() {
        return state;
    }
}
