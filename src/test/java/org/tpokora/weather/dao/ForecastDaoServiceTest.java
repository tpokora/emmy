package org.tpokora.weather.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tpokora.common.services.BaseServiceTest;
import org.tpokora.common.utils.DateUtils;
import org.tpokora.weather.model.Forecast;
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
        Forecast firstForecast = ForecastTestsHelper.createForecast();
        forecastDaoService.saveForecast(firstForecast);
        List<Forecast> allByCoordinates = forecastDaoService.findAllByCoordinates(firstForecast.getLongitude(), firstForecast.getLatitude());
        Assertions.assertEquals(1, allByCoordinates.size());

        Forecast secondForecast = ForecastTestsHelper.createForecast();
        forecastDaoService.saveForecast(secondForecast);

        allByCoordinates = forecastDaoService.findAllByCoordinates(firstForecast.getLongitude(), firstForecast.getLatitude());
        Assertions.assertEquals(1, allByCoordinates.size());

        LocalDateTime newTimestamp = secondForecast.getTimestamp();
        newTimestamp = newTimestamp.plusMinutes(65);
        secondForecast.setTimestamp(newTimestamp);
        forecastDaoService.saveForecast(secondForecast);
        allByCoordinates = forecastDaoService.findAllByCoordinates(firstForecast.getLongitude(), firstForecast.getLatitude());
        Assertions.assertEquals(2, allByCoordinates.size());
    }

}
