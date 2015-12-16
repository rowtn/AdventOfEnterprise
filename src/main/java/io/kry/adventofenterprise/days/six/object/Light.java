package io.kry.adventofenterprise.days.six.object;

import io.kry.adventofenterprise.days.six.enums.State;
import io.kry.adventofenterprise.days.six.enums.StateInstruction;

public class Light {

    private State state = State.OFF;
    private int brightness = 0;
    private LightSwitchboard.Mode mode;

    public Light(LightSwitchboard.Mode mode) {
        this.mode = mode;
    }

    private void toggle() {
        this.state = this.state == State.OFF ? State.ON : State.OFF;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void applyState(StateInstruction state) {
        if (mode == LightSwitchboard.Mode.BOOLEAN) {
            if (state == StateInstruction.TOGGLE) toggle();
            else setState(state.getRelatedState());
        } else if (mode == LightSwitchboard.Mode.BRIGHTNESS) {
            switch (state) {
                case TURN_ON:
                    brightness++;
                    break;
                case TURN_OFF:
                    brightness--;
                    break;
                case TOGGLE:
                    brightness += 2;
                    break;
            }
            if (brightness < 0) brightness = 0;
        }
    }

    public State getState() {
        return state;
    }

    public int getBrightness() {
        return brightness;
    }
}
