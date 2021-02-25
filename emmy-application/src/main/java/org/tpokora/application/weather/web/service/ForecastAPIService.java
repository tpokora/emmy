package org.tpokora.application.weather.web.service;

import org.springframework.stereotype.Service;
import org.tpokora.application.weather.forecast.IForecastService;
import org.tpokora.application.weather.location.ILocationService;
import org.tpokora.domain.weather.Location;
import org.tpokora.persistance.entity.weather.ForecastEntity;

import java.util.Optional;

@Service
public class ForecastAPIService {

    private ILocationService locationService;
    private IForecastService forecastService;

    public ForecastAPIService(ILocationService locationService, IForecastService forecastService) {
        this.locationService = locationService;
        this.forecastService = forecastService;
    }

    public Optional<ForecastEntity> getByLocation(String location) {
        Optional<Location> locationCoordinatesByName = locationService.getLocationCoordinatesByName(location);
        if (locationCoordinatesByName.isEmpty()) {
            return Optional.empty();
        }
        return getForecastByCoordinates(
                locationCoordinatesByName.get().getCoordinates().getLongitude(),
                locationCoordinatesByName.get().getCoordinates().getLatitude());
    }


    public Optional<ForecastEntity> getForecastByCoordinates(double longitude, double latitude) {
        return forecastService.getForecast(longitude, latitude);
    }
}
