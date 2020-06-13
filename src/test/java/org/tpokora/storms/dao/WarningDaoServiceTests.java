package org.tpokora.storms.dao;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.tpokora.common.services.BaseServiceTest;
import org.tpokora.storms.model.WarningEntity;
import org.tpokora.storms.model.WarningStrings;

import java.time.LocalDateTime;

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
        WarningEntity savedWarningEntity = warningDaoService.save(WARNING_ENTITY);
        Assertions.assertEquals(WARNING_ENTITY.toString(), savedWarningEntity.toString());
    }

    @Test
    public void testGetWarningEntity() {
        WarningEntity savedWarningEntity = warningDaoService.save(WARNING_ENTITY);
        Assertions.assertEquals(WARNING_ENTITY.toString(), savedWarningEntity.toString());

        WarningEntity warningEntity = warningDaoService.getById(1);
        Assertions.assertEquals(savedWarningEntity.toString(), warningEntity.toString());
    }

    @Test
    public void testFindTheSameWarningEntity() {
        WarningEntity savedWarningEntity = warningDaoService.save(WARNING_ENTITY);
        Assertions.assertTrue(warningDaoService.getSameWarning(WARNING_ENTITY).isPresent());
    }

    @Disabled
    @Test
    public void testSaveWarningEntityWithTheSameValues() {
        WarningEntity firstWarningEntity = WarningEntity.valueOf(WARNING_ENTITY);
        WarningEntity secondWarningEntity = WarningEntity.valueOf(WARNING_ENTITY);
        Assertions.assertEquals(firstWarningEntity.toString(), secondWarningEntity.toString());

        firstWarningEntity = warningDaoService.save(firstWarningEntity);
        secondWarningEntity = warningDaoService.save(secondWarningEntity);

        Assertions.assertEquals(1, warningDaoService.getAll().size());

    }
}
