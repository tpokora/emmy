package org.tpokora.application.weather.web.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.tpokora.application.weather.location.ILocationService;
import org.tpokora.domain.weather.Location;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationAPIServiceTest {

    public static final double LONGITUDE = 11.11;
    public static final double LATITUDE = 22.22;

    @Mock
    private ILocationService locationService;

    @InjectMocks
    private LocationAPIService locationAPIService;

    @Test
    void getLocationCoordinatesByName() {
        Location mockLocation = new Location(LONGITUDE, LATITUDE);
        when(locationService.getLocationCoordinatesByName(anyString())).thenReturn(Optional.of(mockLocation));

        Optional<Location> testLocation = locationAPIService.getLocationCoordinatesByName("test location");
        Assertions.assertEquals(mockLocation, testLocation.get());
    }

}