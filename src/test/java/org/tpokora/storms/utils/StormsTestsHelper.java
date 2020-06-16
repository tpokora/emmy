package org.tpokora.storms.utils;

import org.tpokora.storms.model.Period;
import org.tpokora.storms.model.Warning;

public class StormsTestsHelper {

    public static Warning createWarning(String warningName, int level, Period period) {
        Warning warning = Warning.builder()
                .name(warningName)
                .level(level)
                .period(period)
                .build();

        return warning;
    }
}
