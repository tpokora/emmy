package org.tpokora.application.weather.forecast.suppliers;

import org.tpokora.persistance.entity.weather.ForecastEntity;

import java.util.Optional;

public interface IForecastSupplier {

    Optional<ForecastEntity> getForecast(double longitude, double latitude);
}
