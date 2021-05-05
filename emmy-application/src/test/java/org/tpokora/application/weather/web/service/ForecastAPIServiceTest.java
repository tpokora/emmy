package org.tpokora.application.weather.web.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.tpokora.application.weather.common.ForecastTestsHelper;
import org.tpokora.application.weather.forecast.IForecastService;
import org.tpokora.application.weather.location.ILocationService;
import org.tpokora.domain.weather.Location;
import org.tpokora.persistance.entity.weather.ForecastEntity;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
class ForecastAPIServiceTest {

    public static final double LONGITUDE = 11.11;
    public static final double LATITUDE = 22.22;

    @Mock
    private ILocationService locationService;

    @Mock
    private IForecastService forecastService;

    @InjectMocks
    private ForecastAPIService forecastAPIService;

    @BeforeEach
    public void setup() {
        forecastAPIService = new ForecastAPIService(locationService, forecastService);
    }

    @Test
    void testGetByLocation() {
        String testLocationName = "Test Location";

        Location mockLocation = new Location(LONGITUDE, LATITUDE);
        ForecastEntity mockForecastEntity = ForecastTestsHelper.createForecastEntity(testLocationName, LONGITUDE, LATITUDE);
        Mockito.when(locationService.getLocationCoordinatesByName(anyString())).thenReturn(Optional.of(mockLocation));
        Mockito.when(forecastService.getForecast(anyDouble(), anyDouble())).thenReturn(Optional.of(mockForecastEntity));
        Optional<ForecastEntity> location = forecastAPIService.getByLocation(testLocationName);

        Assertions.assertFalse(location.isEmpty());
        ForecastEntity forecastEntity = location.get();
        Assertions.assertEquals(testLocationName, forecastEntity.getLocation());
        Assertions.assertEquals(LONGITUDE, forecastEntity.getLongitude());
        Assertions.assertEquals(LATITUDE, forecastEntity.getLatitude());
    }

    @Test
    void testGetByLocation_notFound() {
        String testLocationName = "Location not found";
        Mockito.when(locationService.getLocationCoordinatesByName(testLocationName)).thenReturn(Optional.empty());
        Optional<ForecastEntity> location = forecastAPIService.getByLocation(testLocationName);

        Assertions.assertTrue(location.isEmpty());
    }

    @Test
    void testGetByCoordinates() {
        ForecastEntity mockForecastEntity = ForecastTestsHelper.createForecastEntity("testLocationName", LONGITUDE, LATITUDE);
        Mockito.when(forecastService.getForecast(anyDouble(), anyDouble())).thenReturn(Optional.of(mockForecastEntity));
        Optional<ForecastEntity> location = forecastAPIService.getForecastByCoordinates(LONGITUDE, LATITUDE);

        Assertions.assertFalse(location.isEmpty());
        ForecastEntity forecastEntity = location.get();
        Assertions.assertEquals(LONGITUDE, forecastEntity.getLongitude());
        Assertions.assertEquals(LATITUDE, forecastEntity.getLatitude());
    }

    @Test
    void testGetByCoordinates_notFound() {
        forecastAPIService = new ForecastAPIService(locationService, forecastService);
        Mockito.when(forecastService.getForecast(LONGITUDE, LATITUDE)).thenReturn(Optional.empty());
        Optional<ForecastEntity> location = forecastAPIService.getForecastByCoordinates(LONGITUDE, LATITUDE);

        Assertions.assertTrue(location.isEmpty());
    }
}