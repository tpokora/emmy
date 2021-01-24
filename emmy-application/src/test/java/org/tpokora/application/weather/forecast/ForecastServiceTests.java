package org.tpokora.application.weather.forecast;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.tpokora.application.weather.forecast.suppliers.OpenWeatherForecastSupplier;
import org.tpokora.common.utils.DateUtils;
import org.tpokora.domain.weather.Coordinates;
import org.tpokora.persistance.entity.weather.ForecastEntity;
import org.tpokora.persistance.services.weather.ForecastDaoService;

import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class ForecastServiceTests {

    public static final double LONGITUDE = 11.11;
    public static final double LATITUDE = 22.22;

    @Mock
    private ForecastDaoService forecastDaoService;

    @Mock
    private OpenWeatherForecastSupplier openWeatherForecastSupplier;

    @InjectMocks
    private ForecastService forecastService;

    @BeforeEach
    public void setup() {

    }

    @Test
    public void testGetForecast_openWeatherSupplier() {
        forecastService = new ForecastService(openWeatherForecastSupplier, forecastDaoService);
        ForecastEntity forecastEntity = createForecast();
        Mockito.when(openWeatherForecastSupplier.getForecast(LONGITUDE, LATITUDE)).thenReturn(Optional.of(forecastEntity));
        Mockito.when(forecastDaoService.saveForecast(ArgumentMatchers.any())).thenReturn(forecastEntity);
        Optional<ForecastEntity> forecast = forecastService.getForecast(LONGITUDE, LATITUDE);
        Assertions.assertTrue(forecast.isPresent());

        Optional<ForecastEntity> forecastByCoordinates = forecastService.getForecast(new Coordinates(LONGITUDE, LATITUDE));
        Assertions.assertTrue(forecastByCoordinates.isPresent());
    }

    @Test
    public void testGetForecast_testGetForecast_openWeatherSupplier_fail() {
        forecastService = new ForecastService(openWeatherForecastSupplier, forecastDaoService);
        Mockito.when(openWeatherForecastSupplier.getForecast(LONGITUDE, LATITUDE)).thenReturn(Optional.empty());
        Optional<ForecastEntity> forecast = forecastService.getForecast(LONGITUDE, LATITUDE);
        Assertions.assertTrue(forecast.isEmpty());
    }

    private ForecastEntity createForecast() {
        ForecastEntity forecastEntity = new ForecastEntity();

        forecastEntity.setId(1);
        forecastEntity.setName("testName");
        forecastEntity.setName("testDescription");
        forecastEntity.setTemp(1.1);
        forecastEntity.setFeelTemp(1.2);
        forecastEntity.setMinTemp(0.9);
        forecastEntity.setMaxTemp(2.9);
        forecastEntity.setPressure(1000);
        forecastEntity.setHumidity(10);
        forecastEntity.setWind(10.1);
        forecastEntity.setLongitude(11.11);
        forecastEntity.setLatitude(22.11);
        forecastEntity.setTimestamp(DateUtils.getCurrentLocalDateTime());
        return forecastEntity;
    }
}

