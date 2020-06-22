package org.tpokora.weather.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tpokora.common.services.BaseServiceTest;
import org.tpokora.weather.model.Forecast;

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
        Forecast firstForecast = createForecast();
        forecastDaoService.saveForecast(firstForecast);
        List<Forecast> allByCoordinates = forecastDaoService.findAllByCoordinates(firstForecast.getLongitude(), firstForecast.getLatitude());
        Assertions.assertEquals(1, allByCoordinates.size());

        Forecast secondForecast = createForecast();
        forecastDaoService.saveForecast(secondForecast);

        allByCoordinates = forecastDaoService.findAllByCoordinates(firstForecast.getLongitude(), firstForecast.getLatitude());
        Assertions.assertEquals(1, allByCoordinates.size());
    }

    private Forecast createForecast() {
        Forecast forecast = new Forecast();

        forecast.setId(0);
        forecast.setName("testName");
        forecast.setDescription("testDescription");
        forecast.setTemp(1.1);
        forecast.setFeelTemp(1.2);
        forecast.setMinTemp(0.9);
        forecast.setMaxTemp(2.9);
        forecast.setPressure(1000);
        forecast.setHumidity(10);
        forecast.setWind(10.1);
        forecast.setLongitude(11.11);
        forecast.setLatitude(22.11);
        LocalDateTime now = LocalDateTime.now();
        now = now.minusNanos(now.getNano());
        forecast.setTimestamp(now);
        return forecast;
    }


}
