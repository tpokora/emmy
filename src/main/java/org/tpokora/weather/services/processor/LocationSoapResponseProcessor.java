package org.tpokora.weather.services.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tpokora.common.services.soap.SoapMessageUtilities;
import org.tpokora.common.services.soap.ISoapResponseMessageProcessor;
import org.tpokora.weather.model.Location;

import javax.xml.soap.Node;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.util.Objects;

import static org.tpokora.weather.services.processor.StormProcessorStrings.*;

public class LocationSoapResponseProcessor implements ISoapResponseMessageProcessor<Location> {

    private final Logger LOGGER = LoggerFactory.getLogger(LocationSoapResponseProcessor.class);

    private static final String SOAP_MESSAGE_TO_PROCESS_IS_NULL = "SOAP Message to process is null!";

    @Override
    public Location process(SOAPMessage soapMessage) throws SOAPException {
        Objects.requireNonNull(soapMessage, SOAP_MESSAGE_TO_PROCESS_IS_NULL);
        LOGGER.debug("Process SOAP Location Response: ");
        LOGGER.debug(SoapMessageUtilities.soapMessageToString(soapMessage));

        SOAPBody soapBody = soapMessage.getSOAPBody();
        Node response = (javax.xml.soap.Node) soapBody.getElementsByTagName(NS_1_MIEJSCOWOSC_RESPONSE).item(0);
        org.w3c.dom.Node returnElem = response.getParentElement().getElementsByTagName(RETURN).item(0);

        Double x = Double.parseDouble(SoapMessageUtilities.elementValue(returnElem, X));
        Double y = Double.parseDouble(SoapMessageUtilities.elementValue(returnElem, Y));

        Location location = new Location(x, y);
        LOGGER.debug("Found location: " + location);
        return location;
    }
}