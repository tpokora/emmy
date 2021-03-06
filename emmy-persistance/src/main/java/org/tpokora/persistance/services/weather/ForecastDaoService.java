package org.tpokora.persistance.services.weather;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.tpokora.common.utils.DateUtils;
import org.tpokora.persistance.entity.weather.ForecastEntity;
import org.tpokora.persistance.repositories.weather.IForecastRepository;

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

    public List<ForecastEntity> findAllByCoordinates(double longitude, double latitude) {
        return forecastRepository.findAllByLongitudeAndLatitudeOrderByTimestampDesc(longitude, latitude);
    }

    public List<ForecastEntity> findAllByCoordinatesBetweenDates(double longitude, double latitude, LocalDateTime startDate, LocalDateTime endDate) {
        return forecastRepository.findAllByLongitudeAndLatitudeAndOrderByTimestampDesc(longitude, latitude, startDate, endDate);
    }

    public List<ForecastEntity> findAllByLocationBetweenDates(String location, LocalDateTime startDate, LocalDateTime endDate) {
        return forecastRepository.findAllByLocationContainsIgnoreCaseAndTimestampBetween(location, startDate, endDate);
    }

    public ForecastEntity saveForecast(ForecastEntity forecastEntity) {
        Objects.requireNonNull(forecastEntity, "Forecast can't be null!");
        Optional<ForecastEntity> latestForecast = forecastRepository.findFirstByLongitudeAndLatitudeOrderByTimestampDesc(forecastEntity.getLongitude(), forecastEntity.getLatitude());
        LOGGER.info(">>> Saving Forecast to DB");
        if (latestForecast.isPresent()) {
            if (DateUtils.getMinuteDifference(forecastEntity.getTimestamp(), latestForecast.get().getTimestamp()) > 60) {
                return forecastRepository.save(forecastEntity);
            }
        } else {
            return forecastRepository.save(forecastEntity);
        }

        return forecastEntity;
    }

}
