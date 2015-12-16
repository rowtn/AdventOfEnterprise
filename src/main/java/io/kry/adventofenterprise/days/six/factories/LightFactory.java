package io.kry.adventofenterprise.days.six.factories;

import io.kry.adventofenterprise.days.six.object.Light;
import io.kry.adventofenterprise.days.six.enums.State;
import io.kry.adventofenterprise.days.six.object.LightSwitchboard;

public class LightFactory {

    public static Light createLight(final State state, LightSwitchboard.Mode mode) {
        return new Light(mode) {{
            setState(state);
        }
        };
    }

}
