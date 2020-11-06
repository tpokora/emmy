package org.tpokora.services.weather.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tpokora.common.utils.StringUtils;
import org.tpokora.domain.weather.common.StormConstants;
import org.tpokora.domain.weather.Coordinates;
import org.tpokora.services.soap.ISoapRequestMessageProcessor;
import org.tpokora.services.soap.SOAPService;
import org.tpokora.services.soap.SoapMessageUtilities;
import org.tpokora.services.weather.properties.StormProperties;

import javax.xml.soap.*;
import java.util.HashMap;
import java.util.Objects;

import static org.tpokora.services.weather.processor.StormProcessorStrings.*;

public class WarningsSoapRequestProcessor implements ISoapRequestMessageProcessor<Coordinates> {

    private final Logger LOGGER = LoggerFactory.getLogger(WarningsSoapRequestProcessor.class);

    public static final String COORDINATES_WARNING_IS_NULL = "Coordinates for Warnings is null!";
    protected static final String METHOD_OSTRZEZENIA = "ostrzezenia_pogodowe";

    protected StormProperties stormProperties;

    public WarningsSoapRequestProcessor(StormProperties stormProperties) {
        this.stormProperties = stormProperties;
    }

    @Override
    public SOAPMessage process(Coordinates input) throws SOAPException {
        Objects.requireNonNull(input, COORDINATES_WARNING_IS_NULL);
        SOAPMessage soapMessage = SOAPService.createSOAPMessage();
        HashMap<String, String> namespaces = new HashMap<>();
        namespaces.put(StormConstants.SOAP, StormConstants.NAMESPACE);
        SOAPEnvelope envelope = SOAPService.createSOAPEnvelope(soapMessage, namespaces);

        SOAPService.createSOAPAction(soapMessage, StormConstants.SOAP_ACTION_SZUKAJ_BURZY);
        createSOAPMessage(input, StormConstants.SOAP, envelope);

        LOGGER.debug("Weather Warnings Request SOAP Message:");
        LOGGER.debug(SoapMessageUtilities.soapMessageToString(soapMessage));

        return soapMessage;
    }

    private void createSOAPMessage(Coordinates coordinates, String namespace, SOAPEnvelope envelope) throws SOAPException {
        SOAPBody soapBody = envelope.getBody();
        SOAPElement findStorm = soapBody.addChildElement(METHOD_OSTRZEZENIA, namespace);
        SOAPElement xElem = findStorm.addChildElement(X, namespace);
        SOAPElement yElem = findStorm.addChildElement(Y, namespace);
        SOAPElement keyElem = findStorm.addChildElement(KLUCZ, namespace);

        xElem.addTextNode(StringUtils.formatDouble(coordinates.getLongitudeDM()));
        yElem.addTextNode(StringUtils.formatDouble(coordinates.getLatitudeDM()));
        keyElem.addTextNode(stormProperties.getValue(StormProperties.KEY));
    }
}
