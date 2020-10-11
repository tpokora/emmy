package org.tpokora.domain.weather;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tpokora.domain.weather.Coordinates;
import org.tpokora.domain.weather.StormRequest;

public class StormRequestTests {

    public static final String EXPECTED_STRING_FORMAT = "StormRequest{coordinates=Coordinates{longitude=%s, latitude=%s, longitudeDM=%s, latitudeDM=%s}, distance=%s, time=%d}";

    @Test
    @DisplayName("StormRequest model tests")
    public void testStormRequest() {
        StormRequest stormRequest = new StormRequest();
        stormRequest.setCoordinates(new Coordinates(11.11, 22.22));
        stormRequest.setDistance(111.11);
        stormRequest.setTime(15);

        Assertions.assertEquals(expectedStormRequestString(stormRequest), stormRequest.toString());

        stormRequest = new StormRequest(0, 0, 12, 1);
        stormRequest.setLongitude(11.22);
        stormRequest.setLatitude(22.11);

        Assertions.assertEquals(expectedStormRequestString(stormRequest), stormRequest.toString());

        double longitude = 11.22;
        double longitudeDM = 11.13;
        double latitude = 22.11;
        double latitudeDM = 22.6;
        stormRequest = new StormRequest(new Coordinates(longitude, latitude), 12, 1);
        Assertions.assertEquals(expectedStormRequestString(stormRequest), stormRequest.toString());
        Assertions.assertEquals(longitude, stormRequest.getLongitude());
        Assertions.assertEquals(latitude, stormRequest.getLatitude());
        Assertions.assertEquals(longitudeDM, stormRequest.getLongitudeDM());
        Assertions.assertEquals(latitudeDM, stormRequest.getLatitudeDM());
    }

    private String expectedStormRequestString(StormRequest stormRequest) {
        String expectedStormRequestString = String.format(EXPECTED_STRING_FORMAT,
                stormRequest.getCoordinates().getLongitude(),
                stormRequest.getCoordinates().getLatitude(),
                stormRequest.getCoordinates().getLongitudeDM(),
                stormRequest.getCoordinates().getLatitudeDM(),
                stormRequest.getDistance(),
                stormRequest.getTime());

        return expectedStormRequestString;
    }
}
