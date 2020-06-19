package org.tpokora.weather.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.tpokora.weather.model.Forecast;

public interface IForecastMapper {

    public Forecast map(String json) throws JsonProcessingException;
}
