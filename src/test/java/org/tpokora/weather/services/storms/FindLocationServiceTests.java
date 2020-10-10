package org.tpokora.weather.services.storms;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.tpokora.weather.properties.StormProperties;
import org.tpokora.domain.weather.Location;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class FindLocationServiceTests extends StormServicesTests {

    public static final String TEST_CITY = "testCity";
    public static final Location EXPECTED_LOCATION = new Location(11.11, 22.22);

    @InjectMocks
    private FindCityService findCityService;

    @BeforeEach
    public void setup() {
        EXPECTED_LOCATION.setName(TEST_CITY);
    }

    @Test
    public void testFindCity() throws SOAPException {
        SOAPMessage response = generateCityResponse(EXPECTED_LOCATION);
        Mockito.when(stormProperties.getValue(StormProperties.KEY)).thenReturn(STORM_TEST_KEY);
        Mockito.when(soapService.sendSOAPMessage(any(), anyString())).thenReturn(response);
        Location location = findCityService.findCity(TEST_CITY);
        Assert.assertEquals(EXPECTED_LOCATION.getName(), location.getName());
        Assert.assertEquals(EXPECTED_LOCATION.getCoordinates().getLongitude(), location.getCoordinates().getLongitude());
        Assert.assertEquals(EXPECTED_LOCATION.getCoordinates().getLatitude(), location.getCoordinates().getLatitude());
        Assert.assertEquals(EXPECTED_LOCATION.toString(), location.toString());
    }
}
