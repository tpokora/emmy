package org.tpokora.storms.services;

import org.mockito.Mock;
import org.tpokora.common.services.soap.SOAPService;
import org.tpokora.config.properties.StormProperties;
import org.tpokora.storms.model.City;
import org.tpokora.storms.services.processor.StormProcessorStrings;

import javax.xml.soap.*;
import java.util.HashMap;

import static org.tpokora.storms.services.processor.StormProcessorStrings.*;

abstract public class StormServicesTests {

    public static final String STORM_TEST_KEY = "stormTestKey";

    @Mock
    protected SOAPService soapService;

    @Mock
    protected StormProperties stormProperties;

    public SOAPMessage generateCityResponse(City city) throws SOAPException {
        SOAPMessage soapMessage = SOAPService.createSOAPMessage();
        HashMap<String, String> namespaces = new HashMap<>();
        namespaces.put(StormConstants.SOAP, StormConstants.NAMESPACE);
        SOAPEnvelope envelope = SOAPService.createSOAPEnvelope(soapMessage, namespaces);
        SOAPBody soapBody = envelope.getBody();
        SOAPElement response = soapBody.addChildElement(StormProcessorStrings.NS_1_MIEJSCOWOSC_RESPONSE);
        SOAPElement returnElem = response.addChildElement(RETURN);
        SOAPElement xElem = returnElem.addChildElement(X);
        xElem.setTextContent(String.valueOf(city.getCoordinates().getX()));
        SOAPElement yElem = returnElem.addChildElement(Y);
        yElem.setTextContent(String.valueOf(city.getCoordinates().getY()));
        return soapMessage;
    }
}
