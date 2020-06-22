package org.tpokora.weather.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.tpokora.weather.model.Forecast;

import java.time.LocalDateTime;
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
        Forecast forecast = createForecast();
        Mockito.when(forecastRepository.findAllByLongitudeAndLatitudeOrderByTimestampDesc(anyDouble(), anyDouble())).thenReturn(Collections.singletonList(forecast));
        List<Forecast> allByCoordinates = forecastDaoService.findAllByCoordinates(forecast.getLongitude(), forecast.getLatitude());
        Assertions.assertFalse(allByCoordinates.isEmpty());
    }

    private Forecast createForecast() {
        Forecast forecast = new Forecast();

        forecast.setId(1);
        forecast.setName("testName");
        forecast.setDescription("testDescription");
        forecast.setTemp(1.1);
        forecast.setFeelTemp(1.2);
        forecast.setMinTemp(0.9);
        forecast.setMaxTemp(2.9);
        forecast.setPressure(1000);
        forecast.setHumidity(10);
        forecast.setWind(10.1);
        forecast.setLongitude(11.11);
        forecast.setLatitude(22.11);
        LocalDateTime now = LocalDateTime.now();
        now = now.minusNanos(now.getNano());
        forecast.setTimestamp(now);
        return forecast;
    }
}