package org.tpokora.weather.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.tpokora.weather.model.entity.ForecastEntity;
import org.tpokora.weather.utils.ForecastTestsHelper;

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