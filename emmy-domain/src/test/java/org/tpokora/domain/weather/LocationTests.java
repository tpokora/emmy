package org.tpokora.domain.weather;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LocationTests {

    private static final String EMPTY = "";

    @Test
    @DisplayName("Location model tests")
    public void testLocation() {
        Location location = new Location();
        location.setCoordinates(new Coordinates(11.11, 22.22));

        Assertions.assertEquals(EMPTY, location.getName());

        String testCity = "TestCity";
        location.setName(testCity);
        Assertions.assertEquals(testCity, location.getName());

        String expectedString = String.format("Location{name='%s', coordinates=%s}", location.getName(), location.getCoordinates());
        Assertions.assertEquals(expectedString, location.toString());

        Location locationConstructorParams = new Location(location.getCoordinates().getLongitude(), location.getCoordinates().getLatitude());
        locationConstructorParams.setName(testCity);
        Assertions.assertEquals(expectedString, locationConstructorParams.toString());
    }
}
