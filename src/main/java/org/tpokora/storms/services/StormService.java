package org.tpokora.storms.services;

import com.sun.xml.messaging.saaj.soap.impl.ElementImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tpokora.common.services.SOAPService;
import org.tpokora.config.AppProperties;

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

    @Autowired
    public AppProperties appProperties;

    @Autowired
    protected SOAPService soapService;


    protected String elementValue(org.w3c.dom.Node element, String name) {
        return ((ElementImpl) element).getElementsByTagName(name).item(0).getTextContent();
    }
}
