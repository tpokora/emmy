package org.tpokora.storms.services;

import org.springframework.stereotype.Service;
import org.tpokora.storms.model.Coordinates;
import org.tpokora.storms.model.StormResponse;
import org.tpokora.storms.model.Warning;

import javax.xml.soap.*;
import java.io.IOException;
import java.util.HashMap;

@Service
public class FindWarningService extends StormService {

    public SOAPMessage findWarning(Coordinates coordinates) throws SOAPException, IOException {
        SOAPMessage soapMessage = soapService.createSOAPMessage();
        HashMap<String, String> namespaces = new HashMap<>();
        namespaces.put(SOAP, NAMESPACE);
        SOAPEnvelope envelope = soapService.createSOAPEnvelope(soapMessage, namespaces);

        soapService.createSOAPHeader(soapMessage, SOAP_ACTION_OSTRZEZENIA);
        createSOAPMessage(coordinates, SOAP, envelope);

        System.out.println("Request SOAP Message:");
        soapMessage.writeTo(System.out);
        System.out.println();
        soapMessage.saveChanges();

        SOAPMessage soapResponse = soapService.sendSOAPMessage(soapMessage, URL);

        System.out.println("Response SOAP Message:");
        soapResponse.writeTo(System.out);
        System.out.println();

        return soapResponse;
    }

    public Warning handleResponse(SOAPMessage soapMessage) throws SOAPException {
        SOAPBody soapBody = soapMessage.getSOAPBody();
        Node response = (Node) soapBody.getElementsByTagName("ns1:ostrzezenia_pogodoweResponse").item(0);
        org.w3c.dom.Node returnElem = response.getParentElement().getElementsByTagName("return").item(0);
        Warning warning = new Warning();
        warning.setFromDay(elementValue(returnElem, "od_dnia"));
        warning.setToDay(elementValue(returnElem, "do_dnia"));

        warning.setCold(Integer.parseInt(elementValue(returnElem, "mroz")));
        warning.setColdFromDay(elementValue(returnElem, "mroz_od_dnia"));
        warning.setColdToDay(elementValue(returnElem, "mroz_do_dnia"));

        warning.setHot(Integer.parseInt(elementValue(returnElem, "upal")));
        warning.setHotFromDay(elementValue(returnElem, "upal_od_dnia"));
        warning.setHotToDay(elementValue(returnElem, "upal_do_dnia"));

        warning.setWind(Integer.parseInt(elementValue(returnElem, "wiatr")));
        warning.setWindFromDay(elementValue(returnElem, "wiatr_od_dnia"));
        warning.setWindToDay(elementValue(returnElem, "wiatr_do_dnia"));

        warning.setRain(Integer.parseInt(elementValue(returnElem, "opad")));
        warning.setRainFromDay(elementValue(returnElem, "opad_od_dnia"));
        warning.setRainToDay(elementValue(returnElem, "opad_do_dnia"));

        warning.setStorm(Integer.parseInt(elementValue(returnElem, "burza")));
        warning.setStormFromDay(elementValue(returnElem, "burza_od_dnia"));
        warning.setStormToDay(elementValue(returnElem, "burza_do_dnia"));

        warning.setWhirlwind(Integer.parseInt(elementValue(returnElem, "traba")));
        warning.setWhirlwindFromDay(elementValue(returnElem, "traba_od_dnia"));
        warning.setWhirlwindToDay(elementValue(returnElem, "traba_do_dnia"));

        return warning;
    }

    private void createSOAPMessage(Coordinates coordinates, String namespace, SOAPEnvelope envelope) throws SOAPException {
        SOAPBody soapBody = envelope.getBody();
        SOAPElement findStorm = soapBody.addChildElement(METHOD_OSTRZEZENIA, namespace);
        SOAPElement xElem = findStorm.addChildElement("x", namespace);
        SOAPElement yElem = findStorm.addChildElement("y", namespace);
        SOAPElement keyElem = findStorm.addChildElement("klucz", namespace);

        xElem.addTextNode(String.valueOf(coordinates.getX()));
        yElem.addTextNode(String.valueOf(coordinates.getY()));
        keyElem.addTextNode(environment.getProperty("storm.key"));
    }
}
