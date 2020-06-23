package org.tpokora.weather.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.tpokora.common.utils.DateUtils;
import org.tpokora.weather.model.Forecast;
import org.tpokora.weather.model.StormEntity;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ForecastDaoService {

    private final Logger LOGGER = LoggerFactory.getLogger(ForecastDaoService.class);

    private final IForecastRepository forecastRepository;

    public ForecastDaoService(IForecastRepository forecastRepository) {
        this.forecastRepository = forecastRepository;
    }

    public List<Forecast> findAllByCoordinates(double longitude, double latitude) {
        return forecastRepository.findAllByLongitudeAndLatitudeOrderByTimestampDesc(longitude, latitude);
    }

    public Forecast saveForecast(Forecast forecast) {
        Objects.requireNonNull(forecast, "Forecast can't be null!");
        Optional<Forecast> latestForecast = forecastRepository.findFirstByLongitudeAndLatitudeOrderByTimestampDesc(forecast.getLongitude(), forecast.getLatitude());
        LOGGER.info("==> Saving Forecast to DB");
        if (latestForecast.isPresent()) {
            if (DateUtils.getMinuteDifference(forecast.getTimestamp(), latestForecast.get().getTimestamp()) > 60) {
                return forecastRepository.save(forecast);
            }
        } else {
            return forecastRepository.save(forecast);
        }

        return forecast;
    }

}
