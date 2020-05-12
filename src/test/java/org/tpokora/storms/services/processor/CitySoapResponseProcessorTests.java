package org.tpokora.storms.services.processor;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tpokora.common.services.soap.SOAPService;
import org.tpokora.storms.model.City;

import javax.xml.soap.*;
import java.util.HashMap;

public class CitySoapResponseProcessorTests {

    private CitySoapResponseProcessor citySoapResponseMessageProcessor;
    private SOAPMessage soapMessageResponse;

    @BeforeEach
    public void setup() throws SOAPException {
        citySoapResponseMessageProcessor = new CitySoapResponseProcessor();
        soapMessageResponse = generateSOAPResponse();
    }

    @Test
    @DisplayName("CitySoapResponseProcessor generate City object based on coordinates from SOAPMessage response")
    public void testProcess() throws SOAPException {
        City expectedCity = new City(11.11, 22.22);
        City city = citySoapResponseMessageProcessor.process(soapMessageResponse);
        Assertions.assertEquals(expectedCity.getCoordinates().getX(), city.getCoordinates().getX());
        Assertions.assertEquals(expectedCity.getCoordinates().getY(), city.getCoordinates().getY());
        Assertions.assertEquals(Strings.EMPTY, city.getName());
        Assertions.assertEquals(expectedCity.toString(), city.toString());
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
