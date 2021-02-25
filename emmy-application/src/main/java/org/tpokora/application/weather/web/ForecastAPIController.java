package org.tpokora.application.weather.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tpokora.application.weather.web.service.ForecastAPIService;
import org.tpokora.common.utils.DateUtils;
import org.tpokora.persistance.entity.weather.ForecastEntity;
import org.tpokora.persistance.services.weather.ForecastDaoService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/api/weather")
public class ForecastAPIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ForecastAPIController.class);

    private ForecastDaoService forecastDaoService;
    private ForecastAPIService forecastAPIService;

    public ForecastAPIController(ForecastDaoService forecastDaoService, ForecastAPIService forecastAPIService) {
        this.forecastDaoService = forecastDaoService;
        this.forecastAPIService = forecastAPIService;
    }

    @GetMapping(value = "/getForecast", produces = "application/json")
    public ResponseEntity<ForecastEntity> getForecast(@RequestParam("longitude") double longitude, @RequestParam("latitude") double latitude) {
        LOGGER.info(">> Find forecast, Longitude: {}, Latitude: {}", longitude, latitude);
        Optional<ForecastEntity> forecastEntity = forecastAPIService.getForecastByCoordinates(longitude, latitude);
        if (forecastEntity.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(forecastEntity.get());
    }

    @GetMapping(value = "/forecast", produces = "application/json")
    public ResponseEntity<ForecastEntity> getForecastByLocation(@RequestParam("location") String location) {
        LOGGER.info(">> Find forecast, location: {}", location);
        Optional<ForecastEntity> forecastEntity = forecastAPIService.getByLocation(location);
        if (forecastEntity.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(forecastEntity.get());
    }

    @GetMapping(value = "/getArchiveForecastsFromDate", produces = "application/json")
    public ResponseEntity<List<ForecastEntity>> getForecastsByCoordinatesFromPeriod(@RequestParam(value = "longitude") double longitude,
                                                                    @RequestParam("latitude") double latitude,
                                                                    @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                                    @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        LocalDateTime startDateLocalDateTime = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime endDateLocalDateTime = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LOGGER.info(">> Find archived forecast, Longitude: {}, Latitude: {}, StartDate: {}, EndDate: {}", longitude, latitude,
                DateUtils.parseDateToString(startDateLocalDateTime), DateUtils.parseDateToString(endDateLocalDateTime));
        return new ResponseEntity<>(forecastDaoService.findAllByCoordinatesBetweenDates(longitude, latitude,
                startDateLocalDateTime, endDateLocalDateTime), HttpStatus.OK);
    }

    @GetMapping(value = "/getArchiveForecastsByLocation", produces = "application/json")
    public ResponseEntity<List<ForecastEntity>> getForecastsFromPeriodByLocation(@RequestParam("location") String location,
                                                                       @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                                       @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        LocalDateTime startDateLocalDateTime = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime endDateLocalDateTime = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LOGGER.info(">> Find archived forecast, Location: {}, StartDate: {}, EndDate: {}", location,
                DateUtils.parseDateToString(startDateLocalDateTime), DateUtils.parseDateToString(endDateLocalDateTime));
        return new ResponseEntity<>(forecastDaoService.findAllByLocationBetweenDates(location, startDateLocalDateTime, endDateLocalDateTime), HttpStatus.OK);
    }


    @GetMapping(value = "/getArchiveForecasts", produces = "application/json")
    public ResponseEntity<List<ForecastEntity>> getForecasts(@RequestParam("longitude") double longitude, @RequestParam("latitude") double latitude) {
        LOGGER.info(">> Find archived forecast, Longitude: {}, Latitude: {}", longitude, latitude);
        return new ResponseEntity<>(forecastDaoService.findAllByCoordinates(longitude, latitude), HttpStatus.OK);
    }

}
