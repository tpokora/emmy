package org.tpokora.storms.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tpokora.common.utils.DateUtils;

import java.time.LocalDateTime;

public class StormEntityTests {

    public static final int TIME = 10;
    public static final String DIRECTION = "E";
    public static final double DISTANCE = 100;
    public static final LocalDateTime TIMESTAMP = LocalDateTime.now();
    public static final int AMOUNT = 20;
    public static final String TEST_CITY = "TestCity";
    public static final String X = "10.10";
    public static final String Y = "11.11";
    private StormResponse stormResponse;
    private StormEntity stormEntity;

    @BeforeEach
    public void setup() {
        stormResponse = new StormResponse();
        stormResponse.setTime(TIME);
        stormResponse.setDirection(DIRECTION);
        stormResponse.setTimestamp(TIMESTAMP);
        stormResponse.setDistance(DISTANCE);
        stormResponse.setAmount(AMOUNT);
        stormEntity = StormEntity.builder()
                .cityName(TEST_CITY)
                .x(X)
                .y(Y)
                .amount(AMOUNT)
                .distance(DISTANCE)
                .direction(DIRECTION)
                .time(TIME)
                .timestamp(TIMESTAMP)
                .build();

    }

    @Test
    public void testStormEntity() {
        String expectedString = "StormEntity{" +
                "cityName='" + TEST_CITY + '\'' +
                ", x='" + X + '\'' +
                ", y='" + Y + '\'' +
                ", amount=" + AMOUNT +
                ", distance=" + DISTANCE +
                ", direction='" + DIRECTION + '\'' +
                ", time=" + TIME +
                ", timestamp=" + DateUtils.parseDateToString(TIMESTAMP) +
                '}';

        Assertions.assertEquals(expectedString, stormEntity.toString());
    }
}
