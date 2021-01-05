package org.tpokora.application.soap;


import org.springframework.stereotype.Service;

import javax.xml.soap.*;
import java.util.Map;

@Service
public class SOAPService {

    public static final String SOAP_ACTION = "SOAPAction";

    public static SOAPMessage createSOAPMessage() throws SOAPException {
        MessageFactory messageFactory = MessageFactory.newInstance();
        return messageFactory.createMessage();
    }

    public static SOAPEnvelope createSOAPEnvelope(SOAPMessage soapMessage, Map<String, String> namespaces) throws SOAPException {
        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope envelope = soapPart.getEnvelope();
        for (Map.Entry entry : namespaces.entrySet()) {
            envelope.addNamespaceDeclaration(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
        }

        return envelope;
    }

    public SOAPMessage sendSOAPMessage(SOAPMessage soapMessage, String url) throws SOAPException {
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();

        return soapConnection.call(soapMessage, url);
    }

    public static void createSOAPAction(SOAPMessage soapMessage, String soapAction) {
        createSOAPHeader(soapMessage, SOAP_ACTION, soapAction);
    }

    public static void createSOAPHeader(SOAPMessage soapMessage, String headerName, String headerValue) {
        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader(headerName, headerValue);
    }
}
