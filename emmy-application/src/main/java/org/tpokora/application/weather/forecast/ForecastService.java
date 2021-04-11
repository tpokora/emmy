package org.tpokora.application.weather.forecast;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.tpokora.application.weather.forecast.suppliers.IForecastSupplier;
import org.tpokora.domain.weather.Coordinates;
import org.tpokora.persistance.entity.weather.ForecastEntity;
import org.tpokora.persistance.services.weather.ForecastDaoService;

import java.util.Optional;

@Service
@Profile("!mock")
public class ForecastService implements IForecastService {

    private final Logger LOGGER = LoggerFactory.getLogger(ForecastService.class);

    private final IForecastSupplier forecastSupplier;
    private final ForecastDaoService forecastDaoService;

    public ForecastService(@Qualifier("openWeatherForecastSupplier") IForecastSupplier forecastSupplier, ForecastDaoService forecastDaoService) {
        this.forecastSupplier = forecastSupplier;
        this.forecastDaoService = forecastDaoService;
    }

    @Override
    public Optional<ForecastEntity> getForecast(double longitude, double latitude) {
        LOGGER.info(">>> Find forecast longitude: {}, latitude: {}", longitude, latitude);

        Optional<ForecastEntity> forecastEntityOptional = forecastSupplier.getForecast(longitude, latitude);

        if (forecastEntityOptional.isPresent()) {
            ForecastEntity forecastEntity = forecastEntityOptional.get();
            forecastDaoService.saveForecast(forecastEntity);
            return forecastEntityOptional;
        }

        return Optional.empty();
    }

    @Override
    public Optional<ForecastEntity> getForecast(Coordinates coordinates) {
        return getForecast(coordinates.getLongitude(), coordinates.getLatitude());
    }
}
