package org.tpokora.storms.services.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tpokora.common.services.soap.SOAPService;
import org.tpokora.common.services.soap.SoapMessageUtilities;
import org.tpokora.common.services.soap.SoapRequestMessageProcessor;
import org.tpokora.config.properties.StormProperties;
import org.tpokora.storms.services.StormConstants;

import javax.xml.soap.*;
import java.util.HashMap;
import java.util.Objects;

public class CitySoapRequestProcessor implements SoapRequestMessageProcessor<String> {

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
        SOAPElement nameElem = findCity.addChildElement("nazwa", namespace);
        SOAPElement keyElem = findCity.addChildElement("klucz", namespace);

        nameElem.addTextNode(city);
        keyElem.addTextNode(stormProperties.getStorm().get(StormProperties.KEY));
    }
}
