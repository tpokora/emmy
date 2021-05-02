package org.tpokora.application.weather.web.service;

import org.springframework.stereotype.Service;
import org.tpokora.application.weather.location.ILocationService;
import org.tpokora.domain.weather.Location;

import java.util.Optional;

@Service
public class LocationAPIService {

    private final ILocationService locationService;

    public LocationAPIService(ILocationService locationService) {
        this.locationService = locationService;
    }

    public Optional<Location> getLocationCoordinatesByName(String name) {
        return locationService.getLocationCoordinatesByName(name);
    }
}
