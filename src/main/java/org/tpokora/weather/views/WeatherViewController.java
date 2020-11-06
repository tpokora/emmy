package org.tpokora.weather.views;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.tpokora.domain.weather.*;
import org.tpokora.persistance.entity.weather.ForecastEntity;
import org.tpokora.services.weather.forecast.ForecastService;
import org.tpokora.services.weather.location.OpenCageDataLocationService;
import org.tpokora.services.weather.storms.FindCityService;
import org.tpokora.services.weather.storms.FindStormService;
import org.tpokora.services.weather.storms.FindWarningService;

import java.util.List;
import java.util.Optional;

import static org.tpokora.config.constants.WeatherViewConstants.*;

@Controller
public class WeatherViewController {

    private final Logger LOGGER = LoggerFactory.getLogger(WeatherViewController.class);

    public static final String LOCATION = "location";
    public static final String STORM_RESPONSE = "stormResponse";
    public static final String STORM_REQUEST = "stormRequest";
    public static final String COORDINATES = "coordinates";
    public static final String FORECAST = "forecast";
    public static final String WARNINGS = "warnings";
    public static final String ERROR = "error";

    private final OpenCageDataLocationService openCageDataLocationService;
    private final FindCityService findCityService;
    private final FindStormService findStormService;
    private final FindWarningService findWarningService;
    private final ForecastService forecastService;

    private ForecastEntity forecast;
    private StormResponse stormResponse;
    private List<Warning> warnings;

    public WeatherViewController(OpenCageDataLocationService openCageDataLocationService, FindCityService findCityService, FindStormService findStormService,
                                 FindWarningService findWarningService, ForecastService forecastService) {
        this.openCageDataLocationService = openCageDataLocationService;
        this.findCityService = findCityService;
        this.findStormService = findStormService;
        this.findWarningService = findWarningService;
        this.forecastService = forecastService;
    }

    @GetMapping(value = WEATHER_VIEW_URL, name = WEATHER_VIEW)
    public String weather(Model model) {
        LOGGER.info(">> WeatherView");
        initializeView(model);
        return WEATHER_VIEW_TEMPLATE;
    }

    @GetMapping(value = WEATHER_FIND_LOCATION_URL)
    public String findCity(Model model, @RequestParam("name") String name) {
        LOGGER.info(">> Find city: {}", name);
        initializeView(model);
        Optional<Location> optionalCity;
        try {
            optionalCity = this.openCageDataLocationService.getLocationCoordinatesByName(name);
        } catch (Exception e) {
            return searchError(model, e);
        }
        Location location = optionalCity.get();
        if (location.getCoordinates().getLatitude().equals(0.0) && location.getCoordinates().getLongitude().equals(0.0)) {
            LOGGER.info(">> City {} not found", location.getName());
            setError(model, WeatherViewError.CITY_NOT_FOUND.getErrorMsg());
            return WEATHER_VIEW_TEMPLATE;
        }
        updateModelAttribute(model, LOCATION, location);
        StormRequest stormRequest = createStormRequest(location.getCoordinates(),20, 10);
        updateModelAttribute(model, STORM_REQUEST, stormRequest);
        updateModelAttribute(model, COORDINATES, location.getCoordinates());
        return WEATHER_VIEW_TEMPLATE;
    }

    @GetMapping(value = WEATHER_FIND_FORECAST_URL)
    public String findForecastGet(Model model,
                                  @RequestParam("longitude") double longitude,
                                  @RequestParam("latitude") double latitude) {

        return handleForecastRequest(model, new Coordinates(longitude, latitude));
    }

    @GetMapping(value = WEATHER_FIND_STORM_URL)
    public String findStorm(Model model, @RequestParam("longitude") double longitude,
                            @RequestParam("latitude") double latitude,
                            @RequestParam("distance") double distance,
                            @RequestParam("time") int time) {
        StormRequest stormRequest = new StormRequest(new Coordinates(longitude, latitude), distance, time);
        LOGGER.info(">> Find storm: {}", stormRequest.toString());
        initializeView(model);
        updateModelAttribute(model, STORM_REQUEST, stormRequest);
        updateModelAttribute(model, COORDINATES, stormRequest.getCoordinates());
        try {
            stormResponse = this.findStormService.checkStorm(stormRequest);
        } catch (Exception e) {
            return searchError(model, e);
        }
        if (stormResponse.getAmount() == 0) {
            LOGGER.info(">> Storm not found");
            setError(model, WeatherViewError.NO_STORMS.getErrorMsg());
            return WEATHER_VIEW_TEMPLATE;
        }
        updateModelAttribute(model, STORM_RESPONSE, stormResponse);
        return WEATHER_VIEW_TEMPLATE;
    }

    @GetMapping(value = WEATHER_FIND_WARNINGS_URL)
    public String findWarnings(Model model,
                               @RequestParam("longitude") double longitude,
                               @RequestParam("latitude") double latitude) {
        LOGGER.info(">> Find Warnings");
        initializeView(model);
        Coordinates coordinates = new Coordinates(longitude, latitude);
        updateModelAttribute(model, COORDINATES, coordinates);
        StormRequest stormRequest = new StormRequest();
        stormRequest.setCoordinates(coordinates);
        updateModelAttribute(model, STORM_REQUEST, stormRequest);
        try {
            warnings = this.findWarningService.findWarnings(coordinates);
        } catch (Exception e) {
            return searchError(model, e);
        }
        if (warnings.size() == 0) {
            LOGGER.info(">> Warnings not found");
            setError(model, WeatherViewError.NO_WARNINGS.getErrorMsg());
            return WEATHER_VIEW_TEMPLATE;
        }
        updateModelAttribute(model, WARNINGS, warnings);
        return WEATHER_VIEW_TEMPLATE;
    }

    private String handleForecastRequest(Model model, @ModelAttribute Coordinates coordinates) {
        LOGGER.info(">> Find forecast");
        initializeView(model);
        Optional<ForecastEntity> forecastOptional = forecastService.getForecast(coordinates);
        if (forecastOptional.isPresent()) {
            forecast = forecastOptional.get();
            model.addAttribute(FORECAST, forecast);
            updateModelAttribute(model, COORDINATES, coordinates);
            updateModelAttribute(model, STORM_REQUEST, createStormRequest(coordinates, 20, 10));
            return WEATHER_VIEW_TEMPLATE;
        }

        return WEATHER_VIEW_TEMPLATE;
    }

    private void initializeView(Model model) {
        model.addAttribute(ERROR, "");
        model.addAttribute(LOCATION, new Location());
        model.addAttribute(STORM_REQUEST, createStormRequest(new Coordinates(), 20, 10));
        model.addAttribute(STORM_RESPONSE, stormResponse);
        model.addAttribute(COORDINATES, new Coordinates());
        model.addAttribute(WARNINGS, warnings);
        model.addAttribute(FORECAST, forecast);
    }

    private void updateModelAttribute(Model model, String attributeName, Object object) {
        model.asMap().put(attributeName, object);
    }

    private void setError(Model model, Object object) {
        updateModelAttribute(model, ERROR, object);
    }

    private String searchError(Model model, Exception e) {
        LOGGER.error(">> Connection error");
        LOGGER.error(e.getMessage());
        setError(model, WeatherViewError.CONNECTION_ERROR.getErrorMsg());
        return WEATHER_VIEW_TEMPLATE;
    }

    public enum WeatherViewError {

        CITY_NOT_FOUND("City not found"),
        NO_STORMS("No storms found"),
        NO_WARNINGS("No weather warnings"),
        CONNECTION_ERROR("Connection error");

        WeatherViewError(String errorMsg) {
            this.errorMsg = errorMsg;
        }

        private final String errorMsg;

        public String getErrorMsg() {
            return this.errorMsg;
        }
    }

    private StormRequest createStormRequest(Coordinates coordinates, int distance, int time) {
        return new StormRequest(coordinates, distance, time);
    }
}
