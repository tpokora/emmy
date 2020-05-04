package org.tpokora.storms.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StormRequestTests {

    @Test
    @DisplayName("StormRequest model tests")
    public void testStormRequest() {
        StormRequest stormRequest = new StormRequest();
        stormRequest.setCoordinates(new Coordinates(11.11, 22.22));
        stormRequest.setDistance(111.11);
        stormRequest.setTime(15);


        String expectedStormRequestString = String.format("StormRequest{x=%s, y=%s, distance=%s, time=%d}",
                stormRequest.getCoordinates().getX(),
                stormRequest.getCoordinates().getY(),
                stormRequest.getDistance(),
                stormRequest.getTime());
        Assertions.assertEquals(expectedStormRequestString, stormRequest.toString());
    }
}
