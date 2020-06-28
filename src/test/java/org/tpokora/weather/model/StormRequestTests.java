package org.tpokora.weather.model;

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


        String expectedStormRequestString = String.format("StormRequest{longitude=%s, latitude=%s, distance=%s, time=%d}",
                stormRequest.getCoordinates().getLongitude(),
                stormRequest.getCoordinates().getLatitude(),
                stormRequest.getDistance(),
                stormRequest.getTime());
        Assertions.assertEquals(expectedStormRequestString, stormRequest.toString());

        stormRequest = new StormRequest(0, 0, 12, 1);
        stormRequest.setLongitude(11.22);
        stormRequest.setLatitude(22.11);

        expectedStormRequestString = String.format("StormRequest{longitude=%s, latitude=%s, distance=%s, time=%d}",
                stormRequest.getLongitude(),
                stormRequest.getLatitude(),
                stormRequest.getDistance(),
                stormRequest.getTime());
        Assertions.assertEquals(expectedStormRequestString, stormRequest.toString());
    }
}
