package org.tpokora.views.weather;

import org.springframework.stereotype.Component;
import org.tpokora.storms.model.City;

@Component
public class WeatherService {

    private City city;

    public WeatherService() {
        this.city = new City();
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
