package org.tpokora.common.services.soap;

import org.springframework.stereotype.Service;

import javax.xml.soap.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class SOAPService {

    public static final String SOAP_ACTION = "SOAPAction";

    public static SOAPMessage createSOAPMessage() throws SOAPException {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        return soapMessage;
    }

    public static SOAPEnvelope createSOAPEnvelope(SOAPMessage soapMessage, HashMap<String, String> namespaces) throws SOAPException {
        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope envelope = soapPart.getEnvelope();
        for (Map.Entry entry : namespaces.entrySet()) {
            envelope.addNamespaceDeclaration(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
        }

        return envelope;
    }

    public static SOAPMessage sendSOAPMessage(SOAPMessage soapMessage, String url) throws SOAPException {
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();

        return soapConnection.call(soapMessage, url);
    }

    public static void createSOAPHeader(SOAPMessage soapMessage, String soapAction) {
        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader(SOAP_ACTION, soapAction);
    }
}
