package org.tpokora.weather.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tpokora.common.utils.FileReaderUtils;
import org.tpokora.weather.model.City;
import org.tpokora.weather.model.ForecastEntity;

import static org.junit.jupiter.api.Assertions.*;

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
        City expectedCity = new City(16.3655487, 52.695614);
        expectedCity.setName("Warszawa");
        City city = openCageDataLocationMapper.map(openCageDataLocationStringResponse);
        Assertions.assertEquals(expectedCity.toString(), city.toString());

    }

    @Test
    public void testMap_failureParsingJson() {
        String invalidJson = "invalidJson";
        City city = openCageDataLocationMapper.map(invalidJson);
        Assertions.assertNull(city);
    }
}