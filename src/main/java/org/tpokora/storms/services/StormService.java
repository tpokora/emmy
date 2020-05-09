package org.tpokora.storms.services;

import com.sun.xml.messaging.saaj.soap.impl.ElementImpl;
import org.springframework.stereotype.Service;
import org.tpokora.common.services.soap.SOAPService;
import org.tpokora.common.services.soap.SoapRequestMessageProcessor;
import org.tpokora.common.services.soap.SoapResponseMessageProcessor;
import org.tpokora.config.properties.StormProperties;

@Service
public class StormService {

    // TODO: To be moved to StormConstants
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

    protected SoapRequestMessageProcessor soapRequestMessageProcessor;
    protected SoapResponseMessageProcessor soapResponseMessageProcessor;

    protected SOAPService soapService;

    public StormService(StormProperties stormProperties, SOAPService soapService) {
        this.stormProperties = stormProperties;
    }

    protected String elementValue(org.w3c.dom.Node element, String name) {
        return ((ElementImpl) element).getElementsByTagName(name).item(0).getTextContent();
    }
}
