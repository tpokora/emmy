package org.tpokora.common.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilsTest {

    @Test
    void testParseDateToString() {
        String expectedDate = "2020-10-11 12:13:14";
        LocalDateTime now = LocalDateTime.of(2020, 10, 11, 12, 13, 14);
        Assertions.assertEquals(expectedDate, DateUtils.parseDateToString(now));

    }

    @Test
    void testGetMinuteDifference() {
        int minutes = 10;
        LocalDateTime now = DateUtils.getCurrentLocalDateTime();
        LocalDateTime futurePlus10Minutes = LocalDateTime.now().plusMinutes(minutes);
        Assertions.assertEquals(minutes, DateUtils.getMinuteDifference(futurePlus10Minutes, now));
    }

    @Test
    void testParseStringToDate() {
        String stringDate = "2020-10-11 12:13:14";
        LocalDateTime localDateTime = DateUtils.parseStringToDate(stringDate);
        Assertions.assertEquals(2020, localDateTime.getYear());
        Assertions.assertEquals(10, localDateTime.getMonthValue());
        Assertions.assertEquals(11, localDateTime.getDayOfMonth());
        Assertions.assertEquals(12, localDateTime.getHour());
        Assertions.assertEquals(13, localDateTime.getMinute());
        Assertions.assertEquals(14, localDateTime.getSecond());
    }
}