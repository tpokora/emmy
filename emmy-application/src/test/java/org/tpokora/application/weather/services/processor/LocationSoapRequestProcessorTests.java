package org.tpokora.application.weather.services.processor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tpokora.application.weather.StormsTestsConstants;
import org.tpokora.application.weather.processor.LocationSoapRequestProcessor;
import org.tpokora.application.weather.properties.StormProperties;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;


public class LocationSoapRequestProcessorTests {

    public static final String TEST_CITY = "testCity";

    private LocationSoapRequestProcessor locationSoapRequestProcessor;
    private StormProperties stormProperties;

    @BeforeEach
    public void setup() {
        stormProperties = new StormProperties();
        stormProperties.getStorm().put(StormsTestsConstants.KEY, StormsTestsConstants.TEST_KEY);
        locationSoapRequestProcessor = new LocationSoapRequestProcessor(stormProperties);
    }

    @Test
    @DisplayName("CitySoapRequestProcessor generate SOAPMessage request based on city name")
    public void testProcess() throws SOAPException {
        SOAPMessage cityMessage = locationSoapRequestProcessor.process(TEST_CITY);

        Assertions.assertEquals(TEST_CITY, cityMessage.getSOAPBody().getElementsByTagName(StormsTestsConstants.SOAP_NAZWA).item(0).getTextContent());
        Assertions.assertEquals(StormsTestsConstants.TEST_KEY, cityMessage.getSOAPBody().getElementsByTagName(StormsTestsConstants.SOAP_KLUCZ).item(0).getTextContent());
    }
}
