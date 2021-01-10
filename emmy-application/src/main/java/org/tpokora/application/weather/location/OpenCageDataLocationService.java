package org.tpokora.application.weather.location;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.tpokora.domain.weather.Location;
import org.tpokora.application.common.mapper.IJSONMapper;
import org.tpokora.application.weather.mapper.OpenCageDataLocationMapper;
import org.tpokora.application.weather.properties.OpenCageDataProperties;

import java.util.Map;
import java.util.Optional;

@Service
public class OpenCageDataLocationService implements ILocationService {

    public static final String KEY = "key";
    public static final String Q = "q";
    public static final String LIMIT = "limit";
    public static final String MIN = "min";
    private final Logger LOGGER = LoggerFactory.getLogger(OpenCageDataLocationService.class);

    private RestTemplate restTemplate;
    private IJSONMapper IJSONMapper;
    private OpenCageDataProperties openCageDataProperties;
    private String URL = "https://api.opencagedata.com/geocode/v1/json?key={key}&q={q}&limit={limit}&min_confidence={min}";

    public OpenCageDataLocationService(RestTemplate restTemplate, OpenCageDataProperties openCageDataProperties) {
        this.restTemplate = restTemplate;
        this.openCageDataProperties = openCageDataProperties;
        IJSONMapper = new OpenCageDataLocationMapper();
    }

    @Override
    public Optional<Location> getLocationCoordinatesByName(String name) {
        LOGGER.info(">>> Find Coordinates by location name: {}", name);
        HttpEntity request = new HttpEntity(setupHeaders());

        ResponseEntity<String> responseEntity =
                this.restTemplate.exchange(URL, HttpMethod.GET, request, String.class, getUriVariables(name));
        if(responseEntity.getStatusCode() == HttpStatus.OK) {
            String response = responseEntity.getBody();
            Location location = (Location) IJSONMapper.map(response);
            return Optional.of(location);
        }

        return Optional.empty();
    }

    private HttpHeaders setupHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();

        return httpHeaders;
    }

    private Map<String, String> getUriVariables(String name) {
        return Map.of(KEY, openCageDataProperties.getValue(KEY),
                Q, name,
                LIMIT, "1",
                MIN, "3");
    }

}
