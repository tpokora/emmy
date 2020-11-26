package org.tpokora.persistance.common.utils;

import org.tpokora.common.utils.DateUtils;
import org.tpokora.persistance.entity.weather.ForecastEntity;

import java.time.LocalDateTime;

public class ForecastTestsHelper {

    public static ForecastEntity createForecast() {
        return createForecast(LocalDateTime.now());
    }

    public static ForecastEntity createForecast(LocalDateTime timestamp) {
        ForecastEntity forecastEntity = new ForecastEntity();

        forecastEntity.setId(0);
        forecastEntity.setName("testName");
        forecastEntity.setLocation("testLocation");
        forecastEntity.setDescription("testDescription");
        forecastEntity.setTemp(1.1);
        forecastEntity.setFeelTemp(1.2);
        forecastEntity.setMinTemp(0.9);
        forecastEntity.setMaxTemp(2.9);
        forecastEntity.setPressure(1000);
        forecastEntity.setHumidity(10);
        forecastEntity.setWind(10.1);
        forecastEntity.setRain1h(0.27);
        forecastEntity.setRain3h(1.27);
        forecastEntity.setLongitude(11.11);
        forecastEntity.setLatitude(22.11);
        forecastEntity.setTimestamp(timestamp);
        return forecastEntity;
    }
}
