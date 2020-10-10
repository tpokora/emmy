package org.tpokora.weather.services.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tpokora.common.services.soap.SOAPService;
import org.tpokora.common.services.soap.SoapMessageUtilities;
import org.tpokora.common.services.soap.ISoapRequestMessageProcessor;
import org.tpokora.weather.properties.StormProperties;
import org.tpokora.domain.weather.common.StormConstants;

import javax.xml.soap.*;
import java.util.HashMap;
import java.util.Objects;

import static org.tpokora.weather.services.processor.StormProcessorStrings.*;

public class LocationSoapRequestProcessor implements ISoapRequestMessageProcessor<String> {

    private static final String LOCATION_INPUT_IS_NULL = "Location input is null!";
    protected StormProperties stormProperties;
    private final Logger LOGGER = LoggerFactory.getLogger(LocationSoapRequestProcessor.class);

    public LocationSoapRequestProcessor(StormProperties stormProperties) {
        this.stormProperties = stormProperties;
    }

    @Override
    public SOAPMessage process(String input) throws SOAPException {
        Objects.requireNonNull(input, LOCATION_INPUT_IS_NULL);
        SOAPMessage soapMessage = SOAPService.createSOAPMessage();
        HashMap<String, String> namespaces = new HashMap<>();
        namespaces.put(StormConstants.SOAP, StormConstants.NAMESPACE);
        SOAPEnvelope envelope = SOAPService.createSOAPEnvelope(soapMessage, namespaces);

        SOAPService.createSOAPAction(soapMessage, StormConstants.SOAP_ACTION_SZUKAJ_BURZY);
        createSOAPMessage(input, StormConstants.SOAP, envelope);

        LOGGER.debug("Location Request SOAP Message:");
        LOGGER.debug(SoapMessageUtilities.soapMessageToString(soapMessage));

        return soapMessage;
    }

    private void createSOAPMessage(String location, String namespace, SOAPEnvelope envelope) throws SOAPException {
        SOAPBody soapBody = envelope.getBody();
        SOAPElement findCity = soapBody.addChildElement(StormConstants.METHOD_MIEJSCOWOSC, namespace);
        SOAPElement nameElem = findCity.addChildElement(NAZWA, namespace);
        SOAPElement keyElem = findCity.addChildElement(KLUCZ, namespace);

        nameElem.addTextNode(location);
        keyElem.addTextNode(stormProperties.getValue(StormProperties.KEY));
    }
}
