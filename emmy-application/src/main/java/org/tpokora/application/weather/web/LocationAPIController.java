package org.tpokora.application.weather.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tpokora.application.weather.web.service.LocationAPIService;
import org.tpokora.domain.weather.Location;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class LocationAPIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocationAPIController.class);

    private final LocationAPIService locationAPIService;

    public LocationAPIController(LocationAPIService locationAPIService) {
        this.locationAPIService = locationAPIService;
    }

    @CrossOrigin
    @GetMapping(value = "/location", produces = "application/json")
    public ResponseEntity<Location> getLocationCoordinates(@RequestParam("name") String name) {
        LOGGER.info(">> Find location by name: {}", name);
        Optional<Location> optionalLocation = locationAPIService.getLocationCoordinatesByName(name);
        return optionalLocation.map(location -> new ResponseEntity<>(location, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));

    }
}
