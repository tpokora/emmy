package org.tpokora.application.weather.services.storms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.tpokora.application.weather.properties.StormProperties;
import org.tpokora.application.weather.storms.FindStormService;
import org.tpokora.domain.weather.Coordinates;
import org.tpokora.domain.weather.StormRequest;
import org.tpokora.domain.weather.StormResponse;
import org.tpokora.persistance.services.weather.StormDaoService;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
public class FindStormServiceTests extends StormServicesTests {

    public static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.now();
    private StormRequest stormRequest;
    private StormResponse expectedStormResponse;

    @Mock
    private StormDaoService stormDaoService;

    @InjectMocks
    private FindStormService findStormService;

    @BeforeEach
    public void setup() {
        stormRequest = new StormRequest(new Coordinates(11.11, 22.22), 100, 15);
        expectedStormResponse = new StormResponse(10, 100, "E", 15);
        expectedStormResponse.setTimestamp(LOCAL_DATE_TIME);
    }

    @Test
    public void testFindStormService() throws SOAPException {
        SOAPMessage response = generateStormResponse(expectedStormResponse);
        Mockito.when(stormProperties.getValue(StormProperties.KEY)).thenReturn(STORM_TEST_KEY);
        Mockito.when(soapService.sendSOAPMessage(ArgumentMatchers.any(), ArgumentMatchers.anyString())).thenReturn(response);
        StormResponse stormResponse = findStormService.checkStorm(stormRequest);
        stormResponse.setTimestamp(LOCAL_DATE_TIME);
        Assertions.assertEquals(expectedStormResponse.getAmount(), stormResponse.getAmount());
        Assertions.assertEquals(expectedStormResponse.getTime(), stormResponse.getTime());
        Assertions.assertEquals(expectedStormResponse.getDirection(), stormResponse.getDirection());
        Assertions.assertEquals(Double.compare(expectedStormResponse.getDistance(), stormResponse.getDistance()), 0);
        Assertions.assertEquals(expectedStormResponse.toString(), stormResponse.toString());
    }

}
