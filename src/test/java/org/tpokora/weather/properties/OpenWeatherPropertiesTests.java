package org.tpokora.weather.properties;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tpokora.application.weather.properties.OpenWeatherProperties;

public class OpenWeatherPropertiesTests extends PropertiesTests{

    private OpenWeatherProperties openWeatherProperties;

    @BeforeEach
    public void setup() {
        openWeatherProperties = new OpenWeatherProperties();
    }

    @Test
    public void testGetAndSet() {
        openWeatherProperties.setValue(TEST_KEY, TEST_VALUE);

        Assertions.assertFalse(openWeatherProperties.getOpenweather().isEmpty());
        Assertions.assertEquals(TEST_VALUE, openWeatherProperties.getValue(TEST_KEY));
    }
}
