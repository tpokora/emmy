package org.tpokora.weather.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinatesConverterTest {

    @Test
    void testConvertDecimalDegreeToDM() {
        double decimalDegree = 19.8265702;
        double expectedDMS = 19.49;

        Assertions.assertEquals(expectedDMS, CoordinatesConverter.convertDecimalDegreeToDM(decimalDegree));
    }
}