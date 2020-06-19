package org.tpokora.weather.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tpokora.common.utils.FileReaderUtils;
import org.tpokora.weather.model.Forecast;

class OpenWeatherForecastMapperTest {

    private OpenWeatherForecastMapper openWeatherForecastMapper;
    private String openWeatherStringResponse;

    @BeforeEach
    public void setup() {
        openWeatherForecastMapper = new OpenWeatherForecastMapper();
        openWeatherStringResponse = FileReaderUtils.fileToString("weather/forecast/openweatherResponse.json");
    }

    @Test
    void testMap() throws JsonProcessingException {
        Forecast forecast = openWeatherForecastMapper.map(openWeatherStringResponse);
        Assertions.assertNotNull(forecast);
        Assertions.assertEquals(forecast.getName(), "Clear");
        Assertions.assertEquals(forecast.getDescription(), "clear sky");
        Assertions.assertEquals(forecast.getTemp(), 28.55);
        Assertions.assertEquals(forecast.getFeelTemp(), 29.04);
        Assertions.assertEquals(forecast.getMinTemp(), 28.55);
        Assertions.assertEquals(forecast.getMaxTemp(), 28.55);
        Assertions.assertEquals(forecast.getPressure(), 1012);
        Assertions.assertEquals(forecast.getHumidity(), 56);
        Assertions.assertEquals(forecast.getWind(), 3.85);
        Assertions.assertEquals(forecast.getLongitude(), 22.22);
        Assertions.assertEquals(forecast.getLatitude(), 11.11);
    }
}