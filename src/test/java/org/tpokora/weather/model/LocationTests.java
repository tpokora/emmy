package org.tpokora.weather.model;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LocationTests {

    @Test
    @DisplayName("Location model tests")
    public void testLocation() {
        Location location = new Location();
        location.setCoordinates(new Coordinates(11.11, 22.22));

        Assertions.assertEquals(Strings.EMPTY, location.getName());

        String testCity = "TestCity";
        location.setName(testCity);
        Assertions.assertEquals(testCity, location.getName());
    }
}
