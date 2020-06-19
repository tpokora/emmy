package org.tpokora.storms.services.forecast;

import org.tpokora.storms.model.Forecast;

import java.util.Optional;

public interface IForecastService {

    public Optional<Forecast> getForecast(double longitude, double latitude);
}
