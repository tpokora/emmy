package org.tpokora.application.rates.properties;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tpokora.application.common.properties.PropertiesTests;

public class GoldAPIPropertiesTests extends PropertiesTests {

    private GoldAPIProperties goldAPIProperties;

    @BeforeEach
    public void setup() {
        goldAPIProperties = new GoldAPIProperties();
    }

    @Test
    public void testGetAndSet() {
        goldAPIProperties.setValue(TEST_KEY, TEST_VALUE);

        Assertions.assertFalse(goldAPIProperties.getGoldapi().isEmpty());
        Assertions.assertEquals(TEST_VALUE, goldAPIProperties.getValue(TEST_KEY));
    }
}
