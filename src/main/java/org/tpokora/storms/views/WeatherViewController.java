package org.tpokora.storms.views;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.tpokora.storms.model.*;
import org.tpokora.storms.services.FindCityService;
import org.tpokora.storms.services.FindStormService;
import org.tpokora.storms.services.FindWarningService;

import javax.xml.soap.SOAPException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.tpokora.storms.views.WeatherViewConstants.*;

@Controller
public class WeatherViewController {

    public static final String CITY = "city";
    public static final String STORM_RESPONSE = "stormResponse";
    public static final String STORM_REQUEST = "stormRequest";
    public static final String COORDINATES = "coordinates";
    public static final String WARNINGS = "warnings";

    private FindCityService findCityService;
    private FindStormService findStormService;
    private FindWarningService findWarningService;

    public WeatherViewController(FindCityService findCityService, FindStormService findStormService, FindWarningService findWarningService) {
        this.findCityService = findCityService;
        this.findStormService = findStormService;
        this.findWarningService = findWarningService;
    }

    @GetMapping(value = WEATHER_VIEW_URL, name = WEATHER_VIEW)
    public String weather(Model model) {
        initializeView(model);
        return WEATHER_VIEW;
    }

    @PostMapping(value = WEATHER_FIND_CITY_URL)
    public String findCity(Model model, @ModelAttribute City city) throws IOException, SOAPException {
        initializeView(model);
        city = this.findCityService.handleResponse(this.findCityService.findCity(city.getName()));
        updateModelAttribute(model, CITY, city);
        StormRequest stormRequest = new StormRequest();
        stormRequest.setCoordinates(city.getCoordinates());
        updateModelAttribute(model, STORM_REQUEST, stormRequest);
        updateModelAttribute(model, COORDINATES, city.getCoordinates());
        return WEATHER_VIEW;
    }

    @PostMapping(value = WEATHER_FIND_STORM_URL)
    public String findStorm(Model model, @ModelAttribute StormRequest stormRequest) throws IOException, SOAPException {
        initializeView(model);
        updateModelAttribute(model, STORM_REQUEST, stormRequest);
        updateModelAttribute(model, COORDINATES, stormRequest.getCoordinates());
        StormResponse stormResponse = this.findStormService.handleResponse(this.findStormService.checkStorm(stormRequest));
        updateModelAttribute(model, STORM_RESPONSE, stormResponse);
        return WEATHER_VIEW;
    }

    @PostMapping(value = WEATHER_FIND_WARNINGS_URL)
    public String findWarnings(Model model, @ModelAttribute Coordinates coordinates) throws IOException, SOAPException {
        initializeView(model);
        updateModelAttribute(model, COORDINATES, coordinates);
        Set<Warning> warnings = this.findWarningService.handleResponse(this.findWarningService.findWarning(coordinates));
        updateModelAttribute(model, WARNINGS, warnings);
        return WEATHER_VIEW;
    }

    private void initializeView(Model model) {
        model.addAttribute(CITY, new City());
        model.addAttribute(STORM_REQUEST, new StormRequest());
        model.addAttribute(STORM_RESPONSE, new StormResponse());
        model.addAttribute(COORDINATES, new Coordinates());
        model.addAttribute(WARNINGS, new HashSet<Warning>());
    }

    private void updateModelAttribute(Model model, String attributeName, Object object) {
        model.asMap().put(attributeName, object);
    }
}
