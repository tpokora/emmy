package org.tpokora.application.weather.services.storms;

import org.mockito.Mock;
import org.tpokora.application.soap.SOAPService;
import org.tpokora.application.weather.processor.StormProcessorStrings;
import org.tpokora.application.weather.properties.StormProperties;
import org.tpokora.domain.weather.common.StormConstants;
import org.tpokora.domain.weather.Location;
import org.tpokora.domain.weather.StormResponse;
import org.tpokora.domain.weather.Warning;

import javax.xml.soap.*;
import java.util.HashMap;
import java.util.List;

import static org.tpokora.application.weather.processor.StormProcessorStrings.*;


abstract public class StormServicesTests {

    public static final String STORM_TEST_KEY = "stormTestKey";

    @Mock
    protected SOAPService soapService;

    @Mock
    protected StormProperties stormProperties;

    public SOAPMessage generateCityResponse(Location location) throws SOAPException {
        SOAPMessage soapMessage = SOAPService.createSOAPMessage();
        SOAPEnvelope envelope = generateSOAPEnvelope(soapMessage);
        SOAPBody soapBody = envelope.getBody();
        SOAPElement response = soapBody.addChildElement(StormProcessorStrings.NS_1_MIEJSCOWOSC_RESPONSE);
        SOAPElement returnElem = response.addChildElement(RETURN);
        SOAPElement xElem = returnElem.addChildElement(X);
        xElem.setTextContent(String.valueOf(location.getCoordinates().getLongitude()));
        SOAPElement yElem = returnElem.addChildElement(Y);
        yElem.setTextContent(String.valueOf(location.getCoordinates().getLatitude()));
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

    public SOAPMessage generateWarningsResponse(List<Warning> warnings) throws SOAPException {
        SOAPMessage soapMessage = SOAPService.createSOAPMessage();
        SOAPEnvelope envelope = generateSOAPEnvelope(soapMessage);
        SOAPBody soapBody = envelope.getBody();
        SOAPElement response = soapBody.addChildElement(NS_1_OSTRZEZENIA_POGODOWE_RESPONSE);
        SOAPElement returnElem = response.addChildElement(RETURN);
        warnings.forEach(warning -> {
            createWarningElement(warning, returnElem);
        });

        return soapMessage;
    }

    private void createWarningElement(Warning warning, SOAPElement returnElem) {
        try {
            SOAPElement warningElement = returnElem.addChildElement(warning.getName().toLowerCase());
            warningElement.setTextContent(String.valueOf(warning.getLevel()));
            SOAPElement warningElementFrom = returnElem.addChildElement(warning.getName() + "_od_dnia");
            warningElementFrom.setTextContent(warning.getPeriod().getFromString());
            SOAPElement warningElementTo = returnElem.addChildElement(warning.getName() + "_do_dnia");
            warningElementTo.setTextContent(warning.getPeriod().getToString());
        } catch (SOAPException e) {
            e.printStackTrace();
        }
    }

    public SOAPEnvelope generateSOAPEnvelope(SOAPMessage soapMessage) throws SOAPException {
        HashMap<String, String> namespaces = new HashMap<>();
        namespaces.put(StormConstants.SOAP, StormConstants.NAMESPACE);
        SOAPEnvelope envelope = SOAPService.createSOAPEnvelope(soapMessage, namespaces);
        return envelope;
    }
}
