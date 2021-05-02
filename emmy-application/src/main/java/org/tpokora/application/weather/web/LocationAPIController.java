package org.tpokora.application.weather.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.tpokora.domain.weather.Location;
import org.tpokora.application.weather.properties.OpenCageDataProperties;
import org.tpokora.application.weather.location.ILocationService;
import org.tpokora.application.weather.location.OpenCageDataLocationService;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class LocationAPIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocationAPIController.class);

    private final RestTemplate restTemplate;
    private final OpenCageDataProperties openCageDataProperties;
    private ILocationService locationService;

    public LocationAPIController(RestTemplate restTemplate, OpenCageDataProperties openCageDataProperties) {
        this.restTemplate = restTemplate;
        locationService = new OpenCageDataLocationService(restTemplate, openCageDataProperties);
        this.openCageDataProperties = new OpenCageDataProperties();
    }

    @CrossOrigin
    @GetMapping(value = "/location", produces = "application/json")
    public ResponseEntity<Location> getLocationCoordinates(@RequestParam("name") String name) {
        LOGGER.info(">> Find location by name: {}", name);
        Optional<Location> optionalLocation = locationService.getLocationCoordinatesByName(name);
        return optionalLocation.map(location -> new ResponseEntity<>(location, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));

    }
}
