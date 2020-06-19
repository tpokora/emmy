package org.tpokora.weather.services.forecast;

import org.tpokora.weather.model.Forecast;

import java.util.Optional;

public interface IForecastService {

    public Optional<Forecast> getForecast(double longitude, double latitude);
}
