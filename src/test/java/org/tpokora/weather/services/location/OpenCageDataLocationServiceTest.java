package org.tpokora.weather.services.location;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.tpokora.common.utils.FileReaderUtils;
import org.tpokora.weather.model.Location;
import org.tpokora.weather.properties.OpenCageDataProperties;
import org.tpokora.weather.properties.OpenWeatherProperties;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class OpenCageDataLocationServiceTest {

    @Mock
    private OpenCageDataProperties openCageDataProperties;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private OpenCageDataLocationService openCageDataLocationService;

    @BeforeEach
    public void setup() {
        Mockito.when(openCageDataProperties.getValue(OpenWeatherProperties.KEY)).thenReturn("testKey");
    }

    @Test
    void testGetCityCoordinatesByName() {
        String fileToString = FileReaderUtils.fileToString("weather/location/opencageDataResponse.json");
        ResponseEntity<String> stringResponseEntity = new ResponseEntity<>(fileToString, null, HttpStatus.OK);
        Mockito.when(restTemplate.exchange(
                anyString(), any(HttpMethod.class), any(HttpEntity.class), (Class<String>) any(), anyMap())
        ).thenReturn(stringResponseEntity);
        Optional<Location> cityCoordinatesByName = openCageDataLocationService.getLocationCoordinatesByName("Warszawa");
        Assertions.assertTrue(cityCoordinatesByName.isPresent());
    }

    @Test
    public void testGetForecast_fail() {
        String fileToString = FileReaderUtils.fileToString("weather/location/opencageDataResponse.json");
        ResponseEntity<String> stringResponseEntity = new ResponseEntity<>(fileToString, null, HttpStatus.BAD_REQUEST);
        Mockito.when(restTemplate.exchange(
                anyString(), any(HttpMethod.class), any(HttpEntity.class), (Class<String>) any(), anyMap())
        ).thenReturn(stringResponseEntity);
        Optional<Location> city = openCageDataLocationService.getLocationCoordinatesByName("Warszawa");
        Assertions.assertTrue(city.isEmpty());
    }
}