package org.tpokora.weather.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tpokora.common.model.ErrorMsg;
import org.tpokora.weather.model.*;
import org.tpokora.weather.services.forecast.ForecastService;
import org.tpokora.weather.services.storms.FindCityService;
import org.tpokora.weather.services.storms.FindStormService;
import org.tpokora.weather.services.storms.FindWarningService;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;
import java.util.List;
import java.util.Optional;


@Api(value = "Weather", description = "Storms API")
@RestController
@RequestMapping(value = "/weather/api/")
public class WeatherAPIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherAPIController.class);

    private ForecastService forecastService;

    public WeatherAPIController(ForecastService forecastService) {
        this.forecastService = forecastService;
    }

    @GetMapping(value = "/getForecast", produces = "application/json")
    public ResponseEntity<ForecastEntity> getForecast(@RequestParam("longitude") double longitude, @RequestParam("latitude") double latitude) {
        LOGGER.info("=> Find forecast, Longitude: {}, Latitude: {}", longitude, latitude);
        Optional<ForecastEntity> forecastEntity = forecastService.getForecast(longitude, latitude);
        if (forecastEntity.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(forecastEntity.get(), HttpStatus.OK);
    }

}
