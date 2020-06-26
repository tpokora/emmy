package org.tpokora.weather.views;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.tpokora.weather.model.*;
import org.tpokora.weather.model.ForecastEntity;
import org.tpokora.weather.services.forecast.ForecastService;
import org.tpokora.weather.services.storms.FindCityService;
import org.tpokora.weather.services.storms.FindStormService;
import org.tpokora.weather.services.storms.FindWarningService;

import javax.xml.soap.SOAPException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.tpokora.weather.views.WeatherViewConstants.*;

@Controller
public class WeatherViewController {

    private final Logger LOGGER = LoggerFactory.getLogger(WeatherViewController.class);

    public static final String CITY = "city";
    public static final String STORM_RESPONSE = "stormResponse";
    public static final String STORM_REQUEST = "stormRequest";
    public static final String COORDINATES = "coordinates";
    public static final String WARNINGS = "warnings";
    public static final String ERROR = "error";

    private final FindCityService findCityService;
    private final FindStormService findStormService;
    private final FindWarningService findWarningService;
    private final ForecastService forecastService;

    public WeatherViewController(FindCityService findCityService, FindStormService findStormService,
                                 FindWarningService findWarningService, ForecastService forecastService) {
        this.findCityService = findCityService;
        this.findStormService = findStormService;
        this.findWarningService = findWarningService;
        this.forecastService = forecastService;
    }

    @GetMapping(value = WEATHER_VIEW_URL, name = WEATHER_VIEW)
    public String weather(Model model) {
        LOGGER.info("=> WeatherView");
        initializeView(model);
        return WEATHER_VIEW_TEMPLATE;
    }

    @PostMapping(value = WEATHER_FIND_CITY_URL)
    public String findCity(Model model, @ModelAttribute City city) {
        LOGGER.info("=> Find city: {}", city.getName());
        initializeView(model);
        try {
            city = this.findCityService.findCity(city.getName());
        } catch (Exception e) {
            return searchError(model, e);
        }
        if (city.getCoordinates().getY().equals(0.0) && city.getCoordinates().getX().equals(0.0)) {
            LOGGER.info("=> City {} not found", city.getName());
            setError(model, WeatherViewError.CITY_NOT_FOUND.getErrorMsg());
            return WEATHER_VIEW_TEMPLATE;
        }
        updateModelAttribute(model, CITY, city);
        StormRequest stormRequest = new StormRequest();
        stormRequest.setCoordinates(city.getCoordinates());
        updateModelAttribute(model, STORM_REQUEST, stormRequest);
        updateModelAttribute(model, COORDINATES, city.getCoordinates());
        return WEATHER_VIEW_TEMPLATE;
    }

    @PostMapping(value = WEATHER_FIND_FORECAST_URL)
    public String findForecast(Model model, @ModelAttribute Coordinates coordinates) {
        LOGGER.info("=> Find forecast");
        initializeView(model);
        Optional<ForecastEntity> forecast = forecastService.getForecast(coordinates);
        if (forecast.isPresent()) {
            model.addAttribute("forecast", forecast.get());
            updateModelAttribute(model, COORDINATES, coordinates);
            return WEATHER_VIEW_TEMPLATE;
        }

        return WEATHER_VIEW_TEMPLATE;
    }

    @PostMapping(value = WEATHER_FIND_STORM_URL)
    public String findStorm(Model model, @ModelAttribute StormRequest stormRequest) throws SOAPException {
        LOGGER.info("=> Find storm");
        initializeView(model);
        updateModelAttribute(model, STORM_REQUEST, stormRequest);
        updateModelAttribute(model, COORDINATES, stormRequest.getCoordinates());
        StormResponse stormResponse = null;
        try {
            stormResponse = this.findStormService.checkStorm(stormRequest);
        } catch (Exception e) {
            return searchError(model, e);
        }
        if (stormResponse.getAmount() == 0) {
            LOGGER.info("=> Storm not found");
            setError(model, WeatherViewError.NO_STORMS.getErrorMsg());
            return WEATHER_VIEW_TEMPLATE;
        }
        updateModelAttribute(model, STORM_RESPONSE, stormResponse);
        return WEATHER_VIEW_TEMPLATE;
    }

    @PostMapping(value = WEATHER_FIND_WARNINGS_URL)
    public String findWarnings(Model model, @ModelAttribute Coordinates coordinates) throws SOAPException {
        LOGGER.info("=> Find Warnings");
        initializeView(model);
        updateModelAttribute(model, COORDINATES, coordinates);
        List<Warning> warnings = null;
        try {
            warnings = this.findWarningService.findWarnings(coordinates);
        } catch (Exception e) {
            return searchError(model, e);
        }
        if (warnings.size() == 0) {
            LOGGER.info("=> Warnings not found");
            setError(model, WeatherViewError.NO_WARNINGS.getErrorMsg());
            return WEATHER_VIEW_TEMPLATE;
        }
        updateModelAttribute(model, WARNINGS, warnings);
        return WEATHER_VIEW_TEMPLATE;
    }

    private void initializeView(Model model) {
        model.addAttribute(ERROR, "");
        model.addAttribute(CITY, new City());
        model.addAttribute(STORM_REQUEST, new StormRequest());
        model.addAttribute(STORM_RESPONSE, new StormResponse());
        model.addAttribute(COORDINATES, new Coordinates());
        model.addAttribute(WARNINGS, new HashSet<Warning>());
    }

    private void updateModelAttribute(Model model, String attributeName, Object object) {
        model.asMap().put(attributeName, object);
    }

    private void setError(Model model, Object object) {
        updateModelAttribute(model, ERROR, object);
    }


    private String searchError(Model model, Exception e) {
        LOGGER.error("=> Connection error");
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
}
