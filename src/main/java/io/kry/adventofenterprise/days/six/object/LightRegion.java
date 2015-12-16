package io.kry.adventofenterprise.days.six.object;

import io.kry.adventofenterprise.days.six.enums.StateInstruction;

public class LightRegion {

    private Light[] subset;

    public LightRegion(Light[] subset) {
        this.subset = subset;
    }

    public void applyInstruction(StateInstruction instruction) {
        for (Light aSubset : subset) {
            aSubset.applyState(instruction);
        }
    }
}
