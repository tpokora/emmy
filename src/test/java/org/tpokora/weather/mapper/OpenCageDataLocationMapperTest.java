package org.tpokora.weather.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tpokora.common.utils.FileReaderUtils;
import org.tpokora.weather.model.Location;

class OpenCageDataLocationMapperTest {

    private OpenCageDataLocationMapper openCageDataLocationMapper;
    private String openCageDataLocationStringResponse;

    @BeforeEach
    public void setup() {
        openCageDataLocationMapper = new OpenCageDataLocationMapper();
        openCageDataLocationStringResponse = FileReaderUtils.fileToString("weather/location/opencageDataResponse.json");
    }

    @Test
    void testMap() {
        Location expectedLocation = new Location(16.3655487, 52.695614);
        expectedLocation.setName("Warszawa");
        Location location = openCageDataLocationMapper.map(openCageDataLocationStringResponse);
        Assertions.assertEquals(expectedLocation.toString(), location.toString());

    }

    @Test
    public void testMap_failureParsingJson() {
        String invalidJson = "invalidJson";
        Location location = openCageDataLocationMapper.map(invalidJson);
        Assertions.assertNull(location);
    }
}