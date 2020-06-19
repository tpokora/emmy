package org.tpokora.storms.services.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tpokora.common.services.soap.SoapMessageUtilities;
import org.tpokora.common.services.soap.ISoapResponseMessageProcessor;
import org.tpokora.storms.model.StormResponse;

import javax.xml.soap.Node;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import static org.tpokora.common.services.soap.SoapMessageUtilities.elementValue;
import static org.tpokora.storms.services.processor.StormProcessorStrings.*;

public class StormSoapResponseProcessor implements ISoapResponseMessageProcessor<StormResponse> {

    private final Logger LOGGER = LoggerFactory.getLogger(StormSoapResponseProcessor.class);

    @Override
    public StormResponse process(SOAPMessage soapMessage) throws SOAPException {
        LOGGER.debug("Process SOAP City Response: ");
        LOGGER.debug(SoapMessageUtilities.soapMessageToString(soapMessage));
        StormResponse stormResponse = processStormResponse(soapMessage);
        LOGGER.debug("Storms: " + stormResponse);
        return stormResponse;
    }

    private StormResponse processStormResponse(SOAPMessage soapMessage) throws SOAPException {
        SOAPBody soapBody = soapMessage.getSOAPBody();
        Node response = (Node) soapBody.getElementsByTagName(NS_1_SZUKAJ_BURZY_RESPONSE).item(0);
        org.w3c.dom.Node returnElem = response.getParentElement().getElementsByTagName(RETURN).item(0);

        int amount = Integer.parseInt(elementValue(returnElem, LICZBA));
        double distance = Double.parseDouble(elementValue(returnElem, ODLEGLOSC));
        String direction = elementValue(returnElem, KIERUNEK);
        int time = Integer.parseInt(elementValue(returnElem, OKRES));

        return new StormResponse(amount, distance, direction, time);
    }
}
