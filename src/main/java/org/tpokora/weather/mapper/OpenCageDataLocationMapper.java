package org.tpokora.weather.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.bind.api.ErrorListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tpokora.weather.model.City;
import org.tpokora.weather.model.Coordinates;

public class OpenCageDataLocationMapper implements IJSONMapper<City> {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenCageDataLocationMapper.class);

    @Override
    public City map(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode;
        try {
            rootNode = objectMapper.readTree(json);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error parsing JSON: {}", json);
            LOGGER.error(e.getMessage());
            return null;
        }
        City city = new City();
        setName(city, rootNode);
        setCoordinates(city, rootNode);
        return city;
    }

    private void setName(City city, JsonNode rootNode) {
        JsonNode results = rootNode.get("results").get(0);
        JsonNode components = results.get("components");
        city.setName(components.get("hamlet").asText());
    }

    private void setCoordinates(City city, JsonNode rootNode) {
        JsonNode results = rootNode.get("results").get(0);
        JsonNode geometry = results.get("geometry");
        Coordinates coordinates = new Coordinates(geometry.get("lng").asDouble(), geometry.get("lat").asDouble());
        city.setCoordinates(coordinates);
    }
}
