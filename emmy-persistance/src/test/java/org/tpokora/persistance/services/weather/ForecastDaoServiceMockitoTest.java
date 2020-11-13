package org.tpokora.persistance.services.weather;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.tpokora.persistance.common.utils.ForecastTestsHelper;
import org.tpokora.persistance.entity.weather.ForecastEntity;
import org.tpokora.persistance.repositories.weather.IForecastRepository;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyDouble;

@ExtendWith(MockitoExtension.class)
class ForecastDaoServiceMockitoTest {

    @Mock
    private IForecastRepository forecastRepository;

    @InjectMocks
    private ForecastDaoService forecastDaoService;

    @Test
    void findAllByCoordinates() {
        ForecastEntity forecastEntity = ForecastTestsHelper.createForecast();
        Mockito.when(forecastRepository.findAllByLongitudeAndLatitudeOrderByTimestampDesc(anyDouble(), anyDouble())).thenReturn(Collections.singletonList(forecastEntity));
        List<ForecastEntity> allByCoordinates = forecastDaoService.findAllByCoordinates(forecastEntity.getLongitude(), forecastEntity.getLatitude());
        Assertions.assertFalse(allByCoordinates.isEmpty());
    }
}