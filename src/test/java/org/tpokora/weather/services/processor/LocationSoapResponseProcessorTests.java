package org.tpokora.weather.services.processor;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tpokora.domain.weather.Location;
import org.tpokora.application.soap.SOAPService;
import org.tpokora.application.weather.processor.LocationSoapResponseProcessor;

import javax.xml.soap.*;
import java.util.HashMap;

public class LocationSoapResponseProcessorTests {

    private LocationSoapResponseProcessor citySoapResponseMessageProcessor;
    private SOAPMessage soapMessageResponse;

    @BeforeEach
    public void setup() throws SOAPException {
        citySoapResponseMessageProcessor = new LocationSoapResponseProcessor();
        soapMessageResponse = generateSOAPResponse();
    }

    @Test
    @DisplayName("CitySoapResponseProcessor generate City object based on coordinates from SOAPMessage response")
    public void testProcess() throws SOAPException {
        Location expectedLocation = new Location(11.11, 22.22);
        Location location = citySoapResponseMessageProcessor.process(soapMessageResponse);
        Assertions.assertEquals(expectedLocation.getCoordinates().getLongitude(), location.getCoordinates().getLongitude());
        Assertions.assertEquals(expectedLocation.getCoordinates().getLatitude(), location.getCoordinates().getLatitude());
        Assertions.assertEquals(Strings.EMPTY, location.getName());
        Assertions.assertEquals(expectedLocation.toString(), location.toString());
    }

    private SOAPMessage generateSOAPResponse() throws SOAPException {
        SOAPMessage soapMessage = SOAPService.createSOAPMessage();
        SOAPEnvelope soapEnvelope = SOAPService.createSOAPEnvelope(soapMessage, new HashMap<>());
        SOAPBody soapBody = soapEnvelope.getBody();
        SOAPElement miejscowoscResponse = soapBody.addChildElement("ns1:miejscowoscResponse");
        SOAPElement returnElement = miejscowoscResponse.addChildElement("return");
        SOAPElement y = returnElement.addChildElement("y");
        y.addTextNode("22.22");
        SOAPElement x = returnElement.addChildElement("x");
        x.addTextNode("11.11");
        return soapMessage;
    }
}
