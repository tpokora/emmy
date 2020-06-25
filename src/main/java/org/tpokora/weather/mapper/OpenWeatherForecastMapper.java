package org.tpokora.weather.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tpokora.common.utils.DateUtils;
import org.tpokora.weather.model.Forecast;

import java.util.Optional;

public class OpenWeatherForecastMapper implements IForecastMapper {

    private final Logger LOGGER = LoggerFactory.getLogger(OpenWeatherForecastMapper.class);

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
    public Forecast map(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode;
        try {
            rootNode = objectMapper.readTree(json);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error parsing JSON: {}", json);
            LOGGER.error(e.getMessage());
            return null;
        }

        Forecast forecast = new Forecast();
        setWeather(forecast, rootNode);
        setCoordinates(forecast, rootNode);
        setTemperature(forecast, rootNode);
        setRain(forecast, rootNode);
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

    private void setRain(Forecast forecast, JsonNode rootNode) {
        Optional<JsonNode> rain = Optional.ofNullable(rootNode.get("rain"));
        if (rain.isPresent()) {
            Optional<JsonNode> rain1hOptional = Optional.ofNullable(rain.get().get("1h"));
            rain1hOptional.ifPresent(value -> forecast.setRain1h(value.asDouble()));
            Optional<JsonNode> rain3hOptional = Optional.ofNullable(rain.get().get("3h"));
            rain3hOptional.ifPresent(value -> forecast.setRain3h(value.asDouble()));
        }


    }

    private void setOther(Forecast forecast, JsonNode rootNode) {
        JsonNode main = rootNode.get(MAIN);
        forecast.setPressure(main.get(PRESSURE).asInt());
        forecast.setHumidity(main.get(HUMIDITY).asInt());
        JsonNode wind = rootNode.get(WIND);
        forecast.setWind(wind.get(SPEED).asDouble());
        forecast.setTimestamp(DateUtils.getCurrentLocalDateTime());
    }
}
