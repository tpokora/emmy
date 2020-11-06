package org.tpokora.application.weather.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tpokora.domain.weather.common.CoordinatesConverter;

class CoordinatesConverterTest {

    @Test
    void testConvertDecimalDegreeToDM() {
        double decimalDegree = 19.8265702;
        double expectedDMS = 19.49;

        Assertions.assertEquals(expectedDMS, CoordinatesConverter.convertDecimalDegreeToDM(decimalDegree));

        Assertions.assertEquals(0.0, CoordinatesConverter.convertDecimalDegreeToDM(0.0));
        Assertions.assertEquals(19.0, CoordinatesConverter.convertDecimalDegreeToDM(19.0));
    }
}