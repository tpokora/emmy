package org.tpokora.application.weather.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.tpokora.application.common.mapper.IJSONMapper;
import org.tpokora.domain.weather.Coordinates;
import org.tpokora.domain.weather.Location;

import java.util.Optional;

@Component("openCageDataLocationMapper")
public class OpenCageDataLocationMapper implements IJSONMapper<Location> {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenCageDataLocationMapper.class);

    @Override
    public Location map(String json) {
        Optional<JsonNode> optionalRootNode = getRootNode(LOGGER, json);
        if (optionalRootNode.isPresent()) {
            JsonNode rootNode = optionalRootNode.get();
            JsonNode result = getResult(rootNode);
            Location location = new Location();
            setName(location, result);
            setCoordinates(location, result);
            return location;
        }
        return null;
    }

    private JsonNode getResult(JsonNode rootNode) {
        JsonNode results = rootNode.get("results");
        for (int i = 0; i < results.size(); i++) {
            JsonNode result = results.get(i);
            JsonNode components = result.get("components");
            String type = components.get("_type").asText();
            if ("city".equals(type) || "town".equals(type) || "village".equals(type)) {
                return result;
            }
        }

        return null;
    }

    private void setName(Location location, JsonNode result) {
        JsonNode components = result.get("components");
        String name = getName(components);
        location.setName(name);
    }

    private String getName(JsonNode components) {
        String type = components.get("_type").asText();
        JsonNode name = null;
        if ("city".equals(type)) {
            name = components.get("city");
            if (name == null) {
                name = components.get("town");
            }
        }

        if ("village".equals(type)) {
            name = components.get("village");

        }

        return name != null ? name.asText() : "";
    }

    private void setCoordinates(Location location, JsonNode result) {
        JsonNode geometry = result.get("geometry");
        Coordinates coordinates = new Coordinates(geometry.get("lng").asDouble(), geometry.get("lat").asDouble());
        location.setCoordinates(coordinates);
    }
}
