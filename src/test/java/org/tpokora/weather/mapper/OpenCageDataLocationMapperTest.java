package org.tpokora.weather.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tpokora.common.utils.FileReaderUtils;
import org.tpokora.weather.model.City;

import static org.junit.jupiter.api.Assertions.*;

class OpenCageDataLocationMapperTest {

    private OpenCageDataLocationMapper openWeatherForecastMapper;
    private String openCageDataLocationStringResponse;

    @BeforeEach
    public void setup() {
        openWeatherForecastMapper = new OpenCageDataLocationMapper();
        openCageDataLocationStringResponse = FileReaderUtils.fileToString("weather/location/opencageDataResponse.json");
    }

    @Test
    void map() {
        City expectedCity = new City(16.3655487, 52.695614);
        expectedCity.setName("Warszawa");
        City city = openWeatherForecastMapper.map(openCageDataLocationStringResponse);
        Assertions.assertEquals(expectedCity.toString(), city.toString());

    }
}