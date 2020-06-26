package org.tpokora.weather.services.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tpokora.common.services.soap.SOAPService;
import org.tpokora.common.services.soap.SoapMessageUtilities;
import org.tpokora.common.services.soap.ISoapRequestMessageProcessor;
import org.tpokora.weather.properties.StormProperties;
import org.tpokora.weather.model.StormRequest;
import org.tpokora.weather.common.StormConstants;

import javax.xml.soap.*;
import java.util.HashMap;

import static org.tpokora.weather.services.processor.StormProcessorStrings.*;

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

        xElem.addTextNode(String.valueOf(storm.getCoordinates().getX()));
        yElem.addTextNode(String.valueOf(storm.getCoordinates().getY()));
        radiusElem.addTextNode(String.valueOf(storm.getDistance()));
        keyElem.addTextNode(stormProperties.getValue(StormProperties.KEY));
    }
}
