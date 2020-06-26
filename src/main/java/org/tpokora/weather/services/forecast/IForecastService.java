package org.tpokora.weather.services.forecast;

import org.tpokora.weather.model.ForecastEntity;

import java.util.Optional;

public interface IForecastService {

    public Optional<ForecastEntity> getForecast(double longitude, double latitude);
}
