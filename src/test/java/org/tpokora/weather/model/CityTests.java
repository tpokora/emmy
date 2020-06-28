package org.tpokora.weather.model;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CityTests {

    @Test
    @DisplayName("City model tests")
    public void testCity() {
        City city = new City();
        city.setCoordinates(new Coordinates(11.11, 22.22));

        Assertions.assertEquals(Strings.EMPTY, city.getName());

        String testCity = "TestCity";
        city.setName(testCity);
        Assertions.assertEquals(testCity, city.getName());
    }
}
