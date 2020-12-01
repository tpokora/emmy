package org.tpokora.application.rates.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tpokora.application.common.utils.FileReaderUtils;
import org.tpokora.common.utils.DateUtils;
import org.tpokora.persistance.entity.rates.RateEntity;

class GoldAPIMapperTest {

    private GoldAPIMapper goldAPIMapper;
    private String goldAPIMapperStringResponse;

    @BeforeEach
    public void setup() {
        goldAPIMapper = new GoldAPIMapper();
        goldAPIMapperStringResponse = FileReaderUtils.fileToString("rates/goldAPIRatesReponse.json");
    }

    @Test
    void testGoldAPIRateMap() {
        RateEntity rateEntity = goldAPIMapper.map(goldAPIMapperStringResponse);
        Assertions.assertEquals("XAU", rateEntity.getFrom());
        Assertions.assertEquals("USD", rateEntity.getTo());
        Assertions.assertEquals(1912.4, rateEntity.getValue());
        Assertions.assertEquals("2020-10-09 09:30:00", DateUtils.parseDateToString(rateEntity.getTimestamp()));
        Assertions.assertEquals("Gold API Rate", rateEntity.getName());
    }

    @Test
    void testGoldAPIRateMap_failedToParseJSON() {
        Assertions.assertNull(goldAPIMapper.map("a;a"));
    }
}