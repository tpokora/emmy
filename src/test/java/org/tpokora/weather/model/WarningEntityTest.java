package org.tpokora.weather.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tpokora.common.utils.DateUtils;
import org.tpokora.weather.common.WarningStrings;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WarningEntityTest {

    private Warning warning;
    private WarningEntity warningEntity;
    private Coordinates coordinates;

    @BeforeEach
    public void setup() {
        warning = Warning.builder()
                .name("")
                .level(1)
                .period(LocalDateTime.now(), LocalDateTime.now().plusDays(1))
                .build();

        coordinates = new Coordinates(11.11, 22.22);
    }

    @Test
    public void testWarningEntity() {
        warningEntity = WarningEntity.valueOf(warning);
        warningEntity.setId(1);
        warningEntity.setLatitude(coordinates.getLatitude());
        warningEntity.setLongitude(coordinates.getLongitude());
        Assertions.assertEquals(1, warningEntity.getId());
        Assertions.assertEquals(warning.getName(), warningEntity.getName());
        Assertions.assertEquals(warning.getLevel(), warningEntity.getLevel());
        Assertions.assertEquals(coordinates.getLongitude(), warningEntity.getLongitude());
        Assertions.assertEquals(coordinates.getLatitude(), warningEntity.getLatitude());
        Assertions.assertEquals(DateUtils.parseDateToString(warning.getPeriod().getFrom()), DateUtils.parseDateToString(warningEntity.getStart()));
        Assertions.assertEquals(DateUtils.parseDateToString(warning.getPeriod().getTo()), DateUtils.parseDateToString(warningEntity.getEnd()));

        String expectedWarningString = "WarningEntity{" +
                "id=" + warningEntity.getId() +
                ", longitude=" + warningEntity.getLongitude() +
                ", latitude=" + warningEntity.getLatitude() +
                ", name='" + warningEntity.getName() + '\'' +
                ", level=" + warningEntity.getLevel() +
                ", start=" + warningEntity.getStart().format(DateTimeFormatter.ofPattern(WarningStrings.WARNINGS_DATE_FORMAT)) +
                ", end=" + warningEntity.getEnd().format(DateTimeFormatter.ofPattern(WarningStrings.WARNINGS_DATE_FORMAT)) +
                '}';

        Assertions.assertEquals(expectedWarningString, warningEntity.toString());
    }
}