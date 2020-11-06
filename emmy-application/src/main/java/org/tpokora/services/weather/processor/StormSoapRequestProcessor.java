package org.tpokora.services.weather.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tpokora.common.utils.StringUtils;
import org.tpokora.domain.weather.StormRequest;
import org.tpokora.domain.weather.common.StormConstants;
import org.tpokora.services.soap.ISoapRequestMessageProcessor;
import org.tpokora.services.soap.SOAPService;
import org.tpokora.services.soap.SoapMessageUtilities;
import org.tpokora.services.weather.properties.StormProperties;

import javax.xml.soap.*;
import java.util.HashMap;

import static org.tpokora.services.weather.processor.StormProcessorStrings.*;

public class StormSoapRequestProcessor implements ISoapRequestMessageProcessor<StormRequest> {

    protected StormProperties stormProperties;
    private final Logger LOGGER = LoggerFactory.getLogger(StormSoapRequestProcessor.class);

    public StormSoapRequestProcessor(StormProperties stormProperties) {
        this.stormProperties = stormProperties;
    }

    @Override
    public SOAPMessage process(StormRequest input) throws SOAPException {
        SOAPMessage soapMessage = SOAPService.createSOAPMessage();
        HashMap<String, String> namespaces = new HashMap<>();
        namespaces.put(StormConstants.SOAP, StormConstants.NAMESPACE);
        SOAPEnvelope envelope = SOAPService.createSOAPEnvelope(soapMessage, namespaces);

        SOAPService.createSOAPAction(soapMessage, StormConstants.SOAP_ACTION_SZUKAJ_BURZY);
        createSOAPMessage(input, StormConstants.SOAP, envelope);

        LOGGER.debug("Request SOAP Message:");
        LOGGER.debug(SoapMessageUtilities.soapMessageToString(soapMessage));

        soapMessage.saveChanges();
        return soapMessage;
    }

    private void createSOAPMessage(StormRequest storm, String namespace, SOAPEnvelope envelope) throws SOAPException {
        SOAPBody soapBody = envelope.getBody();
        SOAPElement findStorm = soapBody.addChildElement(StormConstants.METHOD_SZUKAJ_BURZY, namespace);
        SOAPElement xElem = findStorm.addChildElement(X, namespace);
        SOAPElement yElem = findStorm.addChildElement(Y, namespace);
        SOAPElement radiusElem = findStorm.addChildElement(PROMIEN, namespace);
        SOAPElement keyElem = findStorm.addChildElement(KLUCZ, namespace);

        xElem.addTextNode(StringUtils.formatDouble(storm.getCoordinates().getLongitudeDM()));
        yElem.addTextNode(StringUtils.formatDouble(storm.getCoordinates().getLatitudeDM()));
        radiusElem.addTextNode(String.valueOf(storm.getDistance()));
        keyElem.addTextNode(stormProperties.getValue(StormProperties.KEY));
    }
}
