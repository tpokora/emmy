package org.tpokora.weather.services.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tpokora.common.services.soap.SOAPService;
import org.tpokora.common.services.soap.SoapMessageUtilities;
import org.tpokora.common.services.soap.ISoapRequestMessageProcessor;
import org.tpokora.config.properties.StormProperties;
import org.tpokora.weather.services.StormConstants;

import javax.xml.soap.*;
import java.util.HashMap;
import java.util.Objects;

import static org.tpokora.weather.services.processor.StormProcessorStrings.*;

public class CitySoapRequestProcessor implements ISoapRequestMessageProcessor<String> {

    private static final String CITY_INPUT_IS_NULL = "City input is null!";
    protected StormProperties stormProperties;
    private final Logger LOGGER = LoggerFactory.getLogger(CitySoapRequestProcessor.class);

    public CitySoapRequestProcessor(StormProperties stormProperties) {
        this.stormProperties = stormProperties;
    }

    @Override
    public SOAPMessage process(String input) throws SOAPException {
        Objects.requireNonNull(input, CITY_INPUT_IS_NULL);
        SOAPMessage soapMessage = SOAPService.createSOAPMessage();
        HashMap<String, String> namespaces = new HashMap<>();
        namespaces.put(StormConstants.SOAP, StormConstants.NAMESPACE);
        SOAPEnvelope envelope = SOAPService.createSOAPEnvelope(soapMessage, namespaces);

        SOAPService.createSOAPAction(soapMessage, StormConstants.SOAP_ACTION_SZUKAJ_BURZY);
        createSOAPMessage(input, StormConstants.SOAP, envelope);

        LOGGER.debug("City Request SOAP Message:");
        LOGGER.debug(SoapMessageUtilities.soapMessageToString(soapMessage));

        return soapMessage;
    }

    private void createSOAPMessage(String city, String namespace, SOAPEnvelope envelope) throws SOAPException {
        SOAPBody soapBody = envelope.getBody();
        SOAPElement findCity = soapBody.addChildElement(StormConstants.METHOD_MIEJSCOWOSC, namespace);
        SOAPElement nameElem = findCity.addChildElement(NAZWA, namespace);
        SOAPElement keyElem = findCity.addChildElement(KLUCZ, namespace);

        nameElem.addTextNode(city);
        keyElem.addTextNode(stormProperties.getStorm().get(StormProperties.KEY));
    }
}
