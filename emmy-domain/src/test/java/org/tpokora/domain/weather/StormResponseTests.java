package org.tpokora.domain.weather;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tpokora.common.utils.DateUtils;

import java.time.LocalDateTime;

public class StormResponseTests {

    @Test
    @DisplayName("StormResponse model tests")
    public void testStormResponse() {
        int amount = 10;
        double distance = 111.11;
        String direction = "E";
        int time = 15;
        LocalDateTime timestamp = LocalDateTime.now();
        StormResponse stormResponse = new StormResponse();
        stormResponse.setAmount(amount);
        stormResponse.setDistance(distance);
        stormResponse.setDirection(direction);
        stormResponse.setTime(time);
        stormResponse.setTimestamp(timestamp);

        Assertions.assertEquals(amount, stormResponse.getAmount());
        Assertions.assertEquals(distance, stormResponse.getDistance());
        Assertions.assertEquals(direction, stormResponse.getDirection());
        Assertions.assertEquals(time, stormResponse.getTime());
        Assertions.assertEquals(timestamp, stormResponse.getTimestamp());

        String expectedStormResponseString = String.format("StormResponse{amount=%d, distance=%s, direction='%s', time=%d, timestamp=%s}",
                amount, distance, direction, time, DateUtils.parseDateToString(timestamp));

        Assertions.assertEquals(expectedStormResponseString, stormResponse.toString());

    }
}
