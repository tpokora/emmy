package org.tpokora.application.weather.views;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.tpokora.config.constants.WeatherViewConstants;

@Controller
public class WeatherDataViewController {

    private final Logger LOGGER = LoggerFactory.getLogger(WeatherDataViewController.class);

    @GetMapping(value = WeatherViewConstants.WEATHER_DATA_VIEW_URL, name = WeatherViewConstants.WEATHER_DATA_VIEW_URL)
    public String weatherDataHome(Model model) {
        LOGGER.info(">> WeatherDataView");
        return WeatherViewConstants.WEATHER_DATA_VIEW_TEMPLATE;
    }
}
