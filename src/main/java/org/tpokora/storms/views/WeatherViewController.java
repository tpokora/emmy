package org.tpokora.storms.views;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static org.tpokora.storms.views.WeatherViewConstants.WEATHER_VIEW;
import static org.tpokora.storms.views.WeatherViewConstants.WEATHER_VIEW_URL;

@Controller
public class WeatherViewController {

    @GetMapping(value = WEATHER_VIEW_URL, name = WEATHER_VIEW)
    public String weather(Model model) {
        return WEATHER_VIEW;
    }
}
