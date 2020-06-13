package org.tpokora.storms.dao;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.tpokora.common.services.BaseServiceTest;
import org.tpokora.storms.model.WarningEntity;
import org.tpokora.storms.model.WarningStrings;

import java.time.LocalDateTime;
import java.util.Optional;

public class WarningDaoServiceTests extends BaseServiceTest {

    @Autowired
    private WarningRepository warningRepository;

    private WarningDaoService warningDaoService;

    private WarningEntity WARNING_ENTITY;

    @BeforeEach
    public void setup() {
        warningDaoService = new WarningDaoService(warningRepository);
        WARNING_ENTITY = new WarningEntity();
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

        Optional<WarningEntity> warningEntity = warningDaoService.getById(1);
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
}
