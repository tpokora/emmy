package org.tpokora.weather.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tpokora.common.utils.DateUtils;
import org.tpokora.common.utils.FileReaderUtils;
import org.tpokora.weather.model.ForecastEntity;

import java.time.LocalDateTime;

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
        Assertions.assertEquals("Vakaga Prefecture", forecastEntity.getLocation());
        Assertions.assertEquals("Clear", forecastEntity.getName());
        Assertions.assertEquals("clear sky", forecastEntity.getDescription());
        Assertions.assertEquals(28.55, forecastEntity.getTemp());
        Assertions.assertEquals(29.04, forecastEntity.getFeelTemp());
        Assertions.assertEquals(28.55, forecastEntity.getMinTemp());
        Assertions.assertEquals(28.55, forecastEntity.getMaxTemp());
        Assertions.assertEquals(1012, forecastEntity.getPressure());
        Assertions.assertEquals(56, forecastEntity.getHumidity());
        Assertions.assertEquals(3.85, forecastEntity.getWind());
        Assertions.assertEquals(0.27, forecastEntity.getRain1h());
        Assertions.assertEquals(1.2, forecastEntity.getRain3h());
        Assertions.assertEquals(22.22, forecastEntity.getLongitude());
        Assertions.assertEquals(11.11, forecastEntity.getLatitude());

        forecastEntity.setId(1);
        LocalDateTime currentLocalDateTime = DateUtils.getCurrentLocalDateTime();
        forecastEntity.setTimestamp(currentLocalDateTime);
        String expectedForecastString =
                String.format("ForecastEntity{id=%d, location='%s', name='%s', description='%s', temp=%s, feelTemp=%s, minTemp=%s, maxTemp=%s, pressure=%d, humidity=%d, wind=%s, rain1h=%s, rain3h=%s, longitude=%s, latitude=%s, timestamp=%s}",
                        forecastEntity.getId(), forecastEntity.getLocation(), forecastEntity.getName(),
                        forecastEntity.getDescription(), forecastEntity.getTemp(), forecastEntity.getFeelTemp(),
                        forecastEntity.getMinTemp(), forecastEntity.getMaxTemp(), forecastEntity.getPressure(),
                        forecastEntity.getHumidity(), forecastEntity.getWind(), forecastEntity.getRain1h(),
                        forecastEntity.getRain3h(), forecastEntity.getLongitude(), forecastEntity.getLatitude(),
                        currentLocalDateTime);
        Assertions.assertEquals(expectedForecastString, forecastEntity.toString());
    }

    @Test
    public void testMap_failureParsingJson() {
        String invalidJson = "invalidJson";
        ForecastEntity forecastEntity = openWeatherForecastMapper.map(invalidJson);
        Assertions.assertNull(forecastEntity);
    }
}