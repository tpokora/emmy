package org.tpokora.storms.services;

import com.sun.xml.messaging.saaj.soap.impl.ElementImpl;
import org.springframework.stereotype.Service;
import org.tpokora.common.services.SOAPService;
import org.tpokora.config.properties.StormProperties;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

@Service
public class StormService {

    protected static final String SOAP = "soap";
    protected static final String NAMESPACE = "http://burze.dzis.net/soap.php";
    protected static final String URL = "http://burze.dzis.net/soap.php";
    protected static final String SOAP_ACTION_KEY_API = "http://burze.dzis.net/soap.php#KeyAPI";
    protected static final String SOAP_ACTION_MIEJSCOWOSC = "http://burze.dzis.net/soap.php#miejscowosc";
    protected static final String SOAP_ACTION_OSTRZEZENIA = "http://burze.dzis.net/soap.php#ostrzezenia_pogodowe";
    protected static final String SOAP_ACTION_SZUKAJ_BURZY = "http://burze.dzis.net/soap.php#szukaj_burzy";
    protected static final String METHOD_KEY_API = "KeyAPI";
    protected static final String METHOD_MIEJSCOWOSC = "miejscowosc";
    protected static final String METHOD_OSTRZEZENIA = "ostrzezenia_pogodowe";
    protected static final String METHOD_SZUKAJ_BURZY = "szukaj_burzy";

    protected StormProperties stormProperties;

    protected SOAPService soapService;

    public StormService(StormProperties stormProperties, SOAPService soapService) {
        this.stormProperties = stormProperties;
        this.soapService = soapService;
    }


    protected String elementValue(org.w3c.dom.Node element, String name) {
        return ((ElementImpl) element).getElementsByTagName(name).item(0).getTextContent();
    }

    public String printMsg(SOAPMessage soapMessage) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            soapMessage.writeTo(output);
            return new String(output.toByteArray());
        } catch (SOAPException e) {
            return "SOAP Error";
        } catch (IOException e) {
            return "Message read Error";
        }
    }
}
