package org.tpokora.weather.mapper;

import org.tpokora.weather.model.Forecast;

public interface IForecastMapper {

    public Forecast map(String json);
}
