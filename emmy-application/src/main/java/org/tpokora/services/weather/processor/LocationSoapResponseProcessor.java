package org.tpokora.services.weather.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tpokora.domain.weather.Location;
import org.tpokora.services.soap.ISoapResponseMessageProcessor;
import org.tpokora.services.soap.SoapMessageUtilities;

import javax.xml.soap.Node;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.util.Objects;

import static org.tpokora.services.weather.processor.StormProcessorStrings.*;

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
