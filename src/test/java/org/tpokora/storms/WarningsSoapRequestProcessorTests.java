package org.tpokora.storms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tpokora.config.properties.StormProperties;
import org.tpokora.storms.model.Coordinates;
import org.tpokora.storms.services.processor.WarningsSoapRequestProcessor;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

public class WarningsSoapRequestProcessorTests {

    private WarningsSoapRequestProcessor warningsSoapRequestProcessor;
    private StormProperties stormProperties;

    @BeforeEach
    public void setup() {
        stormProperties = new StormProperties();
        stormProperties.getStorm().put(StormsTestsConstants.KEY, StormsTestsConstants.TEST_KEY);
        warningsSoapRequestProcessor = new WarningsSoapRequestProcessor(stormProperties);
    }

    @Test
    @DisplayName("WarningsSoapRequestProcessor generate SOAPMessage request based on coordinates")
    public void testProcess() throws SOAPException {
        Coordinates coordinates = new Coordinates(11.11, 22.22);
        SOAPMessage soapMessage = warningsSoapRequestProcessor.process(coordinates);

        Assertions.assertEquals(String.valueOf(coordinates.getX()), soapMessage.getSOAPBody().getElementsByTagName("soap:x").item(0).getTextContent());
        Assertions.assertEquals(String.valueOf(coordinates.getY()), soapMessage.getSOAPBody().getElementsByTagName("soap:y").item(0).getTextContent());
        Assertions.assertEquals(StormsTestsConstants.TEST_KEY, soapMessage.getSOAPBody().getElementsByTagName(StormsTestsConstants.SOAP_KLUCZ).item(0).getTextContent());
    }
}
