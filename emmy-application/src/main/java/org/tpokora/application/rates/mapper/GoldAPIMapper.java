package org.tpokora.application.rates.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tpokora.application.common.mapper.IJSONMapper;
import org.tpokora.common.utils.DateUtils;
import org.tpokora.persistance.entity.rates.RateEntity;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GoldAPIMapper implements IJSONMapper<RateEntity> {

    private final Logger LOGGER = LoggerFactory.getLogger(GoldAPIMapper.class);

    @Override
    public RateEntity map(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode;
        try {
            rootNode = objectMapper.readTree(json);
        } catch (IOException e) {
            LOGGER.error("Error parsing JSON: {}", json);
            LOGGER.error(e.getMessage());
            return null;
        }

        RateEntity rateEntity = new RateEntity();
        rateEntity.setName("Gold API Rate");
        setFrom(rateEntity, rootNode);
        setTo(rateEntity, rootNode);
        setValue(rateEntity, rootNode);
        setTimestamp(rateEntity, rootNode);
        return rateEntity;
    }

    private void setFrom(RateEntity rateEntity, JsonNode rootNode) {
        rateEntity.setFrom(rootNode.get("metal").asText());
    }

    private void setTo(RateEntity rateEntity, JsonNode rootNode) {
        rateEntity.setTo(rootNode.get("currency").asText());
    }

    private void setValue(RateEntity rateEntity, JsonNode rootNode) {
        rateEntity.setValue(rootNode.get("price").asDouble());
    }

    private void setTimestamp(RateEntity rateEntity, JsonNode rootNode) {
        String dateString = rootNode.get("date").asText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        LocalDateTime localDateTime = LocalDateTime.parse(dateString, formatter);
        rateEntity.setTimestamp(localDateTime);
    }
}
