package org.tpokora.weather.utils;

import org.tpokora.weather.model.Period;
import org.tpokora.weather.model.Warning;

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
