package org.tpokora.weather.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.tpokora.weather.model.Forecast;

public class OpenWeatherForecastMapper implements IForecastMapper {

    public static final String WEATHER = "weather";
    public static final String MAIN = "main";
    public static final String DESCRIPTION = "description";
    public static final String COORD = "coord";
    public static final String LON = "lon";
    public static final String LAT = "lat";
    public static final String TEMP = "temp";
    public static final String FEELS_LIKE = "feels_like";
    public static final String TEMP_MIN = "temp_min";
    public static final String TEMP_MAX = "temp_max";
    public static final String PRESSURE = "pressure";
    public static final String HUMIDITY = "humidity";
    public static final String WIND = "wind";
    public static final String SPEED = "speed";

    @Override
    public Forecast map(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(json);

        Forecast forecast = new Forecast();
        setWeather(forecast, rootNode);
        setCoordinates(forecast, rootNode);
        setTemperature(forecast, rootNode);
        setOther(forecast, rootNode);
        return forecast;
    }

    private void setWeather(Forecast forecast, JsonNode rootNode) {
        JsonNode weather = rootNode.get(WEATHER).get(0);
        forecast.setName(weather.get(MAIN).asText());
        forecast.setDescription(weather.get(DESCRIPTION).asText());
    }

    private void setCoordinates(Forecast forecast, JsonNode rootNode) {
        JsonNode coordinates = rootNode.get(COORD);
        forecast.setLongitude(coordinates.get(LON).asDouble());
        forecast.setLatitude(coordinates.get(LAT).asDouble());
    }

    private void setTemperature(Forecast forecast, JsonNode rootNode) {
        JsonNode main = rootNode.get(MAIN);
        forecast.setTemp(main.get(TEMP).asDouble());
        forecast.setFeelTemp(main.get(FEELS_LIKE).asDouble());
        forecast.setMinTemp(main.get(TEMP_MIN).asDouble());
        forecast.setMaxTemp(main.get(TEMP_MAX).asDouble());
    }

    private void setOther(Forecast forecast, JsonNode rootNode) {
        JsonNode main = rootNode.get(MAIN);
        forecast.setPressure(main.get(PRESSURE).asInt());
        forecast.setHumidity(main.get(HUMIDITY).asInt());
        JsonNode wind = rootNode.get(WIND);
        forecast.setWind(wind.get(SPEED).asDouble());
    }
}
