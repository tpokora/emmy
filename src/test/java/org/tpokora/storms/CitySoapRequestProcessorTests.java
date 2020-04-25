package org.tpokora.storms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tpokora.config.properties.StormProperties;
import org.tpokora.storms.services.processor.CitySoapRequestProcessor;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

public class CitySoapRequestProcessorTests {

    public static final String KEY = "key";
    public static final String TEST_KEY = "testKey";
    public static final String TEST_CITY = "testCity";
    public static final String SOAP_NAZWA = "soap:nazwa";
    public static final String SOAP_KLUCZ = "soap:klucz";

    private CitySoapRequestProcessor citySoapRequestProcessor;
    private StormProperties stormProperties;

    @BeforeEach
    public void setup() {
        stormProperties = new StormProperties();
        stormProperties.getStorm().put(KEY, TEST_KEY);
        citySoapRequestProcessor = new CitySoapRequestProcessor(stormProperties);
    }

    @Test
    @DisplayName("CitySoapRequestProcessor generate SOAPMessage request based on city name")
    public void testProcess() throws SOAPException {
        SOAPMessage cityMessage = citySoapRequestProcessor.process(TEST_CITY);

        Assertions.assertEquals(TEST_CITY, cityMessage.getSOAPBody().getElementsByTagName(SOAP_NAZWA).item(0).getTextContent());
        Assertions.assertEquals(TEST_KEY, cityMessage.getSOAPBody().getElementsByTagName(SOAP_KLUCZ).item(0).getTextContent());
    }
}
