package org.tpokora.storms.services;

import org.mockito.Mock;
import org.tpokora.common.services.soap.SOAPService;
import org.tpokora.config.properties.StormProperties;
import org.tpokora.storms.model.City;
import org.tpokora.storms.model.StormResponse;
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
        SOAPEnvelope envelope = generateSOAPEnvelope(soapMessage);
        SOAPBody soapBody = envelope.getBody();
        SOAPElement response = soapBody.addChildElement(StormProcessorStrings.NS_1_MIEJSCOWOSC_RESPONSE);
        SOAPElement returnElem = response.addChildElement(RETURN);
        SOAPElement xElem = returnElem.addChildElement(X);
        xElem.setTextContent(String.valueOf(city.getCoordinates().getX()));
        SOAPElement yElem = returnElem.addChildElement(Y);
        yElem.setTextContent(String.valueOf(city.getCoordinates().getY()));
        return soapMessage;
    }

    public SOAPMessage generateStormResponse(StormResponse stormResponse) throws SOAPException {
        SOAPMessage soapMessage = SOAPService.createSOAPMessage();
        SOAPEnvelope envelope = generateSOAPEnvelope(soapMessage);
        SOAPBody soapBody = envelope.getBody();
        SOAPElement response = soapBody.addChildElement(NS_1_SZUKAJ_BURZY_RESPONSE);
        SOAPElement returnElem = response.addChildElement(RETURN);
        SOAPElement liczbaElem = returnElem.addChildElement(LICZBA);
        liczbaElem.setTextContent(String.valueOf(stormResponse.getAmount()));
        SOAPElement odlegloscElem = returnElem.addChildElement(ODLEGLOSC);
        odlegloscElem.setTextContent(String.valueOf(stormResponse.getDistance()));
        SOAPElement kierunekElem = returnElem.addChildElement(KIERUNEK);
        kierunekElem.setTextContent(String.valueOf(stormResponse.getDirection()));
        SOAPElement okresElem = returnElem.addChildElement(OKRES);
        okresElem.setTextContent(String.valueOf(stormResponse.getTime()));
        return soapMessage;
    }

    public SOAPEnvelope generateSOAPEnvelope(SOAPMessage soapMessage) throws SOAPException {
        HashMap<String, String> namespaces = new HashMap<>();
        namespaces.put(StormConstants.SOAP, StormConstants.NAMESPACE);
        SOAPEnvelope envelope = SOAPService.createSOAPEnvelope(soapMessage, namespaces);
        return envelope;
    }
}
