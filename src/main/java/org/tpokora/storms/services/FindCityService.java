package org.tpokora.storms.services;

import org.springframework.stereotype.Service;
import org.tpokora.common.services.SOAPService;
import org.tpokora.config.properties.AppProperties;
import org.tpokora.storms.model.City;

import javax.xml.soap.*;
import java.io.IOException;
import java.util.HashMap;

@Service
public class FindCityService extends StormService {

    public FindCityService(AppProperties appProperties, SOAPService soapService) {
        super(appProperties, soapService);
    }

    public SOAPMessage findCity(String city) throws SOAPException, IOException {
        SOAPMessage soapMessage = soapService.createSOAPMessage();
        HashMap<String, String> namespaces = new HashMap<>();
        namespaces.put(SOAP, NAMESPACE);
        SOAPEnvelope envelope = soapService.createSOAPEnvelope(soapMessage, namespaces);

        soapService.createSOAPHeader(soapMessage, SOAP_ACTION_SZUKAJ_BURZY);
        createSOAPMessage(city, SOAP, envelope);

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

    public City handleResponse(SOAPMessage soapMessage) throws SOAPException {
        SOAPBody soapBody = soapMessage.getSOAPBody();
        Node response = (Node) soapBody.getElementsByTagName("ns1:miejscowoscResponse").item(0);
        org.w3c.dom.Node returnElem = response.getParentElement().getElementsByTagName("return").item(0);
        Double x = Double.parseDouble(elementValue(returnElem, "x"));
        Double y = Double.parseDouble(elementValue(returnElem, "y"));

        City city = new City(x, y);
        return city;
    }

    private void createSOAPMessage(String city, String namespace, SOAPEnvelope envelope) throws SOAPException {
        SOAPBody soapBody = envelope.getBody();
        SOAPElement findCity = soapBody.addChildElement(METHOD_MIEJSCOWOSC, namespace);
        SOAPElement nameElem = findCity.addChildElement("nazwa", namespace);
        SOAPElement keyElem = findCity.addChildElement("klucz", namespace);

        nameElem.addTextNode(city);
        keyElem.addTextNode(appProperties.getStorm().get("key"));
    }
}
