package org.tpokora.application.weather.common;

import org.tpokora.persistance.entity.weather.ForecastEntity;

public class ForecastTestsHelper {

    public static ForecastEntity createForecastEntity(String locationName, double longitude, double latitude) {
        ForecastEntity forecastEntity = new ForecastEntity();
        forecastEntity.setLocation(locationName);
        forecastEntity.setLongitude(longitude);
        forecastEntity.setLatitude(latitude);

        return forecastEntity;
    }
}
