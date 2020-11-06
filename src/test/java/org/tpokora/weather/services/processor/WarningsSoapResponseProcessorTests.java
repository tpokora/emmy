package org.tpokora.weather.services.processor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tpokora.domain.weather.Period;
import org.tpokora.domain.weather.Warning;
import org.tpokora.domain.weather.common.WarningStrings;
import org.tpokora.services.soap.SOAPService;
import org.tpokora.services.weather.processor.WarningsSoapResponseProcessor;

import javax.xml.soap.*;
import java.util.HashMap;
import java.util.List;

public class WarningsSoapResponseProcessorTests {

    public static final String FROM = "2020-05-05 17:00";
    public static final String TO = "2020-05-10 17:00";
    public static final String ZERO = "0";
    private WarningsSoapResponseProcessor warningsSoapResponseProcessor;
    private SOAPMessage soapMessageResponse;

    @BeforeEach
    public void setup() throws SOAPException {
        warningsSoapResponseProcessor = new WarningsSoapResponseProcessor();
    }

    @Test
    @DisplayName("WarningsSoapResponseProcessor generate empty Warnings list")
    public void testEmptyWarningResponse() throws SOAPException {
        soapMessageResponse = generateSOAPResponse(false);
        List<Warning> warnings = warningsSoapResponseProcessor.process(soapMessageResponse);

        Assertions.assertEquals(0, warnings.size());
    }

    @Test
    @DisplayName("WarningsSoapResponseProcessor generate Warnings list")
    public void testWarningResponse() throws SOAPException {
        soapMessageResponse = generateSOAPResponse(true);
        List<Warning> warnings = warningsSoapResponseProcessor.process(soapMessageResponse);

        Assertions.assertEquals(6, warnings.size());
        warnings.forEach(warning -> assertWarning(1, new Period(FROM, TO, WarningStrings.WARNINGS_DATE_FORMAT), warning));
    }

    private void assertWarning(int expectedLevel, Period expectedPeriod, Warning warning) {
        Assertions.assertEquals(expectedLevel, warning.getLevel());
        Assertions.assertEquals(expectedPeriod.getFromString(), warning.getPeriod().getFromString());
        Assertions.assertEquals(expectedPeriod.getToString(), warning.getPeriod().getToString());
    }

    private SOAPMessage generateSOAPResponse(boolean warnings) throws SOAPException {
        SOAPMessage soapMessage = SOAPService.createSOAPMessage();
        SOAPEnvelope soapEnvelope = SOAPService.createSOAPEnvelope(soapMessage, new HashMap<>());
        SOAPBody soapBody = soapEnvelope.getBody();
        SOAPElement ostrzezenia_pogodoweResponse = soapBody.addChildElement("ns1:ostrzezenia_pogodoweResponse");
        SOAPElement returnElement = ostrzezenia_pogodoweResponse.addChildElement("return");

        if (warnings) {
            addElement(returnElement, "mroz" , 1, FROM, TO);
            addElement(returnElement, "upal" , 1, FROM, TO);
            addElement(returnElement, "wiatr" , 1, FROM, TO);
            addElement(returnElement, "opad" , 1, FROM, TO);
            addElement(returnElement, "burza" , 1, FROM, TO);
            addElement(returnElement, "traba" , 1, FROM, TO);
        } else {
            addElement(returnElement, "mroz" , 0, ZERO, ZERO);
            addElement(returnElement, "upal" , 0, ZERO, ZERO);
            addElement(returnElement, "wiatr" , 0, ZERO, ZERO);
            addElement(returnElement, "opad" , 0, ZERO, ZERO);
            addElement(returnElement, "burza" , 0, ZERO, ZERO);
            addElement(returnElement, "traba" , 0, ZERO, ZERO);
        }

        return soapMessage;
    }

    private void addElement(SOAPElement returnElement, String prefix, int level, String from, String to) throws SOAPException {
        SOAPElement name = returnElement.addChildElement(prefix);
        name.addTextNode(String.valueOf(level));
        SOAPElement fromElement = returnElement.addChildElement(String.format("%s_od_dnia", prefix));
        fromElement.addTextNode(from);
        SOAPElement toElement = returnElement.addChildElement(String.format("%s_do_dnia", prefix));
        toElement.addTextNode(to);
    }
}
