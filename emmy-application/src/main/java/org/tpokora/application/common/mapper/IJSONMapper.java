package org.tpokora.application.common.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.Optional;

public interface IJSONMapper<T> {

    T map(String json);

    default Optional<JsonNode> getRootNode(Logger logger, String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode;
        try {
            rootNode = objectMapper.readTree(json);
        } catch (IOException e) {
            logger.error("Error parsing JSON: {}", json);
            logger.error(e.getMessage());
            return Optional.empty();
        }

        return Optional.of(rootNode);
    }
}
