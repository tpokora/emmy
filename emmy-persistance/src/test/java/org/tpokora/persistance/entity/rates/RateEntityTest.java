package org.tpokora.persistance.entity.rates;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class RateEntityTest {

    @Test
    public void testRateEntityToString() {
        int id = 1;
        String name = "USDtoEUR";
        LocalDateTime timestamp = LocalDateTime.now();
        double value = 1924.5;
        String from = "USD";
        String to = "EUR";
        RateEntity rate = new RateEntity(name, from, to, value, timestamp);
        rate.setId(id);

        Assertions.assertEquals(expectedString(rate), rate.toString());

        rate = new RateEntity(id, name, from, to, value, timestamp);

        Assertions.assertEquals(expectedString(rate), rate.toString());

        rate = new RateEntity();
        rate.setName(name);
        rate.setTimestamp(timestamp);
        rate.setValue(value);
        rate.setFrom(from);
        rate.setTo(to);

        Assertions.assertEquals(expectedString(rate), rate.toString());
    }

    private String expectedString(RateEntity rate) {
        String expectedString = "RateEntity{" +
                "name='" + rate.getName() + '\'' +
                ", from='" + rate.getFrom() + '\'' +
                ", to='" + rate.getTo() + '\'' +
                ", value=" + rate.getValue() +
                ", timestamp=" + rate.getTimestamp() +
                ", id=" + rate.getId() +
                '}';;

        return expectedString;
    }
}
