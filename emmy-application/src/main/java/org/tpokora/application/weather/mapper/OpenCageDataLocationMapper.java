package org.tpokora.application.weather.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tpokora.application.common.mapper.IJSONMapper;
import org.tpokora.domain.weather.Coordinates;
import org.tpokora.domain.weather.Location;

import java.io.IOException;


public class OpenCageDataLocationMapper implements IJSONMapper<Location> {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenCageDataLocationMapper.class);

    @Override
    public Location map(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode;
        try {
            rootNode = objectMapper.readTree(json);
        } catch (IOException e) {
            LOGGER.error("Error parsing JSON: {}", json);
            LOGGER.error(e.getMessage());
            return null;
        }
        Location location = new Location();
        setName(location, rootNode);
        setCoordinates(location, rootNode);
        return location;
    }

    private void setName(Location location, JsonNode rootNode) {
        JsonNode results = rootNode.get("results").get(0);
        JsonNode components = results.get("components");
        String name = getName(components);
        location.setName(name);
    }

    private String getName(JsonNode components) {
        JsonNode name = components.get("city");
        if (name == null) {
            name = components.get("hamlet");
        }

        return name != null ? name.asText() : "";
    }

    private void setCoordinates(Location location, JsonNode rootNode) {
        JsonNode results = rootNode.get("results").get(0);
        JsonNode geometry = results.get("geometry");
        Coordinates coordinates = new Coordinates(geometry.get("lng").asDouble(), geometry.get("lat").asDouble());
        location.setCoordinates(coordinates);
    }
}
