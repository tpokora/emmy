package org.tpokora.application.rates.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tpokora.application.common.mapper.IJSONMapper;
import org.tpokora.common.utils.DateUtils;
import org.tpokora.persistance.entity.rates.RateEntity;

import java.time.LocalDateTime;
import java.util.Optional;

public class GoldAPIMapper implements IJSONMapper<RateEntity> {

    public static final String METAL = "metal";
    public static final String CURRENCY = "currency";
    public static final String PRICE = "price";
    public static final String DATE = "date";
    public static final String GOLDAPI_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private final Logger LOGGER = LoggerFactory.getLogger(GoldAPIMapper.class);

    @Override
    public RateEntity map(String json) {
        Optional<JsonNode> optionalRootNode = getRootNode(LOGGER, json);

        if (optionalRootNode.isPresent()) {
            JsonNode rootNode = optionalRootNode.get();
            RateEntity rateEntity = new RateEntity();
            rateEntity.setName("Gold API Rate");
            setFrom(rateEntity, rootNode);
            setTo(rateEntity, rootNode);
            setValue(rateEntity, rootNode);
            setTimestamp(rateEntity, rootNode);
            return rateEntity;
        }
        return null;
    }

    private void setFrom(RateEntity rateEntity, JsonNode rootNode) {
        rateEntity.setFrom(rootNode.get(METAL).asText());
    }

    private void setTo(RateEntity rateEntity, JsonNode rootNode) {
        rateEntity.setTo(rootNode.get(CURRENCY).asText());
    }

    private void setValue(RateEntity rateEntity, JsonNode rootNode) {
        rateEntity.setValue(rootNode.get(PRICE).asDouble());
    }

    private void setTimestamp(RateEntity rateEntity, JsonNode rootNode) {
        String dateString = rootNode.get(DATE).asText();
        LocalDateTime localDateTime = DateUtils.parseStringToDate(dateString, GOLDAPI_DATE_FORMAT);
        rateEntity.setTimestamp(localDateTime);
    }
}
