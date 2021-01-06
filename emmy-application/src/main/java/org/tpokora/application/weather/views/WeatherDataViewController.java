package org.tpokora.application.weather.views;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tpokora.config.constants.WeatherViewConstants;
import org.tpokora.domain.weather.Location;
import org.tpokora.persistance.entity.weather.ForecastEntity;
import org.tpokora.persistance.services.weather.ForecastDaoService;

import java.time.LocalDateTime;
import java.util.List;

import static org.tpokora.application.weather.views.WeatherViewConstants.FORECASTS;
import static org.tpokora.application.weather.views.WeatherViewConstants.LOCATION;

@Controller
public class WeatherDataViewController {

    public static final String DATES_FORMATTER = "datesFormatter";

    private final Logger LOGGER = LoggerFactory.getLogger(WeatherDataViewController.class);

    private ForecastDaoService forecastDaoService;

    public WeatherDataViewController(ForecastDaoService forecastDaoService) {
        this.forecastDaoService = forecastDaoService;
    }

    @GetMapping(value = WeatherViewConstants.WEATHER_DATA_VIEW_URL, name = WeatherViewConstants.WEATHER_DATA_VIEW_URL)
    public String weatherDataHome(Model model) {
        initializeModel(model);
        LOGGER.info(">> WeatherDataView");
        return WeatherViewConstants.WEATHER_DATA_VIEW_TEMPLATE;
    }

    @GetMapping(value = WeatherViewConstants.WEATHER_DATA_FIND_URL)
    public String weatherDataByLocation(Model model, @RequestParam("name") String location) {
        initializeModel(model);
        LOGGER.info(">> Find WeatherData by location: {}", location);
        LocalDateTime now = LocalDateTime.now();
        List<ForecastEntity> allByLocationBetweenDates = forecastDaoService.findAllByLocationBetweenDates(location, now.minusMonths(4), now);
        updateModelAttribute(model, FORECASTS, allByLocationBetweenDates);
        return WeatherViewConstants.WEATHER_DATA_VIEW_TEMPLATE;
    }

    private void initializeModel(Model model) {
        model.addAttribute(DATES_FORMATTER, new DatesFormatterViewsHelper());
        model.addAttribute(LOCATION, new Location());

    }

    private void updateModelAttribute(Model model, String attributeName, Object object) {
        model.asMap().put(attributeName, object);
    }

}
