package org.tpokora.storms.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WarningTests {

    public static final String FROM = "2020-01-01 17:00";
    public static final String TO = "2020-01-01 17:00";

    @Test
    @DisplayName("Warning model builder using Period object test")
    public void testWarningBuilderWithPeriod() {
        int level = 2;
        Period period = new Period(FROM, TO, WarningStrings.WARNINGS_DATE_FORMAT);
        Warning warning = Warning.builder()
                .name(WarningStrings.WIND)
                .level(level)
                .period(period)
                .build();

        Assertions.assertEquals(WarningStrings.WIND, warning.getName());
        Assertions.assertEquals(level, warning.getLevel());
        Assertions.assertEquals(period.toString(), warning.getPeriod().toString());

        String warningToStringExpected = String.format("Warning{name='%s', level=%d, period=%s}",
                warning.getName(), warning.getLevel(), warning.getPeriod().toString());

        Assertions.assertEquals(warningToStringExpected, warning.toString());
    }

    @Test
    @DisplayName("Warning model builder using LocalDateTime test")
    public void testWarningBuilderWithLocalDateTime() {
        int level = 2;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(WarningStrings.WARNINGS_DATE_FORMAT);
        Period period = new Period(FROM, TO, WarningStrings.WARNINGS_DATE_FORMAT);
        Warning warning = Warning.builder()
                .name(WarningStrings.WIND)
                .level(level)
                .period(LocalDateTime.parse(FROM, formatter), LocalDateTime.parse(TO, formatter))
                .build();

        Assertions.assertEquals(WarningStrings.WIND, warning.getName());
        Assertions.assertEquals(level, warning.getLevel());
        Assertions.assertEquals(period.toString(), warning.getPeriod().toString());
    }
}
