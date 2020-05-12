package org.tpokora.storms.services.processor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tpokora.common.services.soap.SOAPService;
import org.tpokora.storms.model.StormResponse;

import javax.xml.soap.*;
import java.util.HashMap;

public class StormSoapResponseProcessorTests {

    private StormSoapResponseProcessor stormSoapResponseProcessor;
    private SOAPMessage soapMessageResponse;

    @BeforeEach
    public void setup() throws SOAPException {
        stormSoapResponseProcessor = new StormSoapResponseProcessor();
        soapMessageResponse = generateSOAPResponse();
    }

    @Test
    @DisplayName("StormSoapResponseProcessor generate StormResponse object")
    public void testProcess() throws SOAPException {
        StormResponse expectedResponse = new StormResponse(11, 117.4, "E", 15);
        StormResponse stormResponse = stormSoapResponseProcessor.process(soapMessageResponse);
        Assertions.assertEquals(expectedResponse.getAmount(), stormResponse.getAmount());
        Assertions.assertEquals(expectedResponse.getDistance(), stormResponse.getDistance());
        Assertions.assertEquals(expectedResponse.getDirection(), stormResponse.getDirection());
        Assertions.assertEquals(expectedResponse.getTime(), stormResponse.getTime());
        Assertions.assertEquals(expectedResponse.toString(), stormResponse.toString());
    }

    private SOAPMessage generateSOAPResponse() throws SOAPException {
        SOAPMessage soapMessage = SOAPService.createSOAPMessage();
        SOAPEnvelope soapEnvelope = SOAPService.createSOAPEnvelope(soapMessage, new HashMap<>());
        SOAPBody soapBody = soapEnvelope.getBody();
        SOAPElement szukaj_burzyResponse = soapBody.addChildElement("ns1:szukaj_burzyResponse");
        SOAPElement returnElement = szukaj_burzyResponse.addChildElement("return");
        SOAPElement liczba = returnElement.addChildElement("liczba");
        liczba.addTextNode("11");
        SOAPElement odleglosc = returnElement.addChildElement("odleglosc");
        odleglosc.addTextNode("117.4");
        SOAPElement kierunek = returnElement.addChildElement("kierunek");
        kierunek.addTextNode("E");
        SOAPElement okres = returnElement.addChildElement("okres");
        okres.addTextNode("15");

        return soapMessage;
    }
}
