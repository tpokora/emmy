package org.tpokora.weather.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.tpokora.common.utils.DateUtils;
import org.tpokora.weather.model.Forecast;
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
        Forecast forecast = ForecastTestsHelper.createForecast();
        Mockito.when(forecastRepository.findAllByLongitudeAndLatitudeOrderByTimestampDesc(anyDouble(), anyDouble())).thenReturn(Collections.singletonList(forecast));
        List<Forecast> allByCoordinates = forecastDaoService.findAllByCoordinates(forecast.getLongitude(), forecast.getLatitude());
        Assertions.assertFalse(allByCoordinates.isEmpty());
    }
}