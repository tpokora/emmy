package org.tpokora.storms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tpokora.config.properties.StormProperties;
import org.tpokora.storms.model.Coordinates;
import org.tpokora.storms.model.StormRequest;
import org.tpokora.storms.services.processor.StormSoapRequestProcessor;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

public class StormSoapRequestProcessorTests {

    private StormSoapRequestProcessor stormSoapRequestProcessor;
    private StormProperties stormProperties;

    @BeforeEach
    public void setup() {
        stormProperties = new StormProperties();
        stormProperties.getStorm().put(StormsTestsConstants.KEY, StormsTestsConstants.TEST_KEY);
        stormSoapRequestProcessor = new StormSoapRequestProcessor(stormProperties);
    }

    @Test
    @DisplayName("StormSoapRequestProcessor generate SOAPMessage request based on StormRequest object")
    public void testProcess() throws SOAPException {
        StormRequest stormRequest = new StormRequest();
        double x = 11.11;
        double y = 22.22;
        int distance = 100;
        int time = 30;
        stormRequest.setCoordinates(new Coordinates(x, y));
        stormRequest.setDistance(distance);
        stormRequest.setTime(time);
        SOAPMessage stormMessage = stormSoapRequestProcessor.process(stormRequest);

        assertResponse(stormMessage, stormRequest);
    }

    private void assertResponse(SOAPMessage stormMessage, StormRequest stormRequest) throws SOAPException {
        SOAPBody soapBody = stormMessage.getSOAPBody();

        Assertions.assertNotNull(soapBody.getElementsByTagName("szukaj_burzy"));
        Assertions.assertEquals(String.valueOf(stormRequest.getCoordinates().getX()), soapBody.getElementsByTagName("soap:x").item(0).getTextContent());
        Assertions.assertEquals(String.valueOf(stormRequest.getCoordinates().getY()), soapBody.getElementsByTagName("soap:y").item(0).getTextContent());
        Assertions.assertEquals(String.valueOf(stormRequest.getDistance()), soapBody.getElementsByTagName("soap:promien").item(0).getTextContent());
        Assertions.assertEquals(StormsTestsConstants.TEST_KEY, soapBody.getElementsByTagName("soap:klucz").item(0).getTextContent());
    }
}
