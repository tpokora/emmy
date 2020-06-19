package org.tpokora.weather.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PeriodTests {

    public static final String FROM = "2020-01-01 17:00";
    public static final String TO = "2020-01-01 17:00";

    @Test
    @DisplayName("Period model test")
    public void testPeriod() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(WarningStrings.WARNINGS_DATE_FORMAT);
        LocalDateTime localDateTimeFrom = LocalDateTime.parse(FROM, formatter);
        LocalDateTime localDateTimeTo = LocalDateTime.parse(TO, formatter);

        Period periodWithDateTime = new Period(localDateTimeFrom, localDateTimeTo);
        Assertions.assertEquals(FROM, periodWithDateTime.getFromString());
        Assertions.assertEquals(TO, periodWithDateTime.getToString());

        Period periodWitString = new Period(FROM, TO, WarningStrings.WARNINGS_DATE_FORMAT);
        Assertions.assertEquals(localDateTimeFrom, periodWitString.getFrom());
        Assertions.assertEquals(localDateTimeTo, periodWitString.getTo());

        Assertions.assertEquals(periodWithDateTime.toString(), periodWitString.toString());

    }
}
