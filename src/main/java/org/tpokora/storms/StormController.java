package org.tpokora.storms;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.soap.*;


@RestController
public class StormController {

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
//    private PropertyInfo pfApiKey;


    @RequestMapping(value = "/storm/", method = RequestMethod.GET)
    public ResponseEntity<Storm> getStormByCordinates(@RequestParam("x") int x, @RequestParam("y") int y) throws Exception {
        Storm storm = new Storm();
        storm.setX(x);
        storm.setY(y);

        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String namespace = "soap";
        String namespaceUri = "https://burze.dzis.net/soap.php";

        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration(namespace, namespaceUri);

        createSOAPMessage(storm, namespace, envelope);
        createSOAPHeader(soapMessage, SOAP_ACTION_SZUKAJ_BURZY);

        System.out.println("Request SOAP Message:");
        soapMessage.writeTo(System.out);
        System.out.println();
        soapMessage.saveChanges();

        SOAPMessage soapResponse = sendSOAPMessage(soapMessage, URL);

        System.out.println("Response SOAP Message:");
        soapResponse.writeTo(System.out);
        System.out.println();

        return new ResponseEntity<>(storm, HttpStatus.OK);
    }

    private SOAPMessage sendSOAPMessage(SOAPMessage soapMessage, String url) throws SOAPException {
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();

        return soapConnection.call(soapMessage, url);
    }

    private void createSOAPHeader(SOAPMessage soapMessage, String soapAction) {
        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", soapAction);
    }

    private void createSOAPMessage(Storm storm, String namespace, SOAPEnvelope envelope) throws SOAPException {
        SOAPBody soapBody = envelope.getBody();
        SOAPElement szukaj_burzy = soapBody.addChildElement("szukaj_burzy", namespace);
        SOAPElement xElem = szukaj_burzy.addChildElement("x", namespace);
        SOAPElement yElem = szukaj_burzy.addChildElement("y", namespace);
        SOAPElement radiusElem = szukaj_burzy.addChildElement("promien", namespace);
        SOAPElement keyElem = szukaj_burzy.addChildElement("klucz", namespace);

        xElem.addTextNode(String.valueOf(storm.getX()));
        yElem.addTextNode(String.valueOf(storm.getY()));
        radiusElem.addTextNode("30");
        keyElem.addTextNode("KEY");
    }
}
