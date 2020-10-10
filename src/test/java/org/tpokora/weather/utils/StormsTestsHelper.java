package org.tpokora.weather.utils;

import org.tpokora.domain.weather.Period;
import org.tpokora.domain.weather.Warning;

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
