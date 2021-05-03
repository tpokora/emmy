package org.tpokora.application.weather.web.api;

import org.tpokora.persistance.entity.weather.ForecastEntity;

public class ForecastMapper {

    public Forecast toApi(ForecastEntity forecastEntity) {
        return new Forecast(forecastEntity.getId(), forecastEntity.getLocation(),
                forecastEntity.getName(), forecastEntity.getDescription(), forecastEntity.getTemp(),
                forecastEntity.getFeelTemp(), forecastEntity.getMinTemp(), forecastEntity.getMaxTemp(),
                forecastEntity.getPressure(), forecastEntity.getHumidity(), forecastEntity.getWind(),
                forecastEntity.getRain1h(), forecastEntity.getRain3h(), forecastEntity.getLongitude(),
                forecastEntity.getLatitude(), forecastEntity.getTimestamp().toString());
    }
}
