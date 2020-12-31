package org.tpokora.persistance.entity.weather;

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
    public static final double LONGITUDE = 10.10;
    public static final double LATITUDE = 11.11;
    public static final double LONGITUDE_DM = 10.101010;
    public static final double LATITUDE_DM = 11.111111;
    public static final int ID = 100;
    private StormEntity stormEntity;

    @BeforeEach
    public void setup() {
        stormEntity = StormEntity.builder()
                .id(ID)
                .longitude(LONGITUDE)
                .latitude(LATITUDE)
                .longitudeDM(LONGITUDE_DM)
                .latitudeDM(LATITUDE_DM)
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
                ", longitude=" + LONGITUDE +
                ", latitude=" + LATITUDE +
                ", longitudeDM=" + LONGITUDE_DM +
                ", latitudeDM=" + LATITUDE_DM +
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
        stormEntity.setLongitude(LONGITUDE);
        stormEntity.setLatitude(LATITUDE);
        stormEntity.setLongitudeDM(LONGITUDE_DM);
        stormEntity.setLatitudeDM(LATITUDE_DM);
        stormEntity.setAmount(AMOUNT);
        stormEntity.setDistance(DISTANCE);
        stormEntity.setDirection(DIRECTION);
        stormEntity.setTime(TIME);
        stormEntity.setTimestamp(TIMESTAMP);

        Assertions.assertEquals(ID, stormEntity.getId());
        Assertions.assertEquals(LONGITUDE, stormEntity.getLongitude());
        Assertions.assertEquals(LATITUDE, stormEntity.getLatitude());
        Assertions.assertEquals(LONGITUDE_DM, stormEntity.getLongitudeDM());
        Assertions.assertEquals(LATITUDE_DM, stormEntity.getLatitudeDM());
        Assertions.assertEquals(AMOUNT, stormEntity.getAmount());
        Assertions.assertEquals(DISTANCE, stormEntity.getDistance());
        Assertions.assertEquals(DIRECTION, stormEntity.getDirection());
        Assertions.assertEquals(TIME, stormEntity.getTime());
        Assertions.assertEquals(TIMESTAMP, stormEntity.getTimestamp());
    }
}
