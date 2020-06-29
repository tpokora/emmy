package org.tpokora.weather.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tpokora.common.utils.DateUtils;
import org.tpokora.weather.model.ForecastEntity;

import java.util.Optional;

public class OpenWeatherForecastMapper implements IJSONMapper<ForecastEntity> {

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
    public ForecastEntity map(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode;
        try {
            rootNode = objectMapper.readTree(json);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error parsing JSON: {}", json);
            LOGGER.error(e.getMessage());
            return null;
        }

        ForecastEntity forecastEntity = new ForecastEntity();
        setWeather(forecastEntity, rootNode);
        setCoordinates(forecastEntity, rootNode);
        setTemperature(forecastEntity, rootNode);
        setRain(forecastEntity, rootNode);
        setOther(forecastEntity, rootNode);
        return forecastEntity;
    }

    private void setWeather(ForecastEntity forecastEntity, JsonNode rootNode) {
        JsonNode weather = rootNode.get(WEATHER).get(0);
        forecastEntity.setName(weather.get(MAIN).asText());
        forecastEntity.setDescription(weather.get(DESCRIPTION).asText());
    }

    private void setCoordinates(ForecastEntity forecastEntity, JsonNode rootNode) {
        JsonNode coordinates = rootNode.get(COORD);
        forecastEntity.setLongitude(coordinates.get(LON).asDouble());
        forecastEntity.setLatitude(coordinates.get(LAT).asDouble());
    }

    private void setTemperature(ForecastEntity forecastEntity, JsonNode rootNode) {
        JsonNode main = rootNode.get(MAIN);
        forecastEntity.setTemp(main.get(TEMP).asDouble());
        forecastEntity.setFeelTemp(main.get(FEELS_LIKE).asDouble());
        forecastEntity.setMinTemp(main.get(TEMP_MIN).asDouble());
        forecastEntity.setMaxTemp(main.get(TEMP_MAX).asDouble());
    }

    private void setRain(ForecastEntity forecastEntity, JsonNode rootNode) {
        Optional<JsonNode> rain = Optional.ofNullable(rootNode.get("rain"));
        if (rain.isPresent()) {
            Optional<JsonNode> rain1hOptional = Optional.ofNullable(rain.get().get("1h"));
            rain1hOptional.ifPresent(value -> forecastEntity.setRain1h(value.asDouble()));
            Optional<JsonNode> rain3hOptional = Optional.ofNullable(rain.get().get("3h"));
            rain3hOptional.ifPresent(value -> forecastEntity.setRain3h(value.asDouble()));
        }


    }

    private void setOther(ForecastEntity forecastEntity, JsonNode rootNode) {
        JsonNode main = rootNode.get(MAIN);
        forecastEntity.setPressure(main.get(PRESSURE).asInt());
        forecastEntity.setHumidity(main.get(HUMIDITY).asInt());
        JsonNode wind = rootNode.get(WIND);
        forecastEntity.setWind(wind.get(SPEED).asDouble());
        forecastEntity.setTimestamp(DateUtils.getCurrentLocalDateTime());
    }
}
