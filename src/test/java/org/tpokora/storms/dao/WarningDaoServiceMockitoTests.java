package org.tpokora.storms.dao;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.tpokora.storms.model.WarningEntity;
import org.tpokora.storms.model.WarningStrings;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class WarningDaoServiceMockitoTests {

    @Mock
    private WarningRepository warningRepository;

    @InjectMocks
    private WarningDaoService warningDaoService;
    private WarningEntity WARNING_ENTITY = new WarningEntity();

    @BeforeEach
    public void setup() {
        WARNING_ENTITY = new WarningEntity();
        WARNING_ENTITY.setId(1);
        WARNING_ENTITY.setName(WarningStrings.FROST);
        WARNING_ENTITY.setLevel(1);
        WARNING_ENTITY.setStart(LocalDateTime.now());
        WARNING_ENTITY.setEnd(LocalDateTime.now().plusDays(2));
    }

    @Test
    public void testSaveWarningEntity() {
        Mockito.when(warningRepository.save(any())).thenReturn(WARNING_ENTITY);
        Optional<WarningEntity> savedWarningEntity = warningDaoService.save(WARNING_ENTITY);
        Assertions.assertEquals(WARNING_ENTITY.toString(), savedWarningEntity.get().toString());
    }
}
