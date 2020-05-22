package org.tpokora.storms.services;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.tpokora.config.properties.StormProperties;
import org.tpokora.storms.model.Coordinates;
import org.tpokora.storms.model.StormRequest;
import org.tpokora.storms.model.StormResponse;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class FindStormServiceTests extends StormServicesTests {

    private StormRequest stormRequest;
    private StormResponse expectedStormResponse;

    @InjectMocks
    private FindStormService findStormService;

    @BeforeEach
    public void setup() {
        stormRequest = new StormRequest(new Coordinates(11.11, 22.22), 100, 15);
        expectedStormResponse = new StormResponse(10, 100, "E", 15);
    }

    @Test
    public void testFindStormService() throws SOAPException {
        SOAPMessage response = generateStormResponse(expectedStormResponse);
        Mockito.when(stormProperties.getStorm()).thenReturn(Map.of(StormProperties.KEY, STORM_TEST_KEY));
        Mockito.when(soapService.sendSOAPMessage(any(), anyString())).thenReturn(response);
        StormResponse stormResponse = findStormService.checkStorm(stormRequest);
        Assert.assertEquals(expectedStormResponse.getAmount(), stormResponse.getAmount());
        Assert.assertEquals(expectedStormResponse.getTime(), stormResponse.getTime());
        Assert.assertEquals(expectedStormResponse.getDirection(), stormResponse.getDirection());
        Assert.assertEquals(Double.compare(expectedStormResponse.getDistance(), stormResponse.getDistance()), 0);
        Assert.assertEquals(expectedStormResponse.toString(), stormResponse.toString());
    }

}
