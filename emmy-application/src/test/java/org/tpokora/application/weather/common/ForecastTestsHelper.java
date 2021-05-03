package org.tpokora.application.weather.common;

import org.tpokora.persistance.entity.weather.ForecastEntity;

import java.time.LocalDateTime;

public class ForecastTestsHelper {

    public static ForecastEntity createForecastEntity(String locationName, double longitude, double latitude) {
        ForecastEntity forecastEntity = new ForecastEntity();
        forecastEntity.setName("testName");
        forecastEntity.setLocation(locationName);
        forecastEntity.setLongitude(longitude);
        forecastEntity.setLatitude(latitude);
        forecastEntity.setDescription("testDescription");
        forecastEntity.setTemp(0);
        forecastEntity.setFeelTemp(1);
        forecastEntity.setMinTemp(2);
        forecastEntity.setMaxTemp(3);
        forecastEntity.setPressure(1000);
        forecastEntity.setHumidity(10);
        forecastEntity.setWind(15);
        forecastEntity.setTimestamp(LocalDateTime.now());

        return forecastEntity;
    }
}
