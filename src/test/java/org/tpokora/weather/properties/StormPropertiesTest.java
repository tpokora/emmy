package org.tpokora.weather.properties;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tpokora.services.weather.properties.StormProperties;

class StormPropertiesTest extends PropertiesTests {

    private StormProperties stormProperties;

    @BeforeEach
    public void setup() {
        stormProperties = new StormProperties();
    }

    @Test
    public void testGetAndSet() {
        stormProperties.setValue(TEST_KEY, TEST_VALUE);

        Assertions.assertFalse(stormProperties.getStorm().isEmpty());
        Assertions.assertEquals(TEST_VALUE, stormProperties.getValue(TEST_KEY));
    }

}