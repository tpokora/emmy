package org.tpokora.application.weather.forecast;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.tpokora.common.utils.DateUtils;
import org.tpokora.domain.weather.Coordinates;
import org.tpokora.persistance.entity.weather.ForecastEntity;

import java.util.Optional;

@Service
@Profile("mock")
public class MockForecastService implements IForecastService {
    @Override
    public Optional<ForecastEntity> getForecast(double longitude, double latitude) {
        return mockedForecast();
    }

    @Override
    public Optional<ForecastEntity> getForecast(Coordinates coordinates) {
        return mockedForecast();
    }

    private Optional<ForecastEntity> mockedForecast() {
        ForecastEntity forecastEntity = new ForecastEntity();
        forecastEntity.setId(1);
        forecastEntity.setLocation("testLocation");
        forecastEntity.setName("testName");
        forecastEntity.setDescription("testDescription");
        forecastEntity.setTemp(1.1);
        forecastEntity.setFeelTemp(1.2);
        forecastEntity.setMinTemp(0.9);
        forecastEntity.setMaxTemp(2.9);
        forecastEntity.setPressure(1000);
        forecastEntity.setHumidity(10);
        forecastEntity.setWind(10.1);
        forecastEntity.setLongitude(11.11);
        forecastEntity.setLatitude(22.22);
        forecastEntity.setTimestamp(DateUtils.getCurrentLocalDateTime());
        return Optional.of(forecastEntity);
    }
}
