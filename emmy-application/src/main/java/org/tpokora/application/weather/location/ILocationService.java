package org.tpokora.application.weather.location;

import org.tpokora.domain.weather.Location;

import java.util.Optional;

public interface ILocationService {

    public Optional<Location> getLocationCoordinatesByName(String name);
}
