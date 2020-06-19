package org.tpokora.storms.services.forecast;


import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.tpokora.storms.model.Forecast;

import java.util.Map;
import java.util.Optional;

@Service
public class ForecastService implements IForecastService {

    private final RestTemplate restTemplate;

    public ForecastService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Optional<Forecast> getForecast(double longitude, double latitude) {
        String url = "https://community-open-weather-map.p.rapidapi.com/weather?id={id}&lon={lon}&lat={lat}&&units=metric";
        Map<String, ? extends Number> uriVariables = Map.of("id", 0, "lon", longitude, "lat", latitude);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("X-RapidAPI-Host", "community-open-weather-map.p.rapidapi.com");
        httpHeaders.set("X-RapidAPI-Key", "");

        HttpEntity request = new HttpEntity(httpHeaders);

        ResponseEntity<Forecast> responseEntity = this.restTemplate.exchange(url, HttpMethod.GET, request, Forecast.class, uriVariables);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            Forecast forecast = responseEntity.getBody();
            return Optional.of(forecast);
        }

        return Optional.empty();
    }
}
