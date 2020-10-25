package org.tpokora.domain.rates;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tpokora.common.utils.DateUtils;

import java.time.LocalDateTime;

public class RateTests {

    @Test
    void testRate() {
        String name = "USDtoEUR";
        LocalDateTime timestamp = LocalDateTime.now();
        double value = 1924.5;
        String from = "USD";
        String to = "EUR";
        Rate rate = new Rate(name, timestamp, value, from, to);

        Assertions.assertEquals(expectedString(rate), rate.toString());

        rate = new Rate();
        rate.setName(name);
        rate.setTimestamp(timestamp);
        rate.setValue(value);
        rate.setFrom(from);
        rate.setTo(to);

        Assertions.assertEquals(expectedString(rate), rate.toString());
    }

    private String expectedString(Rate rate) {
        String expectedString = "Rate{" +
                "name='" + rate.getName() + '\'' +
                ", timestamp=" + DateUtils.parseDateToString(rate.getTimestamp()) +
                ", value=" + rate.getValue() +
                ", from='" + rate.getFrom() + '\'' +
                ", to='" + rate.getTo() + '\'' +
                '}';;

        return expectedString;
    }
}
