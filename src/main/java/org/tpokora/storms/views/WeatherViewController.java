package org.tpokora.storms.views;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.tpokora.storms.model.City;
import org.tpokora.storms.services.FindCityService;

import javax.xml.soap.SOAPException;
import java.io.IOException;

import static org.tpokora.storms.views.WeatherViewConstants.*;

@Controller
public class WeatherViewController {

    private FindCityService findCityService;

    public WeatherViewController(FindCityService findCityService) {
        this.findCityService = findCityService;
    }

    @GetMapping(value = WEATHER_VIEW_URL, name = WEATHER_VIEW)
    public String weather(Model model) {
        model.addAttribute("city", new City());
        return WEATHER_VIEW;
    }

    @PostMapping(value = WEATHER_FIND_CITY_URL)
    public String findCity(Model model, @ModelAttribute City city) throws IOException, SOAPException {
        city = this.findCityService.handleResponse(this.findCityService.findCity(city.getName()));
        model.addAttribute("city", city);
        return WEATHER_VIEW;
    }
}
