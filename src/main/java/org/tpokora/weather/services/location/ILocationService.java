package org.tpokora.weather.services.location;

import org.tpokora.weather.model.City;

import java.util.Optional;

public interface ILocationService {

    public Optional<City> getCityCoordinatesByName(String name);
}
