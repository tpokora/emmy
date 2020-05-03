package org.tpokora.storms.services.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tpokora.common.services.soap.SoapMessageUtilities;
import org.tpokora.common.services.soap.SoapResponseMessageProcessor;
import org.tpokora.storms.model.StormResponse;

import javax.xml.soap.Node;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

public class StormSoapResponseProcessor implements SoapResponseMessageProcessor<StormResponse> {

    private final Logger LOGGER = LoggerFactory.getLogger(StormSoapResponseProcessor.class);

    @Override
    public StormResponse process(SOAPMessage soapMessage) throws SOAPException {
        LOGGER.debug("Process SOAP City Response: ");
        LOGGER.debug(SoapMessageUtilities.soapMessageToString(soapMessage));
        SOAPBody soapBody = soapMessage.getSOAPBody();
        Node response = (Node) soapBody.getElementsByTagName("ns1:szukaj_burzyResponse").item(0);
        org.w3c.dom.Node returnElem = response.getParentElement().getElementsByTagName("return").item(0);
        int amount = Integer.parseInt(SoapMessageUtilities.elementValue(returnElem, "liczba"));
        double distance = Double.parseDouble(SoapMessageUtilities.elementValue(returnElem, "odleglosc"));
        String direction = SoapMessageUtilities.elementValue(returnElem, "kierunek");
        int time = Integer.parseInt(SoapMessageUtilities.elementValue(returnElem, "okres"));
        StormResponse stormResponse = new StormResponse(amount, distance, direction, time);
        LOGGER.debug("Storms: " + stormResponse);
        return stormResponse;
    }
}
