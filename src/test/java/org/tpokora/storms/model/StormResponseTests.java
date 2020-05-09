package org.tpokora.storms.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StormResponseTests {

    @Test
    @DisplayName("StormResponse model tests")
    public void testStormResponse() {
        int amount = 10;
        double distance = 111.11;
        String direction = "E";
        int time = 15;
        StormResponse stormResponse = new StormResponse();
        stormResponse.setAmount(amount);
        stormResponse.setDistance(distance);
        stormResponse.setDirection(direction);
        stormResponse.setTime(time);

        Assertions.assertEquals(amount, stormResponse.getAmount());
        Assertions.assertEquals(distance, stormResponse.getDistance());
        Assertions.assertEquals(direction, stormResponse.getDirection());
        Assertions.assertEquals(time, stormResponse.getTime());

        String expectedStormResponseString = String.format("StormResponse{amount=%d, distance=%s, direction='%s', time=%d}",
                amount, distance, direction, time);

        Assertions.assertEquals(expectedStormResponseString, stormResponse.toString());

    }
}
