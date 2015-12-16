package io.kry.adventofenterprise.framework;

import io.kry.adventofenterprise.exceptions.TaskException;

public interface Day {
    TaskAnswer solve() throws TaskException;
}
