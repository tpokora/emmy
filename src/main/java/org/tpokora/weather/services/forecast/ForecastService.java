package org.tpokora.weather.services.forecast;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.tpokora.weather.dao.ForecastDaoService;
import org.tpokora.weather.mapper.IForecastMapper;
import org.tpokora.weather.mapper.OpenWeatherForecastMapper;
import org.tpokora.weather.model.Coordinates;
import org.tpokora.weather.model.ForecastEntity;
import org.tpokora.weather.properties.OpenWeatherProperties;

import java.util.Map;
import java.util.Optional;

@Service
public class ForecastService implements IForecastService {

    public static final String X_RAPID_API_HOST = "X-RapidAPI-Host";
    public static final String X_RAPID_API_KEY = "X-RapidAPI-Key";
    public static final String ID = "id";
    public static final String LON = "lon";
    public static final String LAT = "lat";

    private final Logger LOGGER = LoggerFactory.getLogger(ForecastService.class);

    private final RestTemplate restTemplate;
    private final IForecastMapper iForecastMapper;
    private final OpenWeatherProperties openWeatherProperties;
    private final ForecastDaoService forecastDaoService;

    public static final String URL = "https://community-open-weather-map.p.rapidapi.com/weather?id={id}&lon={lon}&lat={lat}&&units=metric";

    public ForecastService(RestTemplate restTemplate, OpenWeatherProperties openWeatherProperties, ForecastDaoService forecastDaoService) {
        this.restTemplate = restTemplate;
        iForecastMapper = new OpenWeatherForecastMapper();
        this.openWeatherProperties = openWeatherProperties;
        this.forecastDaoService = forecastDaoService;

    }

    @Override
    public Optional<ForecastEntity> getForecast(double longitude, double latitude) {
        LOGGER.info("==> Find forecast longitude: {}, latitude: {}", longitude, latitude);
        HttpEntity request = new HttpEntity(setupHeaders());

        ResponseEntity<String> responseEntity =
                this.restTemplate.exchange(URL, HttpMethod.GET, request, String.class, getUriVariables(longitude, latitude));
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            String forecastString = responseEntity.getBody();
            ForecastEntity forecastEntity = iForecastMapper.map(forecastString);
            forecastEntity = forecastDaoService.saveForecast(forecastEntity);
            return Optional.of(forecastEntity);
        }

        return Optional.empty();
    }

    public Optional<ForecastEntity> getForecast(Coordinates coordinates) {
        return getForecast(coordinates.getLongitude(), coordinates.getLatitude());
    }

    private HttpHeaders setupHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(X_RAPID_API_HOST, openWeatherProperties.getValue(OpenWeatherProperties.HOST));
        httpHeaders.set(X_RAPID_API_KEY, openWeatherProperties.getValue(OpenWeatherProperties.KEY));

        return httpHeaders;
    }

    private Map<String, ? extends Number> getUriVariables(double longitude, double latitude) {
        return Map.of(ID, Integer.parseInt(openWeatherProperties.getValue(OpenWeatherProperties.ID)),
                LON, longitude, LAT, latitude);
    }
}
