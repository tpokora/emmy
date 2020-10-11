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
}