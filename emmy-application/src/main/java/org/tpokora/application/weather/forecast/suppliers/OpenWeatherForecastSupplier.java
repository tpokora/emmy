package org.tpokora.application.weather.forecast.suppliers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.tpokora.application.common.mapper.IJSONMapper;
import org.tpokora.application.weather.properties.OpenWeatherProperties;
import org.tpokora.persistance.entity.weather.ForecastEntity;

import java.util.Map;
import java.util.Optional;

@Component("openWeatherForecastSupplier")
public class OpenWeatherForecastSupplier implements IForecastSupplier {

    private final Logger LOGGER = LoggerFactory.getLogger(OpenWeatherForecastSupplier.class);

    public static final String X_RAPID_API_HOST = "X-RapidAPI-Host";
    public static final String X_RAPID_API_KEY = "X-RapidAPI-Key";
    public static final String URL = "https://community-open-weather-map.p.rapidapi.com/weather?id={id}&lon={lon}&lat={lat}&&units=metric";
    public static final String ID = "id";
    public static final String LON = "lon";
    public static final String LAT = "lat";

    private final RestTemplate restTemplate;
    private final IJSONMapper IJSONMapper;
    private final OpenWeatherProperties openWeatherProperties;

    public OpenWeatherForecastSupplier(RestTemplate restTemplate,
                                       @Qualifier("openWeatherForecastMapper") IJSONMapper IJSONMapper,
                                       OpenWeatherProperties openWeatherProperties) {
        this.restTemplate = restTemplate;
        this.IJSONMapper = IJSONMapper;
        this.openWeatherProperties = openWeatherProperties;
    }

    @Override
    public Optional<ForecastEntity> getForecast(double longitude, double latitude) {
        LOGGER.info(">>> Find forecast longitude: {}, latitude: {}", longitude, latitude);
        HttpEntity request = new HttpEntity(setupHeaders());

        ResponseEntity<String> responseEntity =
                this.restTemplate.exchange(URL, HttpMethod.GET, request, String.class, getUriVariables(longitude, latitude));
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            String forecastString = responseEntity.getBody();
            ForecastEntity forecastEntity = (ForecastEntity) IJSONMapper.map(forecastString);
            return Optional.of(forecastEntity);
        }

        return Optional.empty();
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
