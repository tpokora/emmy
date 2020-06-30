package org.tpokora.weather.services.location;

import org.tpokora.weather.model.Location;

import java.util.Optional;

public interface ILocationService {

    public Optional<Location> getLocationCoordinatesByName(String name);
}
