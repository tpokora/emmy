package org.tpokora.weather.services.forecast;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.tpokora.weather.model.Forecast;

import java.util.Optional;

public class OpenWeatherForecastServiceTests {

    public static final double LONGITUDE = 11.11;
    public static final double LATITUDE = 22.22;
    ForecastService forecastService;

    @BeforeEach
    public void setup() {
        this.forecastService = new ForecastService(new RestTemplateBuilder());
    }

    @Test
    public void testGetForecast() {
        Optional<Forecast> forecast = forecastService.getForecast(LONGITUDE, LATITUDE);
        Assertions.assertTrue(forecast.isPresent());
    }
}
