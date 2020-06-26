package org.tpokora.weather.mapper;

import org.tpokora.weather.model.ForecastEntity;

public interface IForecastMapper {

    public ForecastEntity map(String json);
}
