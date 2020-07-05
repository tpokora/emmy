package org.tpokora.weather.web;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tpokora.weather.dao.ForecastDaoService;
import org.tpokora.weather.model.entity.ForecastEntity;
import org.tpokora.weather.services.forecast.ForecastService;

import java.util.List;
import java.util.Optional;


@Api(value = "Weather", description = "Storms API")
@RestController
@RequestMapping(value = "/weather/api/")
public class WeatherAPIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherAPIController.class);

    private ForecastService forecastService;
    private ForecastDaoService forecastDaoService;

    public WeatherAPIController(ForecastService forecastService, ForecastDaoService forecastDaoService) {
        this.forecastService = forecastService;
        this.forecastDaoService = forecastDaoService;
    }

    @GetMapping(value = "/getForecast", produces = "application/json")
    public ResponseEntity<ForecastEntity> getForecast(@RequestParam("longitude") double longitude, @RequestParam("latitude") double latitude) {
        LOGGER.info(">> Find forecast, Longitude: {}, Latitude: {}", longitude, latitude);
        Optional<ForecastEntity> forecastEntity = forecastService.getForecast(longitude, latitude);
        if (forecastEntity.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(forecastEntity.get(), HttpStatus.OK);
    }

    @GetMapping(value = "/getArchiveForecasts", produces = "application/json")
    public ResponseEntity<List<ForecastEntity>> getForecasts(@RequestParam("longitude") double longitude, @RequestParam("latitude") double latitude) {
        LOGGER.info(">> Find archived forecast, Longitude: {}, Latitude: {}", longitude, latitude);
        return new ResponseEntity<>(forecastDaoService.findAllByCoordinates(longitude, latitude), HttpStatus.OK);
    }

}
