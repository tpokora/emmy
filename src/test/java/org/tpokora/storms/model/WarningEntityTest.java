package org.tpokora.storms.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tpokora.common.utils.DateUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WarningEntityTest {

    private Warning warning;
    private WarningEntity warningEntity;

    @BeforeEach
    public void setup() {
        warning = Warning.builder()
                .name("")
                .level(1)
                .period(LocalDateTime.now(), LocalDateTime.now().plusDays(1))
                .build();
    }

    @Test
    public void testWarningEntity() {
        warningEntity = WarningEntity.valueOf(warning);
        warningEntity.setId(1);
        Assertions.assertEquals(1, warningEntity.getId());
        Assertions.assertEquals(warning.getName(), warningEntity.getName());
        Assertions.assertEquals(warning.getLevel(), warningEntity.getLevel());
        Assertions.assertEquals(DateUtils.parseDateToString(warning.getPeriod().getFrom()), DateUtils.parseDateToString(warningEntity.getStart()));
        Assertions.assertEquals(DateUtils.parseDateToString(warning.getPeriod().getTo()), DateUtils.parseDateToString(warningEntity.getEnd()));

        String expectedWarningString = "WarningEntity{" +
                "id=" + warningEntity.getId() +
                ", name='" + warningEntity.getName() + '\'' +
                ", level=" + warningEntity.getLevel() +
                ", start=" + warningEntity.getStart().format(DateTimeFormatter.ofPattern(WarningStrings.WARNINGS_DATE_FORMAT)) +
                ", end=" + warningEntity.getEnd().format(DateTimeFormatter.ofPattern(WarningStrings.WARNINGS_DATE_FORMAT)) +
                '}';

        Assertions.assertEquals(expectedWarningString, warningEntity.toString());
    }
}
