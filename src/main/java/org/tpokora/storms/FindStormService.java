package org.tpokora.storms;

import org.springframework.stereotype.Service;
import org.tpokora.storms.model.StormRequest;
import org.tpokora.storms.model.StormResponse;

import javax.xml.soap.*;
import java.io.IOException;
import java.util.HashMap;

@Service
public class FindStormService extends StormService {

    public static final String SOAP = "soap";
    private final String NAMESPACE = "http://burze.dzis.net/soap.php";
    private final String URL = "http://burze.dzis.net/soap.php";
    private final String SOAP_ACTION_KEY_API = "http://burze.dzis.net/soap.php#KeyAPI";
    private final String SOAP_ACTION_MIEJSCOWOSC = "http://burze.dzis.net/soap.php#miejscowosc";
    private final String SOAP_ACTION_OSTRZEZENIA = "http://burze.dzis.net/soap.php#ostrzezenia_pogodowe";
    private final String SOAP_ACTION_SZUKAJ_BURZY = "http://burze.dzis.net/soap.php#szukaj_burzy";
    private final String METHOD_KEY_API = "KeyAPI";
    private final String METHOD_MIEJSCOWOSC = "miejscowosc";
    private final String METHOD_OSTRZEZENIA = "ostrzezenia_pogodowe";
    private final String METHOD_SZUKAJ_BURZY = "szukaj_burzy";

    public SOAPMessage checkStorm(StormRequest stormRequest) throws SOAPException, IOException {
        SOAPMessage soapMessage = soapService.createSOAPMessage();
        HashMap<String, String> namespaces = new HashMap<>();
        namespaces.put(SOAP, NAMESPACE);
        SOAPEnvelope envelope = soapService.createSOAPEnvelope(soapMessage, namespaces);

        soapService.createSOAPHeader(soapMessage, SOAP_ACTION_SZUKAJ_BURZY);
        createSOAPMessage(stormRequest, SOAP, envelope);

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

    public StormResponse handleResponse(SOAPMessage soapMessage) throws SOAPException {
        SOAPBody soapBody = soapMessage.getSOAPBody();
        Node response = (Node) soapBody.getElementsByTagName("ns1:szukaj_burzyResponse").item(0);
        org.w3c.dom.Node returnElem = response.getParentElement().getElementsByTagName("return").item(0);
        int amount = Integer.parseInt(elementValue(returnElem, "liczba"));
        double distance = Double.parseDouble(elementValue(returnElem, "odleglosc"));
        String direction = elementValue(returnElem, "kierunek");
        int time = Integer.parseInt(elementValue(returnElem, "okres"));
        StormResponse stormResponse = new StormResponse(amount, distance, direction, time);
        return stormResponse;
    }

    private void createSOAPMessage(StormRequest storm, String namespace, SOAPEnvelope envelope) throws SOAPException {
        SOAPBody soapBody = envelope.getBody();
        SOAPElement findStorm = soapBody.addChildElement(METHOD_SZUKAJ_BURZY, namespace);
        SOAPElement xElem = findStorm.addChildElement("x", namespace);
        SOAPElement yElem = findStorm.addChildElement("y", namespace);
        SOAPElement radiusElem = findStorm.addChildElement("promien", namespace);
        SOAPElement keyElem = findStorm.addChildElement("klucz", namespace);

        xElem.addTextNode(String.valueOf(storm.getX()));
        yElem.addTextNode(String.valueOf(storm.getY()));
        radiusElem.addTextNode(String.valueOf(storm.getDistance()));
        keyElem.addTextNode(environment.getProperty("storm.key"));
    }

}
