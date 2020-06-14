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
    public static final String X = "10.10";
    public static final String Y = "11.11";
    public static final int ID = 100;
    private StormEntity stormEntity;

    @BeforeEach
    public void setup() {
        stormEntity = StormEntity.builder()
                .id(ID)
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
                "id=" + ID +
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

    @Test
    public void testStormEntitySettersAndGetters() {
        StormEntity stormEntity = new StormEntity();
        stormEntity.setId(ID);
        stormEntity.setLongitude(X);
        stormEntity.setLatitude(Y);
        stormEntity.setAmount(AMOUNT);
        stormEntity.setDistance(DISTANCE);
        stormEntity.setDirection(DIRECTION);
        stormEntity.setTime(TIME);
        stormEntity.setTimestamp(TIMESTAMP);

        Assertions.assertEquals(ID, stormEntity.getId());
        Assertions.assertEquals(X, stormEntity.getLongitude());
        Assertions.assertEquals(Y, stormEntity.getLatitude());
        Assertions.assertEquals(AMOUNT, stormEntity.getAmount());
        Assertions.assertEquals(DISTANCE, stormEntity.getDistance());
        Assertions.assertEquals(DIRECTION, stormEntity.getDirection());
        Assertions.assertEquals(TIME, stormEntity.getTime());
        Assertions.assertEquals(TIMESTAMP, stormEntity.getTimestamp());
    }
}
