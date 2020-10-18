package org.tpokora.persistance.services.weather;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tpokora.domain.weather.Coordinates;
import org.tpokora.domain.weather.common.WarningStrings;
import org.tpokora.persistance.entity.weather.WarningEntity;
import org.tpokora.persistance.repositories.weather.IWarningRepository;
import org.tpokora.persistance.services.BaseServiceTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class WarningDaoServiceTests extends BaseServiceTest {

    @Autowired
    private IWarningRepository warningRepository;

    private WarningDaoService warningDaoService;

    private Coordinates coordinates;
    private WarningEntity WARNING_ENTITY;

    @BeforeEach
    public void setup() {
        warningDaoService = new WarningDaoService(warningRepository);
        coordinates = new Coordinates(11.11, 22.22);
        WARNING_ENTITY = new WarningEntity();
        WARNING_ENTITY.setLongitude(coordinates.getLongitude());
        WARNING_ENTITY.setLatitude(coordinates.getLatitude());
        WARNING_ENTITY.setName(WarningStrings.FROST);
        WARNING_ENTITY.setLevel(1);
        WARNING_ENTITY.setStart(LocalDateTime.now());
        WARNING_ENTITY.setEnd(LocalDateTime.now().plusDays(2));
    }

    @AfterEach
    public void teardown() {
        warningRepository.deleteAll();
    }

    @Test
    public void testSaveWarningEntity() {
        Optional<WarningEntity> savedWarningEntity = warningDaoService.save(WARNING_ENTITY);
        Assertions.assertEquals(WARNING_ENTITY.toString(), savedWarningEntity.get().toString());
    }

    @Test
    public void testGetWarningEntity() {
        Optional<WarningEntity> savedWarningEntity = warningDaoService.save(WARNING_ENTITY);
        Assertions.assertEquals(WARNING_ENTITY.toString(), savedWarningEntity.get().toString());

        Optional<WarningEntity> warningEntity = warningDaoService.getById(savedWarningEntity.get().getId());
        Assertions.assertEquals(savedWarningEntity.get().toString(), warningEntity.get().toString());
    }

    @Test
    public void testFindTheSameWarningEntity() {
        Optional<WarningEntity> savedWarningEntity = warningDaoService.save(WARNING_ENTITY);
        Assertions.assertTrue(warningDaoService.getSameWarning(WARNING_ENTITY).isPresent());
    }

    @Test
    public void testSaveWarningEntityWithTheSameValues() {
        WarningEntity firstWarningEntity = WarningEntity.valueOf(WARNING_ENTITY);
        WarningEntity secondWarningEntity = WarningEntity.valueOf(WARNING_ENTITY);
        Assertions.assertEquals(firstWarningEntity.toString(), secondWarningEntity.toString());

        warningDaoService.save(firstWarningEntity);
        warningDaoService.save(secondWarningEntity);

        Assertions.assertEquals(1, warningDaoService.getAll().size());

    }

    @Test
    public void testSaveWarningEntityList() {
        WarningEntity firstWarningEntity = WarningEntity.valueOf(WARNING_ENTITY);
        WarningEntity secondWarningEntity = WarningEntity.valueOf(WARNING_ENTITY);
        secondWarningEntity.setName(WarningStrings.HEAT);
        List<WarningEntity> warningEntityList = Arrays.asList(firstWarningEntity, secondWarningEntity);
        List<WarningEntity> savedWarningEntities = warningDaoService.saveAll(warningEntityList);

        Assertions.assertEquals(warningEntityList.size(), savedWarningEntities.size());
        Assertions.assertEquals(warningEntityList.size(), warningDaoService.getAll().size());
        Assertions.assertEquals(warningEntityList.toString(), savedWarningEntities.toString());

    }

    @Test
    public void testSaveWarningEntityListWithOneDuplicate() {
        WarningEntity firstWarningEntity = WarningEntity.valueOf(WARNING_ENTITY);
        WarningEntity secondWarningEntity = WarningEntity.valueOf(WARNING_ENTITY);
        List<WarningEntity> warningEntityList = Arrays.asList(firstWarningEntity, secondWarningEntity);
        List<WarningEntity> savedWarningEntities = warningDaoService.saveAll(warningEntityList);

        Assertions.assertEquals(1, savedWarningEntities.size());
        Assertions.assertEquals(1, warningDaoService.getAll().size());

    }
}
