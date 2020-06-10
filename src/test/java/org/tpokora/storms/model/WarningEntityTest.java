package org.tpokora.storms.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        Assertions.assertEquals(warning.getPeriod().getFrom(), warningEntity.getFrom());
        Assertions.assertEquals(warning.getPeriod().getTo(), warningEntity.getTo());

        String expectedWarningString = "WarningEntity{" +
                "id=" + warningEntity.getId() +
                ", name='" + warningEntity.getName() + '\'' +
                ", level=" + warningEntity.getLevel() +
                ", to=" + warningEntity.getTo().format(DateTimeFormatter.ofPattern(WarningStrings.WARNINGS_DATE_FORMAT)) +
                ", from=" + warningEntity.getFrom().format(DateTimeFormatter.ofPattern(WarningStrings.WARNINGS_DATE_FORMAT)) +
                '}';

        Assertions.assertEquals(expectedWarningString, warningEntity.toString());
    }
}
