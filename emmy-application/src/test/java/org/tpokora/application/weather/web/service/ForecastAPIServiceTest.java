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

    @Mock
    private ILocationService locationService;

    @Mock
    private IForecastService forecastService;

    @InjectMocks
    private ForecastAPIService forecastAPIService;

    @BeforeEach
    public void setup() {

    }

    @Test
    void testGetByLocation() {
        String testLocationName = "Test Location";
        double longitude = 11.11;
        double latitude = 22.22;
        forecastAPIService = new ForecastAPIService(locationService, forecastService);
        Location mockLocation = new Location(longitude, latitude);
        ForecastEntity mockForecastEntity = ForecastTestsHelper.createForecastEntity(testLocationName, longitude, latitude);
        Mockito.when(locationService.getLocationCoordinatesByName(anyString())).thenReturn(Optional.of(mockLocation));
        Mockito.when(forecastService.getForecast(anyDouble(), anyDouble())).thenReturn(Optional.of(mockForecastEntity));
        Optional<ForecastEntity> location = forecastAPIService.getByLocation(testLocationName);

        Assertions.assertFalse(location.isEmpty());
        ForecastEntity forecastEntity = location.get();
        Assertions.assertEquals(testLocationName, forecastEntity.getLocation());
        Assertions.assertEquals(longitude, forecastEntity.getLongitude());
        Assertions.assertEquals(latitude, forecastEntity.getLatitude());
    }

    @Test
    void testGetByLocation_notFound() {
        String testLocationName = "Location not found";
        forecastAPIService = new ForecastAPIService(locationService, forecastService);
        Mockito.when(locationService.getLocationCoordinatesByName(testLocationName)).thenReturn(Optional.empty());
        Optional<ForecastEntity> location = forecastAPIService.getByLocation(testLocationName);

        Assertions.assertTrue(location.isEmpty());
    }

    @Test
    void testGetByCoordinates() {
        double longitude = 11.11;
        double latitude = 22.22;
        forecastAPIService = new ForecastAPIService(locationService, forecastService);
        ForecastEntity mockForecastEntity = ForecastTestsHelper.createForecastEntity("testLocationName", longitude, latitude);
        Mockito.when(forecastService.getForecast(anyDouble(), anyDouble())).thenReturn(Optional.of(mockForecastEntity));
        Optional<ForecastEntity> location = forecastAPIService.getForecastByCoordinates(longitude, latitude);

        Assertions.assertFalse(location.isEmpty());
        ForecastEntity forecastEntity = location.get();
        Assertions.assertEquals(longitude, forecastEntity.getLongitude());
        Assertions.assertEquals(latitude, forecastEntity.getLatitude());
    }

    @Test
    void testGetByCoordinates_notFound() {
        double longitude = 11.11;
        double latitude = 22.22;
        forecastAPIService = new ForecastAPIService(locationService, forecastService);
        Mockito.when(forecastService.getForecast(longitude, latitude)).thenReturn(Optional.empty());
        Optional<ForecastEntity> location = forecastAPIService.getForecastByCoordinates(longitude, latitude);

        Assertions.assertTrue(location.isEmpty());
    }
}