package org.tpokora.weather.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tpokora.common.services.BaseServiceTest;
import org.tpokora.weather.model.entity.ForecastEntity;
import org.tpokora.weather.utils.ForecastTestsHelper;

import java.time.LocalDateTime;
import java.util.List;

public class ForecastDaoServiceTest extends BaseServiceTest {

    @Autowired
    private IForecastRepository forecastRepository;

    private ForecastDaoService forecastDaoService;

    @BeforeEach
    public void setup() {
        forecastDaoService = new ForecastDaoService(forecastRepository);
    }

    @Test
    public void testSaveForecast() {
        ForecastEntity firstForecastEntity = ForecastTestsHelper.createForecast();
        forecastDaoService.saveForecast(firstForecastEntity);
        List<ForecastEntity> allByCoordinates = forecastDaoService.findAllByCoordinates(firstForecastEntity.getLongitude(), firstForecastEntity.getLatitude());
        Assertions.assertEquals(1, allByCoordinates.size());

        ForecastEntity secondForecastEntity = ForecastTestsHelper.createForecast();
        forecastDaoService.saveForecast(secondForecastEntity);

        allByCoordinates = forecastDaoService.findAllByCoordinates(firstForecastEntity.getLongitude(), firstForecastEntity.getLatitude());
        Assertions.assertEquals(1, allByCoordinates.size());

        LocalDateTime newTimestamp = secondForecastEntity.getTimestamp();
        newTimestamp = newTimestamp.plusMinutes(65);
        secondForecastEntity.setTimestamp(newTimestamp);
        forecastDaoService.saveForecast(secondForecastEntity);
        allByCoordinates = forecastDaoService.findAllByCoordinates(firstForecastEntity.getLongitude(), firstForecastEntity.getLatitude());
        Assertions.assertEquals(2, allByCoordinates.size());
    }

}
