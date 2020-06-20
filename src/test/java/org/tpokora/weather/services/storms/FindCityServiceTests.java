package org.tpokora.weather.services.storms;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.tpokora.config.properties.StormProperties;
import org.tpokora.weather.model.City;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class FindCityServiceTests extends StormServicesTests {

    public static final String TEST_CITY = "testCity";
    public static final City EXPECTED_CITY = new City(11.11, 22.22);

    @InjectMocks
    private FindCityService findCityService;

    @BeforeEach
    public void setup() {
        EXPECTED_CITY.setName(TEST_CITY);
    }

    @Test
    public void testFindCity() throws SOAPException {
        SOAPMessage response = generateCityResponse(EXPECTED_CITY);
        Mockito.when(stormProperties.getStorm()).thenReturn(Map.of(StormProperties.KEY, STORM_TEST_KEY));
        Mockito.when(soapService.sendSOAPMessage(any(), anyString())).thenReturn(response);
        City city = findCityService.findCity(TEST_CITY);
        Assert.assertEquals(EXPECTED_CITY.getName(), city.getName());
        Assert.assertEquals(EXPECTED_CITY.getCoordinates().getX(), city.getCoordinates().getX());
        Assert.assertEquals(EXPECTED_CITY.getCoordinates().getY(), city.getCoordinates().getY());
        Assert.assertEquals(EXPECTED_CITY.toString(), city.toString());
    }
}
