package org.tpokora.application.weather.forecast;

import org.tpokora.persistance.entity.weather.ForecastEntity;

import java.util.Optional;

public interface IForecastService {

    Optional<ForecastEntity> getForecast(double longitude, double latitude);
}
