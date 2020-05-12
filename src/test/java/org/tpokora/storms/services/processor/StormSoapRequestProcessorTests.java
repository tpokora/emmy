package org.tpokora.storms.services.processor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tpokora.config.properties.StormProperties;
import org.tpokora.storms.StormsTestsConstants;
import org.tpokora.storms.model.Coordinates;
import org.tpokora.storms.model.StormRequest;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import static org.tpokora.storms.StormsTestsConstants.*;

public class StormSoapRequestProcessorTests {

    private StormSoapRequestProcessor stormSoapRequestProcessor;
    private StormProperties stormProperties;

    @BeforeEach
    public void setup() {
        stormProperties = new StormProperties();
        stormProperties.getStorm().put(KEY, TEST_KEY);
        stormSoapRequestProcessor = new StormSoapRequestProcessor(stormProperties);
    }

    @Test
    @DisplayName("StormSoapRequestProcessor generate SOAPMessage request based on StormRequest object")
    public void testProcess() throws SOAPException {
        StormRequest stormRequest = new StormRequest();
        double x = 11.11;
        double y = 22.22;
        int distance = 100;
        int time = 15;
        stormRequest.setCoordinates(new Coordinates(x, y));
        stormRequest.setDistance(distance);
        stormRequest.setTime(time);
        SOAPMessage stormMessage = stormSoapRequestProcessor.process(stormRequest);

        assertRequest(stormMessage, stormRequest);
    }

    private void assertRequest(SOAPMessage stormMessage, StormRequest stormRequest) throws SOAPException {
        SOAPBody soapBody = stormMessage.getSOAPBody();

        Assertions.assertNotNull(soapBody.getElementsByTagName("szukaj_burzy"));
        Assertions.assertEquals(String.valueOf(stormRequest.getCoordinates().getX()), soapBody.getElementsByTagName(SOAP_X).item(0).getTextContent());
        Assertions.assertEquals(String.valueOf(stormRequest.getCoordinates().getY()), soapBody.getElementsByTagName(SOAP_Y).item(0).getTextContent());
        Assertions.assertEquals(String.valueOf(stormRequest.getDistance()), soapBody.getElementsByTagName("soap:promien").item(0).getTextContent());
        Assertions.assertEquals(StormsTestsConstants.TEST_KEY, soapBody.getElementsByTagName("soap:klucz").item(0).getTextContent());
    }
}
