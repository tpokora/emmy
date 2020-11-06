package org.tpokora.weather.services.storms;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.tpokora.services.weather.properties.StormProperties;
import org.tpokora.persistance.services.weather.StormDaoService;
import org.tpokora.domain.weather.Coordinates;
import org.tpokora.domain.weather.StormRequest;
import org.tpokora.domain.weather.StormResponse;
import org.tpokora.services.weather.storms.FindStormService;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

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
        Mockito.when(soapService.sendSOAPMessage(any(), anyString())).thenReturn(response);
        StormResponse stormResponse = findStormService.checkStorm(stormRequest);
        stormResponse.setTimestamp(LOCAL_DATE_TIME);
        Assert.assertEquals(expectedStormResponse.getAmount(), stormResponse.getAmount());
        Assert.assertEquals(expectedStormResponse.getTime(), stormResponse.getTime());
        Assert.assertEquals(expectedStormResponse.getDirection(), stormResponse.getDirection());
        Assert.assertEquals(Double.compare(expectedStormResponse.getDistance(), stormResponse.getDistance()), 0);
        Assert.assertEquals(expectedStormResponse.toString(), stormResponse.toString());
    }

}
