package org.tpokora.application.weather.properties;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tpokora.application.common.properties.PropertiesTests;

public class OpenCagePropertiesTests extends PropertiesTests {

    private OpenCageDataProperties openCageDataProperties;

    @BeforeEach
    public void setup() {
        openCageDataProperties = new OpenCageDataProperties();
    }

    @Test
    public void testGetAndSet() {
        openCageDataProperties.setValue(TEST_KEY, TEST_VALUE);

        Assertions.assertFalse(openCageDataProperties.getOpencage().isEmpty());
        Assertions.assertEquals(TEST_VALUE, openCageDataProperties.getValue(TEST_KEY));
    }
}
