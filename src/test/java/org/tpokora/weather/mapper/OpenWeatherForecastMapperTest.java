package org.tpokora.weather.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tpokora.common.utils.DateUtils;
import org.tpokora.common.utils.FileReaderUtils;
import org.tpokora.weather.model.ForecastEntity;

class OpenWeatherForecastMapperTest {

    private OpenWeatherForecastMapper openWeatherForecastMapper;
    private String openWeatherStringResponse;

    @BeforeEach
    public void setup() {
        openWeatherForecastMapper = new OpenWeatherForecastMapper();
        openWeatherStringResponse = FileReaderUtils.fileToString("weather/forecast/openweatherResponse.json");
    }

    @Test
    void testMap() {
        ForecastEntity forecastEntity = openWeatherForecastMapper.map(openWeatherStringResponse);
        Assertions.assertNotNull(forecastEntity);
        Assertions.assertEquals(forecastEntity.getName(), "Clear");
        Assertions.assertEquals(forecastEntity.getDescription(), "clear sky");
        Assertions.assertEquals(forecastEntity.getTemp(), 28.55);
        Assertions.assertEquals(forecastEntity.getFeelTemp(), 29.04);
        Assertions.assertEquals(forecastEntity.getMinTemp(), 28.55);
        Assertions.assertEquals(forecastEntity.getMaxTemp(), 28.55);
        Assertions.assertEquals(forecastEntity.getPressure(), 1012);
        Assertions.assertEquals(forecastEntity.getHumidity(), 56);
        Assertions.assertEquals(forecastEntity.getWind(), 3.85);
        Assertions.assertEquals(forecastEntity.getRain1h(), 0.27);
        Assertions.assertEquals(forecastEntity.getRain3h(), 1.2);
        Assertions.assertEquals(forecastEntity.getLongitude(), 22.22);
        Assertions.assertEquals(forecastEntity.getLatitude(), 11.11);

        forecastEntity.setId(1);
        forecastEntity.setTimestamp(DateUtils.getCurrentLocalDateTime());
        String expectedForecastString =
                String.format("ForecastEntity{id=%d, name='%s', description='%s', temp=%s, feelTemp=%s, minTemp=%s, maxTemp=%s, pressure=%d, humidity=%d, wind=%s, rain1h=%s, rain3h=%s, longitude=%s, latitude=%s, timestamp=%s}",
                        forecastEntity.getId(), forecastEntity.getName(), forecastEntity.getDescription(), forecastEntity.getTemp(),
                        forecastEntity.getFeelTemp(), forecastEntity.getMinTemp(), forecastEntity.getMaxTemp(), forecastEntity.getPressure(),
                        forecastEntity.getHumidity(), forecastEntity.getWind(), forecastEntity.getRain1h(), forecastEntity.getRain3h(),
                        forecastEntity.getLongitude(), forecastEntity.getLatitude(),
                        DateUtils.parseDateToString(forecastEntity.getTimestamp()));
        Assertions.assertEquals(expectedForecastString, forecastEntity.toString());
    }

    @Test
    public void testMap_failureParsingJson() {
        String invalidJson = "invalidJson";
        ForecastEntity forecastEntity = openWeatherForecastMapper.map(invalidJson);
        Assertions.assertNull(forecastEntity);
    }
}